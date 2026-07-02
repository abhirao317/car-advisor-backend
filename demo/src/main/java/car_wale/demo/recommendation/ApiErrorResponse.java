package car_wale.demo.recommendation;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiErrorResponse {

	private final LocalDateTime timestamp;
	private final int status;
	private final String error;
	private final List<String> messages;
}
