package car_wale.demo.car;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarSummary {

	private final Long id;
	private final String make;
	private final String model;
	private final String variant;
	private final Double priceInLakhs;
	private final String bodyType;
	private final String fuelType;
	private final String transmission;
	private final Double mileage;
	private final Integer seatingCapacity;
	private final Double safetyRating;
	private final String segment;
	private final String imageUrl;

	public static CarSummary from(Car car) {
		return new CarSummary(
				car.getId(),
				car.getMake(),
				car.getModel(),
				car.getVariant(),
				car.getPriceInLakhs(),
				car.getBodyType(),
				car.getFuelType(),
				car.getTransmission(),
				car.getMileage(),
				car.getSeatingCapacity(),
				car.getSafetyRating(),
				car.getSegment(),
				car.getImageUrl()
		);
	}
}
