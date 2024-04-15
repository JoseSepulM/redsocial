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
import java.util.Map;
import java.util.HashMap;





@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private ReactionService reactionService;


    @GetMapping()
    public List<Post> getAllPost() {
        return postService.getAllPosts();
    }

    @GetMapping("/{idPost}")
    public ResponseEntity<?> getPostById(@PathVariable Long idPost){
        Optional <Post> post = postService.getPostById(idPost);
        if(post.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("Post not found with id: " + idPost));
        }

        return ResponseEntity.ok(post);
    }

    @GetMapping("/{idPost}/detail")
    public Map<String, Object> getPostDetail(@PathVariable Long idPost) {

        Map<String, Object> result = new HashMap<>();

        Optional<Post> post = postService.getPostById(idPost);
        List<Comment> comments = commentService.getCommentsByIdPost(idPost);
        List<Reaction> reactions = reactionService.getReactionsByIdPost(idPost);


        if(post.isEmpty())
        {
            result.put("response", false);
            result.put("msg", "Post not found");
            return result;
        }

        result.put("response", true);
        result.put("post", post);
        result.put("comment", comments);
        result.put("reaction", reactions);

        return result;
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
