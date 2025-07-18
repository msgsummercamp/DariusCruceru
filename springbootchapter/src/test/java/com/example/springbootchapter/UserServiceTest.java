package com.example.springbootchapter;

import com.example.springbootchapter.model.User;
import com.example.springbootchapter.repository.UserRepository;
import com.example.springbootchapter.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userServiceImpl;


    @Test
    void shouldReturnAllUsers() {
        User alice = new User(
                1L,
                "alice",
                "alice@gmail.com",
                "12345678",
                "Alice",
                "Wonderland"
        );

        List<User> mockUsers = List.of(alice);
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userServiceImpl.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("alice", result.get(0).getUsername());
    }


}
