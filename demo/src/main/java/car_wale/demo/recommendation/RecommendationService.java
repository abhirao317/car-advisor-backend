package car_wale.demo.recommendation;

import car_wale.demo.car.Car;
import car_wale.demo.car.CarRepository;
import car_wale.demo.recommendation.engine.CarRecommendationEngine;
import car_wale.demo.recommendation.engine.RecommendationCandidate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class RecommendationService {

	private final CarRepository carRepository;
	private final CarRecommendationQueryRepository queryRepository;
	private final RecommendationResultRepository resultRepository;
	private final CarRecommendationEngine recommendationEngine;

	@Transactional
	public RecommendationResponse recommend(RecommendationRequest request) {
		List<Car> cars = carRepository.findAll();
		if (cars.isEmpty()) {
			throw new RecommendationCatalogEmptyException();
		}

		RecommendationRequest normalizedRequest = new RecommendationRequest(
				normalize(request.getQueryText()),
				request.getBudgetMin(),
				request.getBudgetMax(),
				normalize(request.getBodyType()),
				normalize(request.getFuelType())
		);

		List<RecommendationCandidate> candidates = recommendationEngine.recommend(cars, normalizedRequest);
		if (candidates.isEmpty()) {
			throw new RecommendationUnavailableException();
		}

		CarRecommendationQuery query = queryRepository.save(CarRecommendationQuery.builder()
				.queryText(normalizedRequest.getQueryText())
				.budgetMin(normalizedRequest.getBudgetMin())
				.budgetMax(normalizedRequest.getBudgetMax())
				.bodyType(normalizedRequest.getBodyType())
				.fuelType(normalizedRequest.getFuelType())
				.build());

		List<RecommendationResult> results = new ArrayList<>();
		for (int index = 0; index < candidates.size(); index++) {
			RecommendationCandidate candidate = candidates.get(index);
			results.add(RecommendationResult.builder()
					.query(query)
					.car(candidate.getCar())
					.rank(index + 1)
					.reason(candidate.getReason())
					.build());
		}

		List<RecommendationResult> savedResults = resultRepository.saveAll(results);

		return toResponse(query, savedResults);
	}

	@Transactional(readOnly = true)
	public RecommendationResponse findByQueryId(Long queryId) {
		CarRecommendationQuery query = queryRepository.findById(queryId)
				.orElseThrow(() -> new RecommendationNotFoundException(queryId));
		List<RecommendationResult> results = resultRepository.findByQueryIdOrderByRankAsc(queryId);

		return toResponse(query, results);
	}

	@Transactional(readOnly = true)
	public List<RecommendationQuerySummary> findRecentQueries() {
		return queryRepository.findTop10ByOrderByCreatedAtDesc().stream()
				.map(RecommendationQuerySummary::from)
				.collect(Collectors.toList());
	}

	private RecommendationResponse toResponse(CarRecommendationQuery query, List<RecommendationResult> results) {
		return new RecommendationResponse(
				query.getId(),
				query.getQueryText(),
				query.getBudgetMin(),
				query.getBudgetMax(),
				query.getBodyType(),
				query.getFuelType(),
				query.getCreatedAt(),
				results.stream()
						.sorted(Comparator.comparing(RecommendationResult::getRank))
						.map(RecommendationItemResponse::from)
						.collect(Collectors.toList())
		);
	}

	private String normalize(String value) {
		return StringUtils.hasText(value) ? value.trim() : null;
	}
}
