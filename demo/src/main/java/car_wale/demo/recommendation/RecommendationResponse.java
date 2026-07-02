package car_wale.demo.recommendation;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationResponse {

	private final Long queryId;
	private final String queryText;
	private final Double budgetMin;
	private final Double budgetMax;
	private final String bodyType;
	private final String fuelType;
	private final LocalDateTime createdAt;
	private final List<RecommendationItemResponse> results;
}
