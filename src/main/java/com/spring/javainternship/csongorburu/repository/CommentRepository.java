package com.spring.javainternship.csongorburu.repository;

import com.spring.javainternship.csongorburu.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {}