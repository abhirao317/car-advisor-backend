package car_wale.demo.recommendation;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class RecommendationControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void recommendsAndPersistsRankedCars() throws Exception {
		String requestBody = "{"
				+ "\"queryText\":\"I want a safe automatic SUV under 18 lakhs\","
				+ "\"budgetMin\":8,"
				+ "\"budgetMax\":18,"
				+ "\"bodyType\":\"SUV\","
				+ "\"fuelType\":\"Petrol\""
				+ "}";

		MvcResult result = mockMvc.perform(post("/api/ai/recommendations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.queryId").isNumber())
				.andExpect(jsonPath("$.results", hasSize(greaterThan(0))))
				.andExpect(jsonPath("$.results[0].rank").value(1))
				.andExpect(jsonPath("$.results[0].car.bodyType").value("SUV"))
				.andReturn();

		String response = result.getResponse().getContentAsString();
		int queryId = objectMapper.readTree(response).get("queryId").asInt();

		mockMvc.perform(get("/api/ai/recommendations/" + queryId))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.queryId").value(queryId))
				.andExpect(jsonPath("$.results", hasSize(greaterThan(0))));

		mockMvc.perform(get("/api/ai/recommendations"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(greaterThan(0))))
				.andExpect(jsonPath("$[0].id").isNumber())
				.andExpect(jsonPath("$[0].queryText").isString());
	}

	@Test
	void trimsRecommendationRequestBeforeSaving() throws Exception {
		String requestBody = "{"
				+ "\"queryText\":\"  I want a safe automatic SUV under 18 lakhs  \","
				+ "\"budgetMin\":8,"
				+ "\"budgetMax\":18,"
				+ "\"bodyType\":\"  SUV  \","
				+ "\"fuelType\":\"  Petrol  \""
				+ "}";

		mockMvc.perform(post("/api/ai/recommendations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.queryText").value("I want a safe automatic SUV under 18 lakhs"))
				.andExpect(jsonPath("$.bodyType").value("SUV"))
				.andExpect(jsonPath("$.fuelType").value("Petrol"));
	}

	@Test
	void rejectsInvalidBudgetRange() throws Exception {
		String requestBody = "{"
				+ "\"queryText\":\"I need a car recommendation\","
				+ "\"budgetMin\":20,"
				+ "\"budgetMax\":10"
				+ "}";

		mockMvc.perform(post("/api/ai/recommendations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasItem("Maximum budget must be greater than or equal to minimum budget.")));
	}

	@Test
	void returnsStructuredErrorForMalformedJson() throws Exception {
		mockMvc.perform(post("/api/ai/recommendations")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"queryText\":\"I need a safe SUV\""))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.status").value(400))
				.andExpect(jsonPath("$.messages", hasItem("Request body must be valid JSON.")));
	}

	@Test
	void rejectsTooShortQueryText() throws Exception {
		String requestBody = "{"
				+ "\"queryText\":\"short\""
				+ "}";

		mockMvc.perform(post("/api/ai/recommendations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasItem("Buying situation must be at least 10 characters.")));
	}

	@Test
	void rejectsOverlyLongQueryText() throws Exception {
		String requestBody = "{"
				+ "\"queryText\":\"" + repeated("a", 2001) + "\""
				+ "}";

		mockMvc.perform(post("/api/ai/recommendations")
						.contentType(MediaType.APPLICATION_JSON)
						.content(requestBody))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.messages", hasItem("Buying situation must be 2000 characters or fewer.")));
	}

	@Test
	void returnsStructuredErrorForMissingRecommendation() throws Exception {
		mockMvc.perform(get("/api/ai/recommendations/999999"))
				.andExpect(status().isNotFound())
				.andExpect(jsonPath("$.status").value(404))
				.andExpect(jsonPath("$.messages", hasItem("Recommendation query 999999 was not found.")));
	}

	private String repeated(String value, int times) {
		StringBuilder builder = new StringBuilder();
		for (int index = 0; index < times; index++) {
			builder.append(value);
		}
		return builder.toString();
	}
}
