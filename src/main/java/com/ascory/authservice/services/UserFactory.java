package com.ascory.authservice.services;

import com.ascory.authservice.models.OAuth2ServiceName;
import com.ascory.authservice.models.RegistrationData;
import com.ascory.authservice.models.User;
import com.ascory.authservice.requests.EmailPassRegisterRequestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserFactory {
    private final PasswordEncoder passwordEncoder;

    public User createUser(RegistrationData registrationData, String oAuth2Id, OAuth2ServiceName OAuth2ServiceName) {
        switch (OAuth2ServiceName){
            case DISCORD:
                return User.builder()
                        .discordId(oAuth2Id)
                        .build();
        }
        return null;//#TODO сделать норм фабрику
    }

    public User createUser(
            EmailPassRegisterRequestEntity emailPassRegisterRequestEntity
                           ){
        return User.builder()
                .email(emailPassRegisterRequestEntity.getEmail())
                .password(passwordEncoder.encode(emailPassRegisterRequestEntity.getPassword()))
                .build();
    }//#TODO сделать норм фабрику
}
