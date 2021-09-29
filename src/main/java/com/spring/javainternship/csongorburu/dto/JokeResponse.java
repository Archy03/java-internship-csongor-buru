package com.spring.javainternship.csongorburu.dto;

import com.spring.javainternship.csongorburu.entity.Joke;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class JokeResponse {

  private List<Joke> jokes;
}
