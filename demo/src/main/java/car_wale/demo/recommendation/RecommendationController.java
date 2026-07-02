package car_wale.demo.recommendation;

import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/recommendations", "/api/ai/recommendations"})
@RequiredArgsConstructor
public class RecommendationController {

	private final RecommendationService recommendationService;

	@PostMapping
	public RecommendationResponse recommend(@Valid @RequestBody RecommendationRequest request) {
		return recommendationService.recommend(request);
	}

	@GetMapping
	public List<RecommendationQuerySummary> findRecentQueries() {
		return recommendationService.findRecentQueries();
	}

	@GetMapping("/{queryId}")
	public RecommendationResponse findByQueryId(@PathVariable Long queryId) {
		return recommendationService.findByQueryId(queryId);
	}
}
