package com.example.redsocial;


import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PostController {
    
    private PostService postService;
    private CommentService commentService;
    private ReactionService reactionService;

    public PostController(PostService  postService, CommentService commentService, ReactionService reactionService){
        this.postService = postService;
        this.commentService = commentService;
        this.reactionService = reactionService;
    }

    @GetMapping("post")
    public List<Post> getPostList() {
        return postService.getAllPosts();
    }

    @GetMapping("post/{idPost}")
    public Post getPostById(@PathVariable int idPost) {
        return postService.getPostById(idPost);
    }

    @GetMapping("post/{idPost}/detail")
    public Map<String, Object> getPostDetail(@PathVariable int idPost) {
        Post post = postService.getPostById(idPost);
        List<Comment> comments = commentService.getCommentByIdPost(idPost);
        Reaction reaction = reactionService.getReactionByIdPost(idPost);
        Map<String, Object> result = new HashMap<>();
        result.put("post", post);
        result.put("comments", comments);
        result.put("reactions", reaction);
        return result;
    }

    @GetMapping("post/{idPost}/metrics")
    public Map<String, Object> getPostMetrics(@PathVariable int idPost) {
        Post post = postService.getPostById(idPost);
        List<Comment> comments = commentService.getCommentByIdPost(idPost);
        Reaction reaction = reactionService.getReactionByIdPost(idPost);
        Map<String, Object> result = new HashMap<>();
        result.put("post", post);
        result.put("totaComments", comments.size());
        result.put("totalLikes", reaction.getTotalLike());
        result.put("totalLoves", reaction.getTotalLove());
        result.put("totalFunnys", reaction.getTotalFunny());
        return result;
    }
    
    
    
    
}
