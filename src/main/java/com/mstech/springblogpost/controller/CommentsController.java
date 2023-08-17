package com.mstech.springblogpost.controller;

import com.mstech.springblogpost.entity.CommentEntity;
import com.mstech.springblogpost.service.CommentsService;
import java.util.List;
import lombok.RequiredArgsConstructor;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/blog/{blogId}")
@RequiredArgsConstructor
public class CommentsController {

  private final CommentsService commentsService;


  @GetMapping("/comments")
  public List<CommentEntity> getCommentsForBlog(
    @PathVariable("blogId") Long blogID
  ) {
    return commentsService.getCommentsForBlog(blogID);
  }

  @PostMapping("/comments")
  public void addComments(
    @PathVariable("blogId") Long blogID,
    @RequestBody CommentEntity comments
  ){
    // System.out.println(comments.getUserId());
    // System.out.println(comments.getBlogEntity().getId());
    // System.out.println(comments.getComment());
    // System.out.println("This is a comment: "+ objectMapper.writeValueAsString(comments));
    commentsService.addCommentsByBlogId(blogID, comments);
  }
}
