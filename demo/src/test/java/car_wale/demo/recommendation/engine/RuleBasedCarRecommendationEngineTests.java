package car_wale.demo.recommendation.engine;

import static org.assertj.core.api.Assertions.assertThat;

import car_wale.demo.car.Car;
import car_wale.demo.recommendation.RecommendationRequest;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class RuleBasedCarRecommendationEngineTests {

	private final RuleBasedCarRecommendationEngine engine = new RuleBasedCarRecommendationEngine();

	@Test
	void ranksMatchingCarAboveWeakerFit() {
		Car suv = car("Tata", "Nexon", 13.8, "SUV", "Petrol", "Automatic", 17.0, 5, 5.0);
		Car sedan = car("Honda", "City", 16.35, "Sedan", "Petrol", "Manual", 17.8, 5, 5.0);
		RecommendationRequest request = new RecommendationRequest(
				"I want a safe automatic SUV",
				8.0,
				18.0,
				"SUV",
				"Petrol"
		);

		List<RecommendationCandidate> candidates = engine.recommend(Arrays.asList(sedan, suv), request);

		assertThat(candidates).hasSize(2);
		assertThat(candidates.get(0).getCar().getModel()).isEqualTo("Nexon");
		assertThat(candidates.get(0).getReason()).contains("fits your budget");
	}

	@Test
	void usesFreeTextTransmissionAndSafetyIntentInRankingReason() {
		Car automatic = car("Hyundai", "Creta", 15.3, "SUV", "Petrol", "Automatic", 17.4, 5, 5.0);
		Car manual = car("Mahindra", "XUV700", 19.9, "SUV", "Petrol", "Manual", 16.6, 7, 5.0);
		RecommendationRequest request = new RecommendationRequest(
				"I need a very safe auto SUV for mixed city and highway driving",
				10.0,
				20.0,
				"SUV",
				"Petrol"
		);

		List<RecommendationCandidate> candidates = engine.recommend(Arrays.asList(manual, automatic), request);

		assertThat(candidates.get(0).getCar().getModel()).isEqualTo("Creta");
		assertThat(candidates.get(0).getReason()).contains("matches your transmission preference");
		assertThat(candidates.get(0).getReason()).contains("supports your safety priority");
	}

	@Test
	void rewardsFamilySeatingIntentFromQueryText() {
		Car sevenSeater = car("Toyota", "Innova Hycross", 25.97, "MPV", "Hybrid", "Automatic", 21.1, 7, 5.0);
		Car fiveSeater = car("Honda", "Elevate", 17.85, "SUV", "Petrol", "Automatic", 16.9, 5, 5.0);
		RecommendationRequest request = new RecommendationRequest(
				"I want a family car for parents and seven people",
				15.0,
				30.0,
				null,
				null
		);

		List<RecommendationCandidate> candidates = engine.recommend(Arrays.asList(fiveSeater, sevenSeater), request);

		assertThat(candidates.get(0).getCar().getModel()).isEqualTo("Innova Hycross");
		assertThat(candidates.get(0).getReason()).contains("7 seats");
	}

	@Test
	void stillReturnsAReasonWhenCatalogDataIsIncomplete() {
		Car partial = car("Demo", "Prototype", null, "SUV", "Petrol", "Automatic", null, 5, null);
		RecommendationRequest request = new RecommendationRequest(
				"I want a safe automatic SUV with good mileage",
				null,
				null,
				"SUV",
				"Petrol"
		);

		List<RecommendationCandidate> candidates = engine.recommend(Arrays.asList(partial), request);

		assertThat(candidates).hasSize(1);
		assertThat(candidates.get(0).getReason()).contains("matches your preferred body type");
		assertThat(candidates.get(0).getReason()).doesNotContain("null");
	}

	private Car car(String make, String model, Double priceInLakhs, String bodyType, String fuelType,
			String transmission, Double mileage, Integer seatingCapacity, Double safetyRating) {
		return Car.builder()
				.make(make)
				.model(model)
				.variant("Top")
				.priceInLakhs(priceInLakhs)
				.bodyType(bodyType)
				.fuelType(fuelType)
				.transmission(transmission)
				.mileage(mileage)
				.seatingCapacity(seatingCapacity)
				.safetyRating(safetyRating)
				.segment(bodyType)
				.imageUrl("image")
				.build();
	}
}
