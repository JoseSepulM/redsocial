package com.example.redsocial;

import java.time.LocalDate;


public class Comment {
    private int idComment;
    private String userComment;
    private String textComment;
    private LocalDate dateComment;
    private int postId;

    public Comment(int idComment, String userComment, String textComent, LocalDate dateComment, int postId){
        this.idComment = idComment;
        this.userComment = userComment;
        this.textComment = textComent;
        this.dateComment = dateComment;
        this.postId = postId;
    }

    // GET

    public int getIdComment(){
        return idComment;
    }

    public String getUserCommnet(){
        return userComment;
    }

    public String getTextCommnet(){
        return textComment;
    }

    public LocalDate getDateComment(){
        return dateComment;
    }
    
    public int getPostId(){
        return postId;
    }
}
