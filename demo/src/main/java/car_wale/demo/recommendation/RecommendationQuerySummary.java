package car_wale.demo.recommendation;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationQuerySummary {

	private final Long id;
	private final String queryText;
	private final Double budgetMin;
	private final Double budgetMax;
	private final String bodyType;
	private final String fuelType;
	private final LocalDateTime createdAt;

	public static RecommendationQuerySummary from(CarRecommendationQuery query) {
		return new RecommendationQuerySummary(
				query.getId(),
				query.getQueryText(),
				query.getBudgetMin(),
				query.getBudgetMax(),
				query.getBodyType(),
				query.getFuelType(),
				query.getCreatedAt()
		);
	}
}
