package com.spring.javainternship.csongorburu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

  private Long id;
  private String text;
  private Long userId;
  private Long postId;
}