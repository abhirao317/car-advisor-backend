package car_wale.demo.recommendation;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import car_wale.demo.car.Car;
import car_wale.demo.car.CarRepository;
import car_wale.demo.recommendation.engine.CarRecommendationEngine;
import java.util.Arrays;
import java.util.Collections;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RecommendationServiceTests {

	@Mock
	private CarRepository carRepository;

	@Mock
	private CarRecommendationQueryRepository queryRepository;

	@Mock
	private RecommendationResultRepository resultRepository;

	@Mock
	private CarRecommendationEngine recommendationEngine;

	@InjectMocks
	private RecommendationService recommendationService;

	@Test
	void rejectsRecommendationsWhenCatalogIsEmpty() {
		when(carRepository.findAll()).thenReturn(Collections.emptyList());

		RecommendationRequest request = new RecommendationRequest(
				"I need a safe city car",
				null,
				null,
				null,
				null
		);

		assertThatThrownBy(() -> recommendationService.recommend(request))
				.isInstanceOf(RecommendationCatalogEmptyException.class)
				.hasMessage("Car catalog is empty. Add cars before requesting recommendations.");

		verify(queryRepository, never()).save(any(CarRecommendationQuery.class));
		verify(recommendationEngine, never()).recommend(any(), any(RecommendationRequest.class));
	}

	@Test
	void rejectsRecommendationsWhenEngineReturnsNoCandidates() {
		RecommendationRequest request = new RecommendationRequest(
				"I need a safe city car",
				null,
				null,
				null,
				null
		);
		when(carRepository.findAll()).thenReturn(Arrays.asList(car()));
		when(recommendationEngine.recommend(any(), any(RecommendationRequest.class))).thenReturn(Collections.emptyList());

		assertThatThrownBy(() -> recommendationService.recommend(request))
				.isInstanceOf(RecommendationUnavailableException.class)
				.hasMessage("No recommendations could be generated from the current catalog.");

		verify(queryRepository, never()).save(any(CarRecommendationQuery.class));
		verify(resultRepository, never()).saveAll(any());
	}

	private Car car() {
		return Car.builder()
				.make("Tata")
				.model("Punch")
				.variant("Top")
				.priceInLakhs(9.75)
				.bodyType("SUV")
				.fuelType("Petrol")
				.transmission("Manual")
				.mileage(20.1)
				.seatingCapacity(5)
				.safetyRating(5.0)
				.segment("Micro SUV")
				.imageUrl("image")
				.build();
	}
}
