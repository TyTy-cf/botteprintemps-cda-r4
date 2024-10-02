package fr.cda.botteprintemps.controller_rest;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginFormFailed() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getLoginJson("toto@gmail.com", "1234")));

        result.andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoginFormSuccess() throws Exception {
        ResultActions result = mockMvc.perform(
                post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getLoginJson("mathilde.rey@hotmail.fr", "12345")));

        result.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.token").exists());
    }

    private String getLoginJson(String email, String password) {
        return "{" +
                "\"email\":\"" + email + "\"," +
                "\"password\":\"" + password + "\"" +
            "}";
    }

}
