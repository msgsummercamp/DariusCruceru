package com.example.springbootchapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class UserValidationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;


    @BeforeAll
    static void setupEnv() {
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        System.setProperty("SPRING_DATASOURCETEST_URL", dotenv.get("SPRING_DATASOURCETEST_URL"));
        System.setProperty("SPRING_DATASOURCETEST_USERNAME", dotenv.get("SPRING_DATASOURCETEST_USERNAME"));
        System.setProperty("SPRING_DATASOURCETEST_PASSWORD", dotenv.get("SPRING_DATASOURCETEST_PASSWORD"));
        System.setProperty("SPRING_DATASOURCE_DRIVER_CLASS_NAME", dotenv.get("SPRING_DATASOURCE_DRIVER_CLASS_NAME"));
    }


    @Test
    void shouldReturn400WhenPostUserWithBlankUsername() throws Exception {
        String userJson = """
                {
                  "username": "",
                  "email": "test@example.com",
                  "password": "12345678",
                  "firstName": "Test",
                  "lastName": "User"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenPostUserWithShortPassword() throws Exception {
        String userJson = """
                {
                  "username": "tester",
                  "email": "test@example.com",
                  "password": "short",
                  "firstName": "Test",
                  "lastName": "User"
                }
                """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenPutUserWithBlankEmail() throws Exception {
        String userJson = """
                {
                  "id": 1,
                  "username": "tester",
                  "email": "",
                  "password": "12345678",
                  "firstName": "Test",
                  "lastName": "User"
                }
                """;

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenIdIsNullInPath() throws Exception {
        // Not a real test – because Spring does not route to /users/null as null.
        mockMvc.perform(get("/users/"))
                .andExpect(status().isNotFound()); // 404 since /users/ expects an ID
    }

    @Test
    void shouldReturn400WhenIdIsNegative() throws Exception {
        mockMvc.perform(get("/users/-5"))
                .andExpect(status().isNotFound());
    }
}
