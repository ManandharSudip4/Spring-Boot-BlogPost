package com.mstech.springblogpost.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mstech.springblogpost.model.LoginRequest;
import com.mstech.springblogpost.model.LoginResponse;
import com.mstech.springblogpost.security.JwtIssuer;
import com.mstech.springblogpost.security.UserPrincipal;
import com.mstech.springblogpost.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/auth/login")
  public LoginResponse login(@RequestBody @Validated LoginRequest request) {
    return authService.attemptLogin(request.getEmail(), request.getPassword());
  }
}
