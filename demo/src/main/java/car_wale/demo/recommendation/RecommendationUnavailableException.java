package car_wale.demo.recommendation;

public class RecommendationUnavailableException extends RuntimeException {

	public RecommendationUnavailableException() {
		super("No recommendations could be generated from the current catalog.");
	}
}
