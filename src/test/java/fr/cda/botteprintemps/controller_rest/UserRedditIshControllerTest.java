package fr.cda.botteprintemps.controller_rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserRedditIshControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "mathilde.rey@hotmail.fr")
    public void testAccessSecuredRoute() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/user"));

        result.andExpect(status().is2xxSuccessful())
                .andExpect(authenticated());
    }

    @Test
    public void testUnauthorizedSecuredRoute() throws Exception {
        ResultActions result = mockMvc.perform(
                get("/api/user"));

        result.andExpect(status().isForbidden());
    }

}
