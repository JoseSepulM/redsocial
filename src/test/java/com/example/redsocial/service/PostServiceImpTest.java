package com.example.redsocial.service;

import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import com.example.redsocial.model.Post;
import com.example.redsocial.repository.PostRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@WebMvcTest(PostServiceImpTest.class)
public class PostServiceImpTest {
    
    @MockBean
    private PostRepository postRepository;

    @Test
    public void getAllPostsTest() {
        // Configuración del mock
        List<Post> posts = List.of(new Post(), new Post());
        when(postRepository.findAll()).thenReturn(posts);

        // Llamada al servicio
        PostService postService = new PostServiceImp(postRepository);
        List<Post> result = postService.getAllPosts();

        // Verificación
        assertEquals(2, result.size());
    }

    @Test
    public void getPostByIdTest() {
        // Configuración del mock
        Post post = new Post();
        post.setIdPost(1L);
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        // Llamada al servicio
        PostService postService = new PostServiceImp(postRepository);
        Optional<Post> result = postService.getPostById(1L);

        // Verificación
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getIdPost());
    }
}
