package com.example.springbootchapter;

import com.example.springbootchapter.model.User;
import com.example.springbootchapter.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

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

    @BeforeEach
    void init() {
        userRepository.deleteAll();
        userRepository.saveAll(List.of(
                new User(null, "alice", "alice@email.com", "password123", "Alice", "Smith"),
                new User(null, "bob", "bob@email.com", "password123", "Bob", "Brown")
        ));
    }

    @Test
    void shouldReturnAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].username", is("alice")));
    }

    @Test
    void shouldGetUserById() throws Exception {
        Long userId = userRepository.findByUsername("alice").getId();

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username", is("alice")));
    }

    @Test
    void shouldCreateUser() throws Exception {
        User newUser = new User(null, "charlie", "charlie@email.com", "password123", "Charlie", "White");

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(content().string("User created successfully"));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        User existing = userRepository.findByUsername("bob");
        existing.setFirstName("Robert");

        mockMvc.perform(put("/users/" + existing.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(existing)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", is("Robert")));
    }

    @Test
    void shouldDeleteUser() throws Exception {
        Long userId = userRepository.findByUsername("alice").getId();

        mockMvc.perform(delete("/users/" + userId))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isNotFound());
    }
}
