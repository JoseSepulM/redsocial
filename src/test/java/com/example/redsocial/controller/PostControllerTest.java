package com.example.redsocial.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;
import com.example.redsocial.model.Post;
import com.example.redsocial.service.CommentService;
import com.example.redsocial.service.PostService;
import com.example.redsocial.service.ReactionService;
import static org.hamcrest.Matchers.equalTo;

import java.sql.Date;


@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postServiceMock;

    @MockBean 
    private CommentService commentServiceMock;

    @MockBean 
    private ReactionService reactionServiceMock;

    @Test
    public void obtenerTodosTest() throws Exception {
        Date fechaActual = new Date(System.currentTimeMillis());
        Post post1 = new Post();
        post1.setIdPost(1L);
        post1.setUserPost("User1");
        post1.setDatePost(fechaActual);
        post1.setTextPost("Hola a todos");
        Post post2 = new Post();
        post2.setIdPost(2L);
        post2.setUserPost("User2");
        post2.setDatePost(fechaActual);
        post2.setTextPost("Adios a todos");
        List<Post> posts = List.of(post1, post2);

        List<EntityModel<Post>> postRepository = posts.stream()
        .map(post -> EntityModel.of(post))
        .collect(Collectors.toList());

        when(postServiceMock.getAllPosts()).thenReturn(posts);

        mockMvc.perform(get("/post"))
        .andExpect(status().isOk())

        .andExpect(jsonPath("$._embedded.postList.length()").value(equalTo(2)))
        .andExpect(jsonPath("$._embedded.postList[0].userPost").value("User1"))
        .andExpect(jsonPath("$._embedded.postList[1].userPost").value("User2"))
        .andExpect(jsonPath("$._embedded.postList[0]._links.self.href").value("http://localhost/post/1"))
        .andExpect(jsonPath("$._embedded.postList[1]._links.self.href").value("http://localhost/post/2"));
    }

    @Test
    public void obtenerPostTest() throws Exception {
        Date fechaActual = new Date(System.currentTimeMillis());
        Post post1 = new Post();
        post1.setIdPost(1L);
        post1.setUserPost("User1");
        post1.setDatePost(fechaActual);
        post1.setTextPost("Hola a todos");

        when(postServiceMock.getPostById(1L)).thenReturn(Optional.of(post1));

        mockMvc.perform(get("/post/{idPost}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userPost").value("User1"))
                .andExpect(jsonPath("$._links.self.href").value("http://localhost/post/1"));
    }
}