package car_wale.demo.recommendation;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {

	@NotBlank(message = "Tell us what kind of car decision you are trying to make.")
	@Size(min = 10, message = "Buying situation must be at least 10 characters.")
	@Size(max = 2000, message = "Buying situation must be 2000 characters or fewer.")
	private String queryText;

	@DecimalMin(value = "0.0", inclusive = true, message = "Minimum budget cannot be negative.")
	private Double budgetMin;

	@DecimalMin(value = "0.0", inclusive = true, message = "Maximum budget cannot be negative.")
	private Double budgetMax;

	@Size(max = 60, message = "Body type must be 60 characters or fewer.")
	private String bodyType;

	@Size(max = 60, message = "Fuel type must be 60 characters or fewer.")
	private String fuelType;

	@AssertTrue(message = "Maximum budget must be greater than or equal to minimum budget.")
	public boolean isBudgetRangeValid() {
		return budgetMin == null || budgetMax == null || budgetMax >= budgetMin;
	}
}
