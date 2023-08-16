package com.mstech.springblogpost.repositories;

import com.mstech.springblogpost.entity.BlogpostEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BlogpostRepository
  extends JpaRepository<BlogpostEntity, Long> {

    Optional<BlogpostEntity> findById(Long id);
  }
