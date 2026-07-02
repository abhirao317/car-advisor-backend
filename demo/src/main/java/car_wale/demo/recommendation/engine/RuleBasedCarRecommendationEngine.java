package car_wale.demo.recommendation.engine;

import car_wale.demo.car.Car;
import car_wale.demo.recommendation.RecommendationRequest;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class RuleBasedCarRecommendationEngine implements CarRecommendationEngine {

	private static final int MAX_RESULTS = 5;

	@Override
	public List<RecommendationCandidate> recommend(List<Car> cars, RecommendationRequest request) {
		return cars.stream()
				.map(car -> new RecommendationCandidate(car, score(car, request), buildReason(car, request)))
				.sorted(Comparator.comparingInt(RecommendationCandidate::getScore).reversed()
						.thenComparing(candidate -> valueOrZero(candidate.getCar().getSafetyRating()), Comparator.reverseOrder())
						.thenComparing(candidate -> valueOrHigh(candidate.getCar().getPriceInLakhs())))
				.limit(MAX_RESULTS)
				.collect(Collectors.toList());
	}

	private int score(Car car, RecommendationRequest request) {
		int score = 0;
		String queryText = normalize(request.getQueryText());

		if (request.getBudgetMin() != null && car.getPriceInLakhs() != null
				&& car.getPriceInLakhs() >= request.getBudgetMin()) {
			score += 15;
		}
		if (request.getBudgetMax() != null && car.getPriceInLakhs() != null
				&& car.getPriceInLakhs() <= request.getBudgetMax()) {
			score += 35;
		}
		if (matches(request.getBodyType(), car.getBodyType())) {
			score += 20;
		}
		if (matches(request.getFuelType(), car.getFuelType())) {
			score += 15;
		}
		if (car.getSafetyRating() != null) {
			score += (int) Math.round(car.getSafetyRating() * 3);
		}
		if (car.getMileage() != null) {
			score += Math.min(12, (int) Math.round(car.getMileage() / 2));
		}
		if (textMentions(queryText, car.getMake(), car.getModel(), car.getSegment(), car.getTransmission())) {
			score += 12;
		}
		if (mentionsAny(queryText, "safe", "safety", "rating") && car.getSafetyRating() != null) {
			score += (int) Math.round(car.getSafetyRating() * 4);
		}
		if (transmissionMatchesQuery(queryText, car.getTransmission())) {
			score += 18;
		}
		if (mentionsAny(queryText, "family", "seven", "7 seater", "parents") && car.getSeatingCapacity() != null
				&& car.getSeatingCapacity() >= 7) {
			score += 18;
		}
		if (mentionsAny(queryText, "mileage", "efficient", "economy", "city") && car.getMileage() != null) {
			score += Math.min(18, (int) Math.round(car.getMileage() / 2));
		}
		if (mentionsAny(queryText, "electric", "ev") && matches("electric", car.getFuelType())) {
			score += 25;
		}

		return score;
	}

	private String buildReason(Car car, RecommendationRequest request) {
		String queryText = normalize(request.getQueryText());
		List<String> reasons = new ArrayList<>();

		if (request.getBudgetMax() != null && car.getPriceInLakhs() != null
				&& car.getPriceInLakhs() <= request.getBudgetMax()) {
			reasons.add("fits your budget");
		} else if (car.getPriceInLakhs() != null) {
			reasons.add("is close to your budget");
		}
		if (matches(request.getBodyType(), car.getBodyType())) {
			reasons.add("matches your preferred body type");
		}
		if (matches(request.getFuelType(), car.getFuelType())) {
			reasons.add("uses your preferred fuel type");
		}
		if (transmissionMatchesQuery(queryText, car.getTransmission())) {
			reasons.add("matches your transmission preference");
		}
		if (mentionsAny(queryText, "safe", "safety", "rating") && car.getSafetyRating() != null) {
			reasons.add(String.format("supports your safety priority with a %.1f/5.0 rating", car.getSafetyRating()));
		}
		if (mentionsAny(queryText, "family", "seven", "7 seater", "parents") && car.getSeatingCapacity() != null
				&& car.getSeatingCapacity() >= 7) {
			reasons.add("has " + car.getSeatingCapacity() + " seats for family use");
		}
		if (mentionsAny(queryText, "mileage", "efficient", "economy", "city") && car.getMileage() != null) {
			reasons.add(String.format("offers %.1f km/l mileage", car.getMileage()));
		}

		if (reasons.size() < 3 && car.getSafetyRating() != null) {
			reasons.add(String.format("scores %.1f/5.0 for safety", car.getSafetyRating()));
		}
		if (reasons.isEmpty()) {
			reasons.add("is a relevant catalog match for your shortlist");
		}

		return String.format("%s %s %s because it %s.",
				car.getMake(), car.getModel(), car.getVariant(), String.join(", ", reasons));
	}

	private boolean matches(String requested, String actual) {
		return StringUtils.hasText(requested) && StringUtils.hasText(actual)
				&& actual.equalsIgnoreCase(requested.trim());
	}

	private boolean textMentions(String queryText, String... values) {
		if (!StringUtils.hasText(queryText)) {
			return false;
		}
		for (String value : values) {
			if (StringUtils.hasText(value) && queryText.contains(value.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	private boolean mentionsAny(String queryText, String... phrases) {
		return textMentions(queryText, phrases);
	}

	private boolean transmissionMatchesQuery(String queryText, String transmission) {
		if (!StringUtils.hasText(transmission)) {
			return false;
		}
		String normalizedTransmission = transmission.toLowerCase();
		if (mentionsAny(queryText, "automatic", "auto")) {
			return normalizedTransmission.contains("automatic")
					|| normalizedTransmission.contains("amt")
					|| normalizedTransmission.contains("cvt");
		}
		if (mentionsAny(queryText, "amt")) {
			return normalizedTransmission.contains("amt");
		}
		if (mentionsAny(queryText, "cvt")) {
			return normalizedTransmission.contains("cvt");
		}
		if (mentionsAny(queryText, "manual")) {
			return normalizedTransmission.contains("manual");
		}
		return false;
	}

	private String normalize(String value) {
		return StringUtils.hasText(value) ? value.toLowerCase() : "";
	}

	private Double valueOrZero(Double value) {
		return value != null ? value : 0.0;
	}

	private Double valueOrHigh(Double value) {
		return value != null ? value : Double.MAX_VALUE;
	}
}
