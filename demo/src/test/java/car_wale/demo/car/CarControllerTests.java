package car_wale.demo.car;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void returnsSeededCarCatalog() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/cars"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(greaterThan(0))))
				.andExpect(jsonPath("$[0].id").isNumber())
				.andExpect(jsonPath("$[0].make").isString())
				.andExpect(jsonPath("$[0].model").isString())
				.andExpect(jsonPath("$[0].priceInLakhs").isNumber())
				.andReturn();

		JsonNode cars = objectMapper.readTree(result.getResponse().getContentAsString());
		for (int index = 1; index < cars.size(); index++) {
			double previousPrice = cars.get(index - 1).get("priceInLakhs").asDouble();
			double currentPrice = cars.get(index).get("priceInLakhs").asDouble();

			assertThat(currentPrice).isGreaterThanOrEqualTo(previousPrice);
		}
	}

	@Test
	void returnsFilterOptionsFromCatalog() throws Exception {
		mockMvc.perform(get("/api/cars/filter-options"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.bodyTypes", hasSize(greaterThan(0))))
				.andExpect(jsonPath("$.fuelTypes", hasSize(greaterThan(0))));
	}
}
