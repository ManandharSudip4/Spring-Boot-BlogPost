package com.mstech.springblogpost.service;

import com.mstech.springblogpost.entity.BlogEntity;
import com.mstech.springblogpost.repositories.BlogpostRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

  private final BlogpostRepository blogpostRepository;

  public List<BlogEntity> getAllBlogs() {
    return blogpostRepository.findAll();
  }

  public BlogEntity getBlogByID(Long blogId) {
    System.out.println(blogId);
    BlogEntity myBlog = blogpostRepository
      .findById(blogId)
      .orElseThrow(() -> new IllegalStateException("This blog does not exists.")
      );
    return myBlog;
  }

  public void addNewBlog(BlogEntity blog) {
    blogpostRepository.save(blog);
  }

  public void deleteBlogByID(Long blogID) {
    boolean exists = blogpostRepository.existsById(blogID);

    if (!exists) {
      throw new IllegalStateException(
        "Blog ID: " + blogID + " does not exist."
      );
    }

    blogpostRepository.deleteById(blogID);
  }

  @Transactional
  public void updateBlog(Long blogID, BlogEntity blog) {
    BlogEntity oldBlog = blogpostRepository
      .findById(blogID)
      .orElseThrow(() -> new IllegalStateException("This blog does not exists.")
      );

    Long catid = blog.getCatID();
    String title = blog.getTitle();
    String content = blog.getContent();
    // Long userId = blog.getId();
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

    // if (userId != null && !Objects.equals(userId, oldBlog.getUserId())) {
    //   oldBlog.setUserId(userId);
    // }

    if (
      thumbnail != null && !Objects.equals(thumbnail, oldBlog.getThumbnailUrl())
    ) {
      oldBlog.setThumbnailUrl(thumbnail);
    }

    blogpostRepository.save(oldBlog);
  }
}
