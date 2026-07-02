package car_wale.demo.recommendation;

public class RecommendationNotFoundException extends RuntimeException {

	public RecommendationNotFoundException(Long queryId) {
		super("Recommendation query " + queryId + " was not found.");
	}
}
