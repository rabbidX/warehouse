package dmitry.garyanov.warehouse;

import dmitry.garyanov.warehouse.model.DocumentRow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@Profile("test")
class
WarehouseApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Test
	void contextLoads() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users")).andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void saveRows() {

		DocumentRow documentRow = new DocumentRow();
		documentRow.setDate()
		mockMvc.perform(MockMvcRequestBuilders.post("/api/rows/").content()).
	}

}
