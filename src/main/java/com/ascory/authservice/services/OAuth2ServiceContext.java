package com.ascory.authservice.services;

import com.ascory.authservice.exceptions.OAuth2AccountAlreadyExistsException;
import com.ascory.authservice.exceptions.UserAlreadyExistsException;
import com.ascory.authservice.models.*;
import com.ascory.authservice.repositories.UserRepository;
import com.ascory.authservice.responses.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class OAuth2ServiceContext{//TODO USE FACTORY

    private final RegistrationValidator registrationValidator;
    private OAuth2ServiceStrategy oAuth2ServiceStrategy;
    private final UserRepository userRepository;
    private final AuthenticationHandler authenticationHandler;

    public void setOAuth2ServiceStrategy(OAuth2ServiceStrategy oAuth2ServiceStrategy){
        this.oAuth2ServiceStrategy = oAuth2ServiceStrategy;
    }

    public AuthenticationResponse authenticate(String code) {
        String accessToken = oAuth2ServiceStrategy.getAccessToken(code);
        String oAuth2Id = oAuth2ServiceStrategy.getOAuth2Id(accessToken);
        User user = oAuth2ServiceStrategy.getUserByOAuth2Id(oAuth2Id);
        return authenticationHandler.authenticateAndGetAuthenticationResponse(user);
    }

    public AuthenticationResponse register(String code) {
        //registrationValidator.validateRegistrationData(registrationData);
        String accessToken = oAuth2ServiceStrategy.getAccessToken(code);
        String oAuth2Id = oAuth2ServiceStrategy.getOAuth2Id(accessToken);
        if(oAuth2ServiceStrategy.existsByOAuth2Id(oAuth2Id)){
            throw new UserAlreadyExistsException();
        }
        User user = new User();
        oAuth2ServiceStrategy.setOAuth2IdToUser(user, oAuth2Id);
        userRepository.save(user);
        return authenticationHandler.authenticateAndGetAuthenticationResponse(user);
    }

    public void addVerification(String code, Principal userPrincipal) {
        String accessToken = oAuth2ServiceStrategy.getAccessToken(code);
        String oAuth2Id = oAuth2ServiceStrategy.getOAuth2Id(accessToken);
        if(oAuth2ServiceStrategy.existsByOAuth2Id(oAuth2Id)){
            throw new OAuth2AccountAlreadyExistsException("Данный OAuth2 аккаунт уже занят другой учетной записью в нашем сервисе");
        }
        User user = userRepository.getUserById(Long.valueOf(userPrincipal.getName()));
        oAuth2ServiceStrategy.setOAuth2IdToUser(user, oAuth2Id);
        userRepository.save(user);
    }
}
