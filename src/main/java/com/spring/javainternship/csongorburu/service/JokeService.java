package com.spring.javainternship.csongorburu.service;

import com.spring.javainternship.csongorburu.dto.JokeResponse;
import com.spring.javainternship.csongorburu.entity.Joke;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class JokeService {

  private final RestTemplate restTemplate;

  public JokeResponse getJokeResponse(int amount) {

    String url = "https://sv443.net/jokeapi/v2/joke/Any?type=single&amount={amount}";

    return restTemplate.getForObject(url, JokeResponse.class, amount);
  }

  public Joke getJoke() {

    String url = "https://sv443.net/jokeapi/v2/joke/Any?type=single";

    return restTemplate.getForObject(url, Joke.class);
  }
}
