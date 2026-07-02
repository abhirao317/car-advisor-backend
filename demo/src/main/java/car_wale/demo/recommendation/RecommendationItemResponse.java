package car_wale.demo.recommendation;

import car_wale.demo.car.CarSummary;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationItemResponse {

	private final Long id;
	private final Integer rank;
	private final CarSummary car;
	private final String reason;

	public static RecommendationItemResponse from(RecommendationResult result) {
		return new RecommendationItemResponse(
				result.getId(),
				result.getRank(),
				CarSummary.from(result.getCar()),
				result.getReason()
		);
	}
}
