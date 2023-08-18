package com.mstech.springblogpost.controller;

import com.mstech.springblogpost.allfunctions.GetUserFromJwt;
import com.mstech.springblogpost.entity.CommentEntity;
import com.mstech.springblogpost.service.CommentsService;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentsController {

  private final CommentsService commentsService;
  private final GetUserFromJwt getUserFromJwt;

  @GetMapping("api/v1/blog/{blogId}/comments")
  public ResponseEntity<List<CommentEntity>> getCommentsForBlog(
    @PathVariable("blogId") Long blogID
  ) {
    var myUser = getUserFromJwt.getCurrentActiveUser();

    System.out.println(
      "Username: " + myUser.getEmail() + "\n User Id: " + myUser.getUserId()
    );
    return commentsService.getCommentsForBlog(blogID);
  }

  @PostMapping("api/v1/blog/{blogId}/comments")
  public ResponseEntity<CommentEntity> addComments(
    @PathVariable("blogId") Long blogID,
    @RequestBody CommentEntity comments
  ) {
    return commentsService.addCommentsByBlogId(blogID, comments);
  }

  @PutMapping("/comment/{commentId}")
  public ResponseEntity<CommentEntity> updateComment(
    @PathVariable("commentId") Long commentId,
    @RequestBody CommentEntity commentRequest
  ) {
    System.out.println("Comment ID: " + commentId);
    System.out.println("Comment: " + commentRequest.getComment());
    // return null;
    return commentsService.updateComment(commentId, commentRequest);
  }

  @DeleteMapping("/comment/{commentId}")
  public ResponseEntity<HttpStatus> deleteComment(
    @PathVariable("commentId") Long commentId
  ) {
    return commentsService.deleteComment(commentId);
  }
}
