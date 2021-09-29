package com.spring.javainternship.csongorburu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponseBrief {

  private Long userId;
  private String text;
}