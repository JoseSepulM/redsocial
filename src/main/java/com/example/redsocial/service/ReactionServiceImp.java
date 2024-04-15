package com.example.redsocial.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.redsocial.model.Reaction;
import com.example.redsocial.repository.ReactionRepository;

import java.util.List;

@Service
public class ReactionServiceImp implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepository;


    @Override
    public List<Reaction> getReactionsByIdPost(Long idPost){
        return reactionRepository.findByidPost(idPost);
    }


}
