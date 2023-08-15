// package com.mstech.springblogpost.entity;

// import jakarta.persistence.Column;
// import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
// import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
// import jakarta.persistence.Table;
// import java.util.Date;
// import lombok.Getter;
// import lombok.Setter;

// @Getter
// @Setter
// @Entity
// @Table
// public class BlogpostEntity {

//   @Id
//   @GeneratedValue(strategy = GenerationType.AUTO)
//   @Column(name = "post_id")
//   private Long id;

//   private Long catID;

//   @Column(nullable = false)
//   private String title;

//   private String content;


//   private UserEntity userId;

//   @Lob
//   private byte[] thumbnail;

//   private Date createdDate;
// }
