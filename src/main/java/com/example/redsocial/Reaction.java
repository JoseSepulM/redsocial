package com.example.redsocial;

public class Reaction {
    private int idReaction;
    private int totalLike;
    private int totalLove;
    private int totalFunny;
    private int idPost;

    public Reaction(int idReaction, int totalLike, int totalLove, int totalFunny, int idPost){
        this.idReaction = idReaction;
        this.totalLike = totalLike;
        this.totalLove = totalLove;
        this.totalFunny = totalFunny;
        this.idPost = idPost;
    }

    //GET
    public int getIdReaction(){
        return idReaction;
    }

    public int getTotalLike(){
        return totalLike;
    }

    public int getTotalLove(){
        return totalLove;
    }

    public int getTotalFunny(){
        return totalFunny;
    }

    public int getIdPost(){
        return idPost;
    }
}
