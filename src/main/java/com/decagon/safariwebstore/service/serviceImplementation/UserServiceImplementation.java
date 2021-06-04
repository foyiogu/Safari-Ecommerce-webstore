package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.BadRequestException;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.auth.RegisterUser;
import com.decagon.safariwebstore.repository.UserRepository;
import com.decagon.safariwebstore.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean existsByMail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public User registration(RegisterUser registerUser){
        if(userRepository.existsByEmail(registerUser.getEmail())) {
            throw new BadRequestException("Error: Email is already taken!");
        }
        if(!(registerUser.getPassword().equals(registerUser.getConfirmPassword()))){
            throw new BadRequestException("Error: Password does not match");
        }

        return new User(
                registerUser.getFirstName(),
                registerUser.getLastName(),
                registerUser.getEmail(),
                registerUser.getGender(),
                registerUser.getDateOfBirth(),
                bCryptPasswordEncoder.encode(registerUser.getPassword())
        );
    }


}
