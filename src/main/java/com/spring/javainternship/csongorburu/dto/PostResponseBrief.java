package com.spring.javainternship.csongorburu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseBrief {

  private Long id;
  private String title;
  private String content;
  private UserResponse user;
}

