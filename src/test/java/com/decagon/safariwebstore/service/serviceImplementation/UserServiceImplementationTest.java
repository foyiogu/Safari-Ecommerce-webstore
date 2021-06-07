package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.repository.UserRepository;
import com.decagon.safariwebstore.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

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

    @Test
    void shouldFindUserByEmail() {
        final String email = "user1@gmail.com";
        final User users = new User("austin", "sam", "austin@gmail.com", "male", "27-11-1999",  "password");
        given(userRepository.findByEmail(email)).willReturn(Optional.of(users));
        final Optional<User> expected = userService.getUserByEmail(email);
        assertThat(expected).isNotNull();
    }

    @Test
    void shouldFindPasswordResetToken() {
        final String token = "i3ii3rjif31iof2fbougfqe";
        final User users = new User("austin", "sam", "austin@gmail.com", "male", "27-11-1999","password");
        given(userRepository.findByPasswordResetToken(token)).willReturn(Optional.of(users));
        final Optional<User> expected  = userService.findUserByResetToken(token);
        System.out.println(expected.get().getPasswordResetToken());
        assertThat(expected).isNotNull();

    }

    @Test
    void deactivateResetPasswordToken(){

        final User users = new User
                ("austin", "sam", "austin@gmail.com", "male", "27-11-1999", null);
        Date presentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 1);
        String tokenExpiryDate = dateFormat.format(calendar.getTime());
        users.setPasswordResetExpireDate(tokenExpiryDate);


        userService.deactivateResetPasswordToken();
        assertThat(users.getPasswordResetExpireDate()).isNotNull();

        System.out.println(users.getPasswordResetToken());
        System.out.println(users.getPasswordResetExpireDate());
    }
}