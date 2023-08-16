package com.mstech.springblogpost.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mstech.springblogpost.entity.CommentEntity;
import com.mstech.springblogpost.repositories.CommentsRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentsService {
    private final CommentsRepository commentsRepository;

    public List<CommentEntity> getCommentsForBlog(Long blogId){
        return commentsRepository.findByBlogId(blogId);
    }


    // this blogId can be used if comment does not have blog id
    public void addCommentsByBlogId(Long blogId, CommentEntity comment){
        commentsRepository.save(comment);
    }
}
