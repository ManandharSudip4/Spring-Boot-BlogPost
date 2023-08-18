package com.mstech.springblogpost.service;

import com.mstech.springblogpost.allfunctions.MSFunction;
import com.mstech.springblogpost.entity.BlogEntity;
import com.mstech.springblogpost.exceptions.ResourceNotFoundException;
import com.mstech.springblogpost.repositories.BlogpostRepository;
import com.mstech.springblogpost.repositories.UserEntityRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogpostRepository blogpostRepository;
  private final UserEntityRepository userEntityRepository;
  private final MSFunction msFunction;

  public ResponseEntity<List<BlogEntity>> getAllBlogs() {
    try {
      List<BlogEntity> blogs = blogpostRepository.findAll();
      return new ResponseEntity<>(blogs, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<BlogEntity> getBlogByID(Long blogId) {
    BlogEntity myBlog = blogpostRepository
      .findById(blogId)
      .orElseThrow(() ->
        new ResourceNotFoundException(
          msFunction.errorDesc(blogId, "Blog", "Retrieve")
        )
      );
    // This is how we access UserEntity from Blog....
    // UserEntity user = myBlog.getUserEntity();
    // System.out.println("User Email: "+ user.getEmail());
    return new ResponseEntity<>(myBlog, HttpStatus.OK);
  }

  public ResponseEntity<HttpStatus> addNewBlog(BlogEntity blog) {
    var myUser = msFunction.getCurrentActiveUser();
    userEntityRepository
      .findByEmail(myUser.getEmail())
      .map(user -> {
        blog.setUserEntity(user);
        return blogpostRepository.save(blog);
      })
      .orElseThrow(() ->
        new ResourceNotFoundException("You are not authorized to add the blog.")
      );

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  public ResponseEntity<HttpStatus> deleteBlogByID(Long blogId) {
    boolean exists = blogpostRepository.existsById(blogId);

    if (!exists) {
      throw new ResourceNotFoundException(
        msFunction.errorDesc(blogId, "Blog", "Delete")
      );
    }

    blogpostRepository.deleteById(blogId);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Transactional
  public ResponseEntity<BlogEntity> updateBlog(Long blogId, BlogEntity blog) {
    BlogEntity oldBlog = blogpostRepository
      .findById(blogId)
      .orElseThrow(() ->
        new ResourceNotFoundException(
          msFunction.errorDesc(blogId, "Blog", "Update")
        )
      );

    Long catid = blog.getCatID();
    String title = blog.getTitle();
    String content = blog.getContent();
    String thumbnail = blog.getThumbnailUrl();

    if (catid != null && !Objects.equals(catid, oldBlog.getCatID())) {
      oldBlog.setCatID(catid);
    }

    if (title != null && !Objects.equals(title, oldBlog.getTitle())) {
      oldBlog.setTitle(title);
    }

    if (content != null && !Objects.equals(content, oldBlog.getContent())) {
      oldBlog.setContent(content);
    }

    if (
      thumbnail != null && !Objects.equals(thumbnail, oldBlog.getThumbnailUrl())
    ) {
      oldBlog.setThumbnailUrl(thumbnail);
    }

    return new ResponseEntity<>(
      blogpostRepository.save(oldBlog),
      HttpStatus.CREATED
    );
  }
}
