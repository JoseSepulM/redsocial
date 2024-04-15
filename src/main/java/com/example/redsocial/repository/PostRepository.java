package com.example.redsocial.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.redsocial.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}