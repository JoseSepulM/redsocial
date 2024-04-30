package com.example.redsocial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redsocial.model.Post;
import com.example.redsocial.model.Comment;
import com.example.redsocial.model.Reaction;
import com.example.redsocial.service.CommentService;
import com.example.redsocial.service.PostService;
import com.example.redsocial.service.ReactionService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.HashMap;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




@RestController
@RequestMapping("/post")
public class PostController {

    private static final Logger log = LoggerFactory.getLogger(PostController.class);
    
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactionService reactionService;


    @GetMapping()
    public CollectionModel<EntityModel<Post>> getAllPost() {
        List<Post> posts = postService.getAllPosts();
        log.info("GET /post");
        log.info("Retornando todos los post");

        List<EntityModel<Post>> postsResources = posts.stream()
        .map( post -> EntityModel.of(post,
            WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPostById(post.getIdPost())).withSelfRel()
        ))
        .collect(Collectors.toList());
       
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPost());
        CollectionModel<EntityModel<Post>> resources = CollectionModel.of(postsResources, linkTo.withRel("posts"));

        return resources;
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<?> getPostById(@PathVariable Long idPost) {
        Optional<Post> optionalPost = postService.getPostById(idPost);
        if (optionalPost.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Post not found with id: " + idPost));
        }
    
        Post post = optionalPost.get();
        EntityModel<Post> postResource = EntityModel.of(post,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPostById(idPost)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getAllPost()).withRel("allPosts"));
    
        return ResponseEntity.ok(postResource);
    }

    @GetMapping("/{idPost}/detail")
    public ResponseEntity<EntityModel<Map<String, Object>>> getPostDetail(@PathVariable Long idPost) {
    
        Map<String, Object> result = new HashMap<>();
    
        Optional<Post> optionalPost = postService.getPostById(idPost);
        List<Comment> comments = commentService.getCommentsByIdPost(idPost);
        List<Reaction> reactions = reactionService.getReactionsByIdPost(idPost);
    
        if (optionalPost.isEmpty()) {
            result.put("response", false);
            result.put("msg", "Post not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body(EntityModel.of(result,
                                                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPostDetail(idPost)).withSelfRel()));
        }
    
        Post post = optionalPost.get();
        result.put("response", true);
        result.put("post", post);
        result.put("comment", comments);
        result.put("reaction", reactions);
    
        EntityModel<Map<String, Object>> resultResource = EntityModel.of(result,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPostDetail(idPost)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPostById(idPost)).withRel("post"));
    
        return ResponseEntity.ok(resultResource);
    }
    
   
    @PostMapping()
    public Post addPost(@RequestBody Post payload){
        return postService.createPost(payload);
    }

    @PutMapping("/{idPost}")
    public ResponseEntity<?> updatePost(@RequestBody Post payload, @PathVariable Long idPost){
        Optional <Post> post = postService.getPostById(idPost);
        if(post.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Post not found with id: " + idPost));
        }

        postService.updatePost(payload, idPost);
        return ResponseEntity.ok("Update post!");
    }

    @DeleteMapping("/{idPost}")
    public ResponseEntity<?> deletePost(@PathVariable Long idPost){
        
        Optional <Post> post = postService.getPostById(idPost);
        if(post.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Post not found with id:  " + idPost));
        }
        
        postService.deletePost(idPost);
        return ResponseEntity.ok("Delete post!");
    }
    

    static class ErrorResponse{
        private final String message;
        
        public ErrorResponse(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
