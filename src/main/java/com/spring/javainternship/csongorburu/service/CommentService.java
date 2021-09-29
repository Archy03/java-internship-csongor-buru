package com.spring.javainternship.csongorburu.service;

import com.spring.javainternship.csongorburu.entity.Comment;
import com.spring.javainternship.csongorburu.exception.ResourceNotFoundException;
import com.spring.javainternship.csongorburu.repository.CommentRepository;
import com.spring.javainternship.csongorburu.utility.Mapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final CommentRepository commentRepository;
  private final Mapper mapper;

  public Comment createComment(Comment comment) {
    return commentRepository.save(comment);
  }

  public Comment getCommentById(long id) {
    return commentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
  }

  public List<Comment> getAllComments() {
    return commentRepository.findAll();
  }

  public Comment updateComment(Comment comment, long id) {

    Comment existingComment = commentRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Comment not found with id: " + id));
    mapper.map(comment, existingComment);

    return commentRepository.save(existingComment);
  }

  public void deleteComment(long id) {

    if (commentRepository.existsById(id)) {
      commentRepository.deleteById(id);
    }
  }
}
