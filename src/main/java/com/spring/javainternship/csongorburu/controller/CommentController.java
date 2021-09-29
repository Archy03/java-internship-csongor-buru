package com.spring.javainternship.csongorburu.controller;

import com.spring.javainternship.csongorburu.dto.CommentRequest;
import com.spring.javainternship.csongorburu.dto.CommentResponse;
import com.spring.javainternship.csongorburu.entity.Comment;
import com.spring.javainternship.csongorburu.service.CommentService;
import com.spring.javainternship.csongorburu.utility.Mapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;
  private final Mapper mapper;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public CommentResponse createComment(@Valid @RequestBody CommentRequest commentRequest) {

    Comment comment = mapper.map(commentRequest, Comment.class);
    Comment commentSaved = commentService.createComment(comment);
    CommentResponse commentResponse = mapper.map(commentSaved, CommentResponse.class);

    return commentResponse;
  }

  @GetMapping("/getone/{commentId}")
  public CommentResponse getCommentById(@PathVariable long commentId) {

    Comment comment = commentService.getCommentById(commentId);

    return mapper.map(comment, CommentResponse.class);
  }

  @GetMapping("/list")
  public List<CommentResponse> getAllComments() {

    List<Comment> commentList = commentService.getAllComments();

    return commentList.stream().map(comment -> mapper.map(comment, CommentResponse.class)).collect(
        Collectors.toList());
  }

  @PutMapping("/update/{commentId}")
  public CommentResponse updateComment(@Valid @RequestBody CommentRequest commentRequest, @PathVariable long commentId) {

    Comment comment = commentService.updateComment(mapper.map(commentRequest, Comment.class), commentId);
    CommentResponse commentResponse = mapper.map(comment, CommentResponse.class);

    return commentResponse;
  }

  @DeleteMapping("/delete/{commentId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteComment(@PathVariable long commentId) {
    commentService.deleteComment(commentId);
  }
}
