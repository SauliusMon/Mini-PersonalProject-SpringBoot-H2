package lt.techin.from.scratch.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@MockBeans({@MockBean(RoomController.class), @MockBean(RoomService.class), @MockBean(RoomRepository.class)})
@AutoConfigureMockMvc
class EntityControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getInfo_returnsCorrectVersion() throws Exception {
        var mvcResult = mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/api/v1/entities/info")
                )
                .andExpect(status().isOk())
                .andReturn();

        var resulString = mvcResult.getResponse().getContentAsString();

        assertThat(resulString)
                .isEqualTo("0.2");
    }

}



