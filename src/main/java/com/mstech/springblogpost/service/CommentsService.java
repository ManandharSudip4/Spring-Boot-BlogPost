package com.mstech.springblogpost.service;

import com.mstech.springblogpost.allfunctions.MSFunction;
import com.mstech.springblogpost.entity.CommentEntity;
import com.mstech.springblogpost.repositories.BlogpostRepository;
import com.mstech.springblogpost.repositories.CommentsRepository;
import com.mstech.springblogpost.repositories.UserEntityRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentsService {

  private final CommentsRepository commentsRepository;
  private final BlogpostRepository blogpostRepository;
  private final UserEntityRepository userEntityRepository;
  private final MSFunction getUserFromJwt;

  public ResponseEntity<List<CommentEntity>> getCommentsForBlog(Long blogId) {
    List<CommentEntity> comment = commentsRepository.findByBlogEntityId(blogId);
    return new ResponseEntity<>(comment, HttpStatus.OK);
  }

  public ResponseEntity<CommentEntity> addCommentsByBlogId(
    Long blogId,
    CommentEntity commentRequest
  ) {
    var myUser = getUserFromJwt.getCurrentActiveUser();

    userEntityRepository
      .findByEmail(myUser.getEmail())
      .ifPresentOrElse(
        user -> {
          blogpostRepository
            .findById(blogId)
            .ifPresentOrElse(
              blog -> {
                commentRequest.setUserEntity(user);
                commentRequest.setBlogEntity(blog);
                commentsRepository.save(commentRequest);
              },
              () -> {
                throw new IllegalStateException("This blog does not exist");
              }
            );
        },
        () -> {
          throw new IllegalStateException("User not found");
        }
      );

    // CommentEntity comment = blogpostRepository
    //   .findById(blogId)
    //   .map(blog -> {
    //     commentRequest.setBlogEntity(blog);
    //     return commentsRepository.save(commentRequest);
    //   })
    //   .orElseThrow(() -> new IllegalStateException("This blog does not exists")
    //   );

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @Transactional
  public ResponseEntity<CommentEntity> updateComment(
    Long commentId,
    CommentEntity commentRequest
  ) {
    CommentEntity comment = commentsRepository
      .findById(commentId)
      .orElseThrow(() -> new IllegalStateException("This comment not found."));

    String newComment = commentRequest.getComment();

    if (
      newComment != null && !Objects.equals(newComment, comment.getComment())
    ) {
      comment.setComment(newComment);
    }

    return new ResponseEntity<CommentEntity>(
      commentsRepository.save(comment),
      HttpStatus.OK
    );
  }

  public ResponseEntity<HttpStatus> deleteComment(Long commentId) {
    commentsRepository.deleteById(commentId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
