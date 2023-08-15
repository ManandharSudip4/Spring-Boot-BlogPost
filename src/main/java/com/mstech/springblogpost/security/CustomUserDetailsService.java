package com.mstech.springblogpost.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mstech.springblogpost.repositories.UserEntityRepository;
import com.mstech.springblogpost.service.UserService;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

  private final UserService userService;

  private final UserEntityRepository userEntityRepository;

  @Override
  public UserDetails loadUserByUsername(String username)
    throws UsernameNotFoundException {
    var user = userEntityRepository.findByEmail(username).orElseThrow();
    // var user = userService.findByEmail(username).orElseThrow();

    return UserPrincipal
      .builder()
      .userId(user.getId())
      .email(user.getEmail())
      .password(user.getPassword())
      .authorities(List.of(new SimpleGrantedAuthority(user.getRole())))
      .build();
  }
}
