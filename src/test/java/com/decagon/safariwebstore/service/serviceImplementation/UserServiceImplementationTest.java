package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceImplementationTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImplementation userService;

    @Test
    void contextLoads() {
    }

    @Test
    void saveUserTest() {
        User user = new User("austin", "sam", "austin@gmail.com", "male", "27-11-1999",  "password");
        given(userRepository.save(user)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));
        User savedUser = userService.saveUser(user);
        assertThat(savedUser).isNotNull();
        verify(userRepository).save(any(User.class));
    }

    @Test
    void existsByMailTest() {
        String email = "austin@gmail.com";
        new User("austin", "sam", "austin@gmail.com", "male", "27-11-1999",  "password");

        given(userRepository.existsByEmail(email)).willReturn(true);
        boolean expected = userService.existsByMail(email);
        assert(expected);
    }
}