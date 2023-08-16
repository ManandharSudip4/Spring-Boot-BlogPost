package com.mstech.springblogpost.controller;

import com.mstech.springblogpost.entity.BlogpostEntity;
import com.mstech.springblogpost.service.BlogService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
  public List<BlogpostEntity> getAllBlogs() {
    return blogService.getAllBlogs();
  }

  @GetMapping("blogs/{id}")
  public void getBlogByID(@PathVariable("id") Long blogID) {
    blogService.getBlogByID(blogID);
  }

  @PostMapping("/blog")
  public void addNewBlog(@RequestBody BlogpostEntity blog) {
    blogService.addNewBlog(blog);
  }

  @DeleteMapping("blog/{id}")
  public void deleteBlogByID(@PathVariable("id") Long blogID) {
    blogService.deleteBlogByID(blogID);
  }

  @PutMapping("/blog/{blogId}")
  public void updateBlog(
    @PathVariable("blogId") Long blogId,
    @RequestBody BlogpostEntity blog
  ) {
    blogService.updateBlog(blogId, blog);
  }
}
