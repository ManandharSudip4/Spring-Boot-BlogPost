package com.mstech.springblogpost.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mstech.springblogpost.security.UserPrincipal;

@RestController
public class HomeController {

  @GetMapping("/")
  public String greetings() {
    return "Hola Mundo!";
  }

  @GetMapping("/secured")
  public String secured(@AuthenticationPrincipal UserPrincipal prinicipal) {
    return (
      "Successful Login as " +
      prinicipal.getEmail() +
      " User ID: " +
      prinicipal.getUserId()
    );
  }

  @GetMapping("/admin")
  public String admino(@AuthenticationPrincipal UserPrincipal principal) {
    return (
      "Hey Admino! with ADMIN ID: " + principal.getUserId() + ". What's up?"
    );
  }
}
