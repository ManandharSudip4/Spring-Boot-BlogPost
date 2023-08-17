package com.mstech.springblogpost.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "blogs")
public class BlogEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "blog_id")
  private Long id;

  private Long catID;

  @Column(nullable = false)
  private String title;

  @Column(columnDefinition = "TEXT")
  private String content;

  @Column(name = "user_id")
  private Long userId;


  private String thumbnailUrl;

  private Date createdDate;
}
