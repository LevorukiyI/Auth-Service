package com.ascory.authservice.controllers;


import com.ascory.authservice.requests.EmailPassAuthenticateRequest;
import com.ascory.authservice.requests.EmailPassRegisterRequestEntity;
import com.ascory.authservice.requests.VerifyEmailPassTokenRequest;
import com.ascory.authservice.responses.AuthenticationResponse;
import com.ascory.authservice.services.EmailPassAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth/email-pass")
@RequiredArgsConstructor
class EmailPassAuthController {

    private final EmailPassAuthService emailPassAuthService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody EmailPassAuthenticateRequest emailPassAuthenticateRequest) {
        return ResponseEntity.ok(emailPassAuthService.authenticate(emailPassAuthenticateRequest));
    }

    @PostMapping("/get-register-token")
    public ResponseEntity<?> getRegisterTokenOnEmail(
            @RequestBody EmailPassRegisterRequestEntity emailPassRegisterRequestEntity) {
        emailPassAuthService.createEmailVerificationToken(emailPassRegisterRequestEntity, null);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/get-verification-token")
    public ResponseEntity<?> getAddVerificationTokenOnEmail(
            @RequestBody EmailPassRegisterRequestEntity emailPassRegisterRequestEntity,
            @AuthenticationPrincipal Principal principal) {
        emailPassAuthService.createEmailVerificationToken(emailPassRegisterRequestEntity, principal);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify-token")
    public ResponseEntity<?> verifyToken(
            @RequestBody VerifyEmailPassTokenRequest verifyEmailPassTokenRequest) {
        emailPassAuthService.verifyEmailPassToken(verifyEmailPassTokenRequest.getToken());
        return ResponseEntity.ok().build();
    }
}