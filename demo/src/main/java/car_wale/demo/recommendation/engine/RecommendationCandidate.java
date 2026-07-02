package car_wale.demo.recommendation.engine;

import car_wale.demo.car.Car;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecommendationCandidate {

	private final Car car;
	private final int score;
	private final String reason;
}
