package com.example.redsocial.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "POST")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDPOST")
    private Long idPost;

    @Column(name = "USERPOST")
    private String userPost;

    @Column(name = "TEXTPOST")
    private String textPost;

    @Column(name = "DATEPOST")
    private Date datePost;
    

    // Getters and Setters

    public Long getIdPost(){
        return idPost;
    }

    public String getUserPost(){
        return userPost;
    }

    public String getTextPost(){
        return textPost;
    }

    public Date getDatePost(){
        return datePost;
    }

    public void setIdPost(Long idPost){
        this.idPost = idPost;
    }

    public void setUserPost(String userPost){
        this.userPost = userPost;
    }

    public void setTextPost(String textPost){
        this.textPost = textPost;
    }

    public void setDatePost(Date datePost){
        this.datePost = datePost;
    }

}
