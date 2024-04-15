
package com.example.redsocial.service;

import com.example.redsocial.model.Reaction;
import java.util.List;

public interface ReactionService {
    List<Reaction> getReactionsByIdPost(Long idPost);
} 
