package car_wale.demo.car;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

	private final CarRepository carRepository;

	@GetMapping
	public List<CarSummary> findAll() {
		return carRepository.findAll().stream()
				.sorted(Comparator.comparing(Car::getPriceInLakhs)
						.thenComparing(Car::getMake)
						.thenComparing(Car::getModel))
				.map(CarSummary::from)
				.collect(Collectors.toList());
	}

	@GetMapping("/filter-options")
	public CarFilterOptions findFilterOptions() {
		List<Car> cars = carRepository.findAll();
		List<String> bodyTypes = cars.stream()
				.map(Car::getBodyType)
				.filter(StringUtils::hasText)
				.distinct()
				.sorted(Comparator.naturalOrder())
				.collect(Collectors.toList());
		List<String> fuelTypes = cars.stream()
				.map(Car::getFuelType)
				.filter(StringUtils::hasText)
				.distinct()
				.sorted(Comparator.naturalOrder())
				.collect(Collectors.toList());

		return new CarFilterOptions(bodyTypes, fuelTypes);
	}
}
