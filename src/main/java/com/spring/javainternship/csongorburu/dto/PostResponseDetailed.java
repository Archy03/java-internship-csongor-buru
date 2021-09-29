package com.spring.javainternship.csongorburu.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDetailed {

  private Long id;
  private String title;
  private String content;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private UserResponse user;
  private List<TagResponse> tagResponses;
  private List<CommentResponseBrief> commentResponseBriefs;
}

