package com.mstech.springblogpost.allfunctions;

import com.mstech.springblogpost.security.JwtDecoder;
import com.mstech.springblogpost.security.JwtToPrinicipalConverter;
import com.mstech.springblogpost.security.UserPrincipal;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Component
public class GetUserFromJwt {

  private final HttpServletRequest request;
  private final JwtDecoder jwtDecoder;
  private final JwtToPrinicipalConverter jwtToPrinicipalConverter;

  public UserPrincipal getCurrentActiveUser() {
    UserPrincipal myUser = extractTokenFromRequest(request)
      .map(jwtDecoder::decode)
      .map(jwtToPrinicipalConverter::convert)
      .orElseThrow(() -> new IllegalStateException("User Doesnot exists."));

    return myUser;
  }

  private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
    var token = request.getHeader("Authorization");
    if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
      return Optional.of(token.substring(7));
    }
    return Optional.empty();
  }
}
