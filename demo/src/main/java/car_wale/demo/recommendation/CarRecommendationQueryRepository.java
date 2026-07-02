package car_wale.demo.recommendation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRecommendationQueryRepository extends JpaRepository<CarRecommendationQuery, Long> {

	List<CarRecommendationQuery> findTop10ByOrderByCreatedAtDesc();
}
