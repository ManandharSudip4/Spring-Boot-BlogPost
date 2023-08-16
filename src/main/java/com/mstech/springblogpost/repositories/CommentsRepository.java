package com.mstech.springblogpost.repositories;

import com.mstech.springblogpost.entity.CommentEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<CommentEntity, Long> {
  List<CommentEntity> findByBlogId(Long blogId);
}
