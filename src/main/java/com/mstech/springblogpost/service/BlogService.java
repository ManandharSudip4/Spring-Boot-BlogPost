package com.mstech.springblogpost.service;

import com.mstech.springblogpost.allfunctions.GetUserFromJwt;
import com.mstech.springblogpost.entity.BlogEntity;
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
  private final GetUserFromJwt getUserFromJwt;

  public ResponseEntity<List<BlogEntity>> getAllBlogs() {
    List<BlogEntity> blogs = blogpostRepository.findAll();
    return new ResponseEntity<>(blogs, HttpStatus.OK);
  }

  public ResponseEntity<BlogEntity> getBlogByID(Long blogId) {
    System.out.println(blogId);
    BlogEntity myBlog = blogpostRepository
      .findById(blogId)
      .orElseThrow(() -> new IllegalStateException("This blog does not exists.")
      );
    // This is how we access UserEntity from Blog....
    // UserEntity user = myBlog.getUserEntity();
    // System.out.println("User Email: "+ user.getEmail());
    return new ResponseEntity<>(myBlog, HttpStatus.OK);
  }

  public ResponseEntity<HttpStatus> addNewBlog(BlogEntity blog) {
    var myUser = getUserFromJwt.getCurrentActiveUser();
    userEntityRepository
      .findByEmail(myUser.getEmail())
      .map(user -> {
        blog.setUserEntity(user);
        return blogpostRepository.save(blog);
      })
      .orElseThrow(() -> new IllegalStateException("This blog does not exists")
      );

      return new ResponseEntity<>(HttpStatus.CREATED);
  }

  public ResponseEntity<HttpStatus> deleteBlogByID(Long blogID) {
    boolean exists = blogpostRepository.existsById(blogID);

    if (!exists) {
      throw new IllegalStateException(
        "Blog ID: " + blogID + " does not exist."
      );
    }

    blogpostRepository.deleteById(blogID);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @Transactional
  public ResponseEntity<BlogEntity> updateBlog(Long blogID, BlogEntity blog) {
    BlogEntity oldBlog = blogpostRepository
      .findById(blogID)
      .orElseThrow(() -> new IllegalStateException("This blog does not exists.")
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

    return new ResponseEntity<>(blogpostRepository.save(oldBlog), HttpStatus.CREATED);
  }
}
