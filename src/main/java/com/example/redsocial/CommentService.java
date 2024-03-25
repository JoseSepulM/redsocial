package com.example.redsocial;

import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CommentService {
    private List<Comment> comments = new ArrayList<>();

    public CommentService(List<Comment> comments){
        this.comments = comments;
        comments.add(new Comment(1, "Pedro 23", "Igual a ti bro", LocalDate.of(2024, 01, 01), 1));
        comments.add(new Comment(2, "Marcela Paz", "Saludos a la familia", LocalDate.of(2024, 01, 01), 1));
        comments.add(new Comment(3, "Diego Rivarola", "Lo mejor!", LocalDate.of(2024, 03, 10), 2));
        comments.add(new Comment(4, "Pedro Bulla", "Aguante!", LocalDate.of(2024, 03, 11), 2));
        comments.add(new Comment(5, "Maikel Indio", "Aguante la garra", LocalDate.of(2024, 03, 13), 3));
        comments.add(new Comment(6, "Ivan Zamorano", "Buena clasificacion", LocalDate.of(2024, 03, 14), 3));   
    }

    public List<Comment> getCommentByIdPost(int idPost){
        List<Comment> commentPost = new ArrayList<>();
        for(Comment comment : comments){
            if(comment.getPostId() == idPost)
            {
                commentPost.add(comment);
            }
        }

        return commentPost;

    }
}
