package com.example.redsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redsocial.model.Comment;


import com.example.redsocial.repository.CommentRepository;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentRepository commentRepository;


    @Override
    public List<Comment> getCommentsByIdPost(Long idPost){
        return commentRepository.findByIdPost(idPost);
    }


}
