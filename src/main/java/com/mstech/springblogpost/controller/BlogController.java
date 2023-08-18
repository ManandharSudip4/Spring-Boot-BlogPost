package com.mstech.springblogpost.controller;

import com.mstech.springblogpost.entity.BlogEntity;
import com.mstech.springblogpost.service.BlogService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
public class BlogController {

  public final BlogService blogService;

  @GetMapping("/blogs")
  public ResponseEntity<List<BlogEntity>> getAllBlogs() {
    return blogService.getAllBlogs();
  }

  @GetMapping("/blogs/{id}")
  public ResponseEntity<BlogEntity> getBlogByID(
    @PathVariable("id") Long blogID
  ) {
    return blogService.getBlogByID(blogID);
  }

  @PostMapping("/blog")
  public ResponseEntity<HttpStatus> addNewBlog(@RequestBody BlogEntity blog) {
    return blogService.addNewBlog(blog);
  }

  @DeleteMapping("blog/{id}")
  public ResponseEntity<HttpStatus> deleteBlogByID(
    @PathVariable("id") Long blogID
  ) {
    return blogService.deleteBlogByID(blogID);
  }

  @PutMapping("/blog/{blogId}")
  public ResponseEntity<BlogEntity> updateBlog(
    @PathVariable("blogId") Long blogId,
    @RequestBody BlogEntity blog
  ) {
    return blogService.updateBlog(blogId, blog);
  }
}
