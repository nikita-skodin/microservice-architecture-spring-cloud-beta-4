package com.skodin.controllers;

import com.skodin.services.AuthenticationService;
import com.skodin.services.JwtService;
import com.skodin.util.auth.AuthenticationRequest;
import com.skodin.util.auth.AuthenticationResponse;
import com.skodin.util.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController extends MainController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request,
            BindingResult bindingResult
    ) {
        return ResponseEntity.ok(authenticationService.register(request, bindingResult));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(
            @RequestBody String refreshToken
    ) {
        return ResponseEntity.ok(authenticationService.refresh(refreshToken));
    }

    @PostMapping("/enable/{code}")
    public ResponseEntity<Boolean> enable(
            @PathVariable String code) {
        return ResponseEntity.ok(authenticationService.enable(code));
    }

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validate(
            @RequestParam String token
    ) {
        return ResponseEntity.ok(jwtService.isTokenValid(token));
    }

}

