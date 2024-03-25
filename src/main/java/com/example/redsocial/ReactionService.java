package com.example.redsocial;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
public class ReactionService {
    private List<Reaction> reactions = new ArrayList<>();

    public ReactionService(List<Reaction> reactions){
        this.reactions = reactions;

        reactions.add(new Reaction(1, 6, 12, 0, 1));
        reactions.add(new Reaction(2, 12, 20, 0, 2));
        reactions.add(new Reaction(3, 3, 0, 15, 3));
        
    }

    public Reaction getReactionByIdPost(int idPost){
        for(Reaction reaction : reactions){
            if(reaction.getIdPost() == idPost){
                return reaction;
            }
        }

        return null;
    }


}
