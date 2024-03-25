package com.example.redsocial;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class PostService {
    private List<Post> posts = new ArrayList<>();

    public PostService(List<Post> posts){
        this.posts = posts;
        posts.add(new Post(1, "Pepe12", "Feliz anio nuevo a todos mis amigos", LocalDate.of(2024, 01, 01)));
        posts.add(new Post(2, "JuanAzul", "Aguante la U", LocalDate.of(2024, 03,10)));
        posts.add(new Post(3, "Carlos GB", "Aguante Colo Colo", LocalDate.of(2024, 03, 13)));
    }

    public List<Post> getAllPosts(){
        return posts;
    }

    public Post getPostById(int idPost){
        for(Post post : posts){
            if(post.getIdPost() == idPost){
                return post;
            }
        }

        return null;
    }
}
