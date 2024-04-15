
package com.example.redsocial.service;

import com.example.redsocial.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts();
    Optional<Post> getPostById(Long id);
    Post createPost(Post payload);
    Post updatePost(Post payload, Long id);
    void deletePost(Long id);
} 
