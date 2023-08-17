package com.mstech.springblogpost.service;

import com.mstech.springblogpost.entity.BlogEntity;
import com.mstech.springblogpost.entity.CommentEntity;
import com.mstech.springblogpost.repositories.BlogpostRepository;
import com.mstech.springblogpost.repositories.CommentsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsService {

  private final CommentsRepository commentsRepository;
  private final BlogpostRepository blogpostRepository;

  public List<CommentEntity> getCommentsForBlog(Long blogId) {
    return commentsRepository.findByBlogEntityId(blogId);
  }

  // this blogId can be used if comment does not have blog id
  public void addCommentsByBlogId(Long blogId, CommentEntity commentRequest) {
    blogpostRepository
      .findById(blogId)
      .map(blog -> {
        commentRequest.setBlogEntity(blog);
        return commentsRepository.save(commentRequest);
      })
      .orElseThrow(() -> new IllegalStateException("This blog does not exists")
      );
  }
}
