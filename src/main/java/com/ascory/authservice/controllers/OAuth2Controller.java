package com.ascory.authservice.controllers;

import com.ascory.authservice.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("auth")
interface OAuth2Controller {
    @PostMapping("/authenticate")
    ResponseEntity<AuthenticationResponse> authenticate(@RequestParam("code") String authenticationCode);

    @PostMapping("/register")
    ResponseEntity<AuthenticationResponse> register(@RequestParam("code") String authenticationCode);

    @PostMapping("/add-verification")
    ResponseEntity<?> addVerification(@RequestParam("code") String authenticationCode);//add new OAuth2 verification to the account
}
