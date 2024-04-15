package com.example.redsocial.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "COMMENT_POST")
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCOMMENT")
    private Long idComment;

    @Column(name = "USERCOMMENT")
    private String userComment;

    @Column(name = "TEXTCOMMENT")
    private String textComment;

    @Column(name = "DATECOMMENT")
    private Date dateComment;

    @Column(name = "IDPOST")
    private Long idPost;
    

    // Getters and Setters

    public Long getIdComment(){
        return idComment;
    }

    public String getUserComment(){
        return userComment;
    }

    public String getTextComment(){
        return textComment;
    }

    public Date getDateComment(){
        return dateComment;
    }

    public Long getIdPost(){
        return idPost;
    }

    public void setIdComment(Long idComment){
        this.idComment = idComment;
    }

    public void setUserComment(String userComment){
        this.userComment = userComment;
    }

    public void setTextComment(String textComment){
        this.textComment = textComment;
    }

    public void setDateComment(Date dateComment){
        this.dateComment = dateComment;
    }

    public void setDateComment(Long idPost){
        this.idPost = idPost;
    }

}
