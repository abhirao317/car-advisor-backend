package car_wale.demo.recommendation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendationResultRepository extends JpaRepository<RecommendationResult, Long> {

	List<RecommendationResult> findByQueryIdOrderByRankAsc(Long queryId);
}
