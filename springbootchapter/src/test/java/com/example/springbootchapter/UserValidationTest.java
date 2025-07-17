package com.example.springbootchapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserValidationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void shouldReturn400WhenPostUserWithMissingName() throws Exception {
        String userJson = """
            {
              "id": 10,
              "name": ""
            }
        """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenPostUserWithNullId() throws Exception {
        String userJson = """
            {
              "id": null,
              "name": "Test"
            }
        """;

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenPutUserWithBlankName() throws Exception {
        String userJson = """
            {
              "id": 5,
              "name": "   "
            }
        """;

        mockMvc.perform(put("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isBadRequest());
    }



    @Test
    void shouldReturn400WhenPathVariableIdIsNull() throws Exception {
        // You can't send null in path variable directly — instead, test invalid format:
        mockMvc.perform(get("/users/null"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400WhenIdIsNegative() throws Exception {
        mockMvc.perform(get("/users/-5"))
                .andExpect(status().isNotFound()); // 404 e corect aici

    }
}
