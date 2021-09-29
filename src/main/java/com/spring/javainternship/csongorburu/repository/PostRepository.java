package com.spring.javainternship.csongorburu.repository;

import com.spring.javainternship.csongorburu.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {}