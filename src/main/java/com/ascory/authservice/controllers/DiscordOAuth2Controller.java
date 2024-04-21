package com.ascory.authservice.controllers;

import com.ascory.authservice.requests.OAuth2Request;
import com.ascory.authservice.responses.AuthenticationResponse;
import com.ascory.authservice.services.DiscordOAuth2Service;
import com.ascory.authservice.services.OAuth2ServiceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/auth/discord")
@RequiredArgsConstructor
class DiscordOAuth2Controller implements OAuth2Controller {

    private final OAuth2ServiceContext oAuth2ServiceContext;
    private final DiscordOAuth2Service discordOAuth2Service;

    @PostMapping("/authenticate")
    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody OAuth2Request oAuth2Request) {
        // Реализация метода authenticate
        oAuth2ServiceContext.setOAuth2ServiceStrategy(discordOAuth2Service);
        return ResponseEntity.ok(oAuth2ServiceContext.authenticate(oAuth2Request.getCode()));
    }

    @PostMapping("/register")
    @Override
    public ResponseEntity<AuthenticationResponse> register(@RequestBody OAuth2Request oAuth2Request) {
        oAuth2ServiceContext.setOAuth2ServiceStrategy(discordOAuth2Service);
        return ResponseEntity.ok(oAuth2ServiceContext.register(oAuth2Request.getCode()));
    }

    @PostMapping("/add-verification")
    @Override
    public ResponseEntity<?> addVerification(
            @RequestBody OAuth2Request oAuth2Request,
            @AuthenticationPrincipal Principal principal) {
        oAuth2ServiceContext.setOAuth2ServiceStrategy(discordOAuth2Service);
        oAuth2ServiceContext.addVerification(oAuth2Request.getCode(), principal);
        return ResponseEntity.ok().build();
    }

}
