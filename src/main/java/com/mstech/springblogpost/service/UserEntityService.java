package com.mstech.springblogpost.service;

import com.mstech.springblogpost.entity.UserEntity;
import com.mstech.springblogpost.repositories.UserEntityRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEntityService {

  private final UserEntityRepository userEntityRepository;

  public void registerNewUser(UserEntity user) {
    Optional<UserEntity> oldUser = userEntityRepository.findByEmail(
      user.getEmail()
    );
    if (oldUser.isPresent()) {
    //   System.out.println("This is not happening!");
      throw new IllegalStateException("Email already taken");
    } else {
    //   System.out.println("This is happening!");
    //   System.out.println(
    //     "ID: " +
    //     user.getId() +
    //     " Email: " +
    //     user.getEmail() +
    //     " Password: " +
    //     user.getPassword() +
    //     "  Role: " +
    //     user.getRole()
    //   );
      userEntityRepository.save(user);
    }
  }
}
