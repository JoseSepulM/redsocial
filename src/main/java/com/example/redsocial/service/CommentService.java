
package com.example.redsocial.service;

import com.example.redsocial.model.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentsByIdPost(Long idPost);
   
} 
