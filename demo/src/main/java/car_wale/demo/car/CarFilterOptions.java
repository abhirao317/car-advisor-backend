package car_wale.demo.car;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarFilterOptions {

	private final List<String> bodyTypes;
	private final List<String> fuelTypes;
}
