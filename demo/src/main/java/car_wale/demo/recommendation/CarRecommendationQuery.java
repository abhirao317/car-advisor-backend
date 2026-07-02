package car_wale.demo.recommendation;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
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
public class CarRecommendationQuery {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 2000)
	private String queryText;

	private Double budgetMin;
	private Double budgetMax;

	@Column(length = 60)
	private String bodyType;

	@Column(length = 60)
	private String fuelType;

	@Column(nullable = false)
	private LocalDateTime createdAt;

	@PrePersist
	void beforeCreate() {
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
	}
}
