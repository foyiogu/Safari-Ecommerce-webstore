package com.decagon.safariwebstore.service.serviceImplementation;

import com.decagon.safariwebstore.exceptions.BadRequestException;
import com.decagon.safariwebstore.exceptions.ResourceNotFoundException;
import com.decagon.safariwebstore.model.ERole;
import com.decagon.safariwebstore.model.Role;
import com.decagon.safariwebstore.model.User;
import com.decagon.safariwebstore.payload.request.auth.RegisterUser;
import com.decagon.safariwebstore.payload.response.Response;
import com.decagon.safariwebstore.payload.response.auth.ResetPassword;
import com.decagon.safariwebstore.repository.RoleRepository;
import com.decagon.safariwebstore.repository.UserRepository;
import com.decagon.safariwebstore.service.UserService;
import com.decagon.safariwebstore.utils.DateUtils;
import com.decagon.safariwebstore.utils.mailService.MailService;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MailService mailService;
    private final RoleRepository roleRepository;


    @Autowired
    public UserServiceImplementation(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, MailService mailService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailService = mailService;
        this.roleRepository = roleRepository;
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
    @Override
    public Optional<User> findUserByResetToken(String resetToken) {
        return userRepository.findByPasswordResetToken(resetToken);
    }
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    /**
     * This method is called by the scheduler every 1 minutes
     * to check if the time to invalidate the token has reached limit
     * */
    @Override
    public void deactivateResetPasswordToken() {
        List<User> accountsList = userRepository.findAllByPasswordResetTokenIsNotNull();
        accountsList.forEach(account -> {
            String expireDate = account.getPasswordResetExpireDate();
            String presentDate = DateUtils.getCurrentTime();
            int actionDelete = presentDate.compareTo(expireDate);
            if(actionDelete > 0 || actionDelete == 0) {
                account.setPasswordResetExpireDate(null);
                account.setPasswordResetToken(null);
                userRepository.save(account);
            }
        });
    }
    /**
     * Sends an email to the customer with a url link to reset password
     * the url link will be received in the frontend
     * @param request
     * @param accountEmail
     * @return object
     * */


    @Override
    public ResponseEntity<Response> userForgotPassword(HttpServletRequest request, String accountEmail)
    {
        // Lookup user in database by e-mail
        Optional<User> userOptional = userRepository.findByEmail(accountEmail);
        //response handler
        Response responseHandler = new Response();

        Role roleUser = roleRepository.findByName(ERole.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Error: Role is not found."));

        if(userOptional.isEmpty()) {
            responseHandler.setStatus(404);
            responseHandler.setMessage("We couldn't find an account with that e-mail address.");
            return new ResponseEntity<>(responseHandler, HttpStatus.NOT_FOUND);
        }

        Role userRole = userOptional.get().getRoles().get(0);
        if(userRole != roleUser){
            responseHandler.setStatus(401);
            responseHandler.setMessage("You don't have access to this link");
            return new ResponseEntity<>(responseHandler, HttpStatus.UNAUTHORIZED);
        }
        //process email
        try {
            // Generate random 36-character string token for reset password
            User user = userOptional.get();
            user.setPasswordResetToken(UUID.randomUUID().toString());
            //24hours expiry date for token
            String tokenExpiryDate = DateUtils.passwordResetExpiryTimeLimit();
            user.setPasswordResetExpireDate(tokenExpiryDate);

            String appUrl = request.getScheme() + "://" + request.getServerName();
            String subject = "Customer Reset Password";
            String mailBody = "To reset your password, click the link below:\n"
                    + appUrl + "/reset?token="
                    + user.getPasswordResetToken();
            mailService.sendMessage(user.getEmail(), subject, mailBody);
            // Save token and expiring date to database
            userRepository.save(user);
            responseHandler.setStatus(200);
            responseHandler.setMessage("Successfully sent email");
        }
        catch (UnirestException e){
            System.out.println("Error sending email:\n\tError message:"+e.getMessage());
        }
        return new ResponseEntity<>(responseHandler, HttpStatus.OK);
    }
    /**
     * This method check the validity of the token sent and also validates passwords(password and confirm password)
     * before saving it
     * @param resetPassword
     * @return response
     * */
    public ResponseEntity<Response> userResetPassword(ResetPassword resetPassword){

        //find the user by the token
        Optional<User> userOptional = userRepository.findByPasswordResetToken(resetPassword.getToken());

        String password = resetPassword.getPassword();
        String confirmPassword = resetPassword.getConfirmPassword();

        //response handler
        Response responseHandler = new Response();

        if (userOptional.isEmpty()){
            responseHandler.setStatus(400);
            responseHandler.setMessage("Oops!  This is an invalid password reset link.");
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }
        User user = userOptional.get();

        if(!password.equals(confirmPassword)){
            responseHandler.setStatus(400);
            responseHandler.setMessage("Passwords does not match");
            return new ResponseEntity<>(responseHandler, HttpStatus.BAD_REQUEST);
        }

        //set the encrypted password
        user.setPassword(bCryptPasswordEncoder.encode(password));
        // Set the reset token to null so it cannot be used again
        user.setPasswordResetToken(null);
        //set the reset passwordRestExpireDate to null
        user.setPasswordResetExpireDate(null);
        try {
            // Save user
            userRepository.save(user);
            responseHandler.setStatus(201);
            responseHandler.setMessage("You have successfully reset your password. You can now login.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseHandler, HttpStatus.CREATED);
    }

    @Override
    public User findUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()) throw new ResourceNotFoundException("Incorrect parameter; email " + email + " does not exist");
        return user.get();
    }

}
