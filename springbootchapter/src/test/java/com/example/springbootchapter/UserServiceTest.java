package com.example.springbootchapter;

import com.example.springbootchapter.model.User;
import com.example.springbootchapter.repository.UserRepository;
import com.example.springbootchapter.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userServiceImpl;


    @Test
    void shouldReturnAllUsers() {
        List<User> mockUsers = List.of(new User(1L, "Alice"));
        when(userRepository.findAll()).thenReturn(mockUsers);

        List<User> result = userServiceImpl.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getName());

    }

}
