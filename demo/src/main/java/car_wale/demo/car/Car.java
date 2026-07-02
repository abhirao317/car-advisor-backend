package car_wale.demo.car;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 80)
	private String make;

	@Column(nullable = false, length = 80)
	private String model;

	@Column(nullable = false, length = 120)
	private String variant;

	@Column(nullable = false)
	private Double priceInLakhs;

	@Column(nullable = false, length = 60)
	private String bodyType;

	@Column(nullable = false, length = 60)
	private String fuelType;

	@Column(nullable = false, length = 60)
	private String transmission;

	@Column(nullable = false)
	private Double mileage;

	@Column(nullable = false)
	private Integer seatingCapacity;

	@Column(nullable = false)
	private Double safetyRating;

	@Column(nullable = false, length = 80)
	private String segment;

	@Column(nullable = false, length = 1000)
	private String imageUrl;
}
