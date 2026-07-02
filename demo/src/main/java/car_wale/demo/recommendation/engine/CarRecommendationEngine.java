package car_wale.demo.recommendation.engine;

import car_wale.demo.car.Car;
import car_wale.demo.recommendation.RecommendationRequest;
import java.util.List;

public interface CarRecommendationEngine {

	List<RecommendationCandidate> recommend(List<Car> cars, RecommendationRequest request);
}
