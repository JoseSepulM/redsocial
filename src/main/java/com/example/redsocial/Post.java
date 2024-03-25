package com.example.redsocial;
import java.time.LocalDate;


public class Post {
    private int idPost;
    private String userPost;
    private String textPost;
    private LocalDate datePost;


    public Post(int idPost, String userPost, String textPost, LocalDate datePost){
        this.idPost = idPost;
        this.userPost = userPost;
        this.textPost = textPost;
        this.datePost = datePost;
    }

    // GET

    public int getIdPost(){
        return idPost;
    }

    public String getUserPost(){
        return userPost;
    }

    public String getTextPost(){
        return textPost;
    }
    
    public LocalDate getDatePost(){
        return datePost;
    }
}
