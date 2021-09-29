package com.spring.javainternship.csongorburu.dto;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Value;

@Value
public class PostRequest {

  @NotBlank
  String title;
  @NotBlank
  String content;
  @NotNull
  Long userId;
  List<Long> tagIds;
}