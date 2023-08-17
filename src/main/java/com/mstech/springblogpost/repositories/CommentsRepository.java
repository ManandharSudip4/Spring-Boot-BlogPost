package com.mstech.springblogpost.repositories;

import com.mstech.springblogpost.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {
  @Query("SELECT c FROM CommentEntity c WHERE c.blogEntity.id = :blogId")
  List<CommentEntity> findByBlogEntityId(Long blogId);
}
