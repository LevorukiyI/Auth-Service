package com.ascory.authservice.controllers;

import com.ascory.authservice.requests.OAuth2Request;
import com.ascory.authservice.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController("auth")
interface OAuth2Controller {
    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestBody OAuth2Request oAuth2Request);

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestBody OAuth2Request oAuth2Request);

    //this method adds new OAuth2 verification to the account
    @PostMapping("/add-verification")
    ResponseEntity<?> addVerification(@RequestBody OAuth2Request oAuth2Request,
                                      @AuthenticationPrincipal Principal principal);
}
