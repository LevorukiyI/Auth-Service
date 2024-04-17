package com.ascory.authservice.services;

import com.ascory.authservice.exceptions.UserNotFoundException;
import com.ascory.authservice.models.EmailPassVerificationTokenEntity;
import com.ascory.authservice.models.User;
import com.ascory.authservice.repositories.UserRepository;
import com.ascory.authservice.requests.EmailPassAuthenticateRequest;
import com.ascory.authservice.requests.EmailPassRegisterRequestEntity;
import com.ascory.authservice.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@RequiredArgsConstructor
@Service
public class EmailPassAuthService{

    private final UserRepository userRepository;
    private final AuthenticationHandler authenticationHandler;
    private final AuthenticationManager authenticationManager;
    private final RegistrationValidator registrationValidator;
    private final EmailVerificationTokenService emailVerificationTokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationResponse authenticate(
            EmailPassAuthenticateRequest emailPassAuthenticateRequest){
        User user = userRepository.findByEmail(emailPassAuthenticateRequest.getEmail())
                .orElseThrow(()-> new UserNotFoundException("User with such email not exists"));
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        emailPassAuthenticateRequest.getPassword()
                )
        );
        if(!user.getUsername().equals((String) authentication.getPrincipal())){
            throw new BadCredentialsException("Bad credentials, password doesn't matches");
        }
        return authenticationHandler.authenticateAndGetAuthenticationResponse(user);
    }

    public void createEmailVerificationToken(
            EmailPassRegisterRequestEntity emailPassRegisterRequestEntity,
            Principal principal) {//add principal as null, if you creating new user
        registrationValidator.validateEmailPassRegisterRequest(emailPassRegisterRequestEntity);

        EmailPassVerificationTokenEntity emailVerificationToken =
                emailVerificationTokenService.createToken(emailPassRegisterRequestEntity, Long.valueOf(principal.getName()));
        emailVerificationTokenService.sendVerificationEmail(
                emailPassRegisterRequestEntity.getEmail(), emailVerificationToken.getToken());
    }

    public void confirmEmailVerificationByToken(String token){
        EmailPassVerificationTokenEntity verificationTokenEntity =
                emailVerificationTokenService.getEmailPassVerificationTokenEntity(token);
        EmailPassRegisterRequestEntity registerRequestEntity = verificationTokenEntity.getRegisterRequestEntity();

        User user = userRepository.getUserById(verificationTokenEntity.getPrincipal());
        this.setEmailPassToUser(registerRequestEntity, user);
        userRepository.save(user);

        emailVerificationTokenService.deleteTokens(verificationTokenEntity, registerRequestEntity);
    }

    public void registerUserByToken(String token){
        EmailPassVerificationTokenEntity verificationTokenEntity =
                emailVerificationTokenService.getEmailPassVerificationTokenEntity(token);
        EmailPassRegisterRequestEntity registerRequestEntity = verificationTokenEntity.getRegisterRequestEntity();

        UserFactory userFactory = new UserFactory(passwordEncoder);
        User user = userFactory.createUser(registerRequestEntity);
        userRepository.save(user);

        emailVerificationTokenService.deleteTokens(verificationTokenEntity, registerRequestEntity);
    }

    public void setEmailPassToUser(EmailPassRegisterRequestEntity registerRequestEntity, User user){
        user.setEmail(registerRequestEntity.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestEntity.getPassword()));//#TODO шифровать пароль перед установкой
    }

    public void addVerification() {

    }
}
