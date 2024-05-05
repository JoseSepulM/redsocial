package com.example.redsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.redsocial.repository.PostRepository;
import com.example.redsocial.model.Post;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImp implements PostService{
    
    @Autowired
    private PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }
 
    @Override
    public List<Post> getAllPosts()
    {
        return postRepository.findAll();
    }

    @Override 
    public Optional<Post> getPostById(Long idPost){
        
        return postRepository.findById(idPost);
    }

    @Override
    public Post createPost(Post payload){
        return postRepository.save(payload);
    }

    @Override
    public Post updatePost(Post payload, Long idPost){
        if(postRepository.existsById(idPost)){
            payload.setIdPost(idPost);
            return postRepository.save(payload);
        }

        return null;
    }

    @Override
    public void deletePost(Long idPost){
        postRepository.deleteById(idPost);
    }
    




}
