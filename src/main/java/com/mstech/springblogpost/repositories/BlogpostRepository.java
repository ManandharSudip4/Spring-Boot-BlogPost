package com.mstech.springblogpost.repositories;

import com.mstech.springblogpost.entity.BlogEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogpostRepository extends JpaRepository<BlogEntity, Long> {
  Optional<BlogEntity> findById(Long id);
}
