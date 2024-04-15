package com.example.redsocial.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "REACTION")
public class Reaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDREACTION")
    private Long idReaction;

    @Column(name = "TOTALLIKE")
    private Integer totalLike;

    @Column(name = "TOTALLOVE")
    private Integer totalLove;

    @Column(name = "TOTALFUNNY")
    private Integer totalFunny;
    
    @Column(name = "IDPOST")
    private Long idPost;
    

    // Getters and Setters

    public Long getIdReaction(){
        return idReaction;
    }

    public Integer getTotalLike(){
        return totalLike;
    }

    public Integer getTotalLove(){
        return totalLove;
    }

    public Integer getTotalFunny(){
        return totalFunny;
    }

    public Long getIdPost(){
        return idPost;
    }

}
