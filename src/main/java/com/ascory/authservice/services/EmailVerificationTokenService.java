package com.ascory.authservice.services;

import com.ascory.authservice.models.EmailPassVerificationTokenEntity;
import com.ascory.authservice.repositories.EmailPassRegisterRequestRepository;
import com.ascory.authservice.repositories.EmailPassVerificationTokenRepository;
import com.ascory.authservice.repositories.UserRepository;
import com.ascory.authservice.requests.EmailPassRegisterRequestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationTokenService {
    private final EmailSender emailSender;
    private final EmailPassVerificationTokenRepository verificationTokenRepository;
    private final EmailPassRegisterRequestRepository registerRequestRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public EmailPassVerificationTokenEntity createToken(
            EmailPassRegisterRequestEntity registerRequestEntity,
            Long principal) {
        registerRequestRepository.save(registerRequestEntity);
        EmailPassVerificationTokenEntity emailVerificationToken = EmailPassVerificationTokenEntity.builder()
                .token(generateTokenString())
                .registerRequestEntity(registerRequestEntity)
                .principal(principal)
                .build();
        verificationTokenRepository.save(emailVerificationToken);
        return emailVerificationToken;
    }

    public EmailPassVerificationTokenEntity getEmailPassVerificationTokenEntity(String token) {
        //TODO сделать проверку на истекший строк годности токена
        return verificationTokenRepository
                .findByToken(token)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Token."));
    }

    public void deleteTokens(
            EmailPassVerificationTokenEntity verificationTokenEntity,
            EmailPassRegisterRequestEntity registerRequestEntity){
        verificationTokenRepository.delete(verificationTokenEntity);
        registerRequestRepository.delete(registerRequestEntity);
    }

    private String generateTokenString() {//TODO добавить защиту от совпавших UUID добавлением id последней записи или пользователя
        return UUID.randomUUID().toString();
    }

    public void sendVerificationEmail(String email, String token) {
        String subject = "Подтверждение электронной почты";
        //#TODO не забыть поменять ссылку
        String body = "Для подтверждения электронной почты перейдите по ссылке: http://127.0.0.1:8080/validation/email/verify?token=" + token;
        emailSender.sendEmail(email, subject, body);
    }
}
