package car_wale.demo.recommendation;

public class RecommendationCatalogEmptyException extends RuntimeException {

	public RecommendationCatalogEmptyException() {
		super("Car catalog is empty. Add cars before requesting recommendations.");
	}
}
