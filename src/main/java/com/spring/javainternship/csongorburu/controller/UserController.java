package com.spring.javainternship.csongorburu.controller;

import com.spring.javainternship.csongorburu.dto.UserRequest;
import com.spring.javainternship.csongorburu.dto.UserResponse;
import com.spring.javainternship.csongorburu.dto.UserResponseWithJoke;
import com.spring.javainternship.csongorburu.entity.User;
import com.spring.javainternship.csongorburu.service.JokeService;
import com.spring.javainternship.csongorburu.service.UserService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final JokeService jokeService;
  private final Mapper mapper;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponse createUser(@Valid @RequestBody UserRequest userRequest) {

    User user = mapper.map(userRequest, User.class);
    User userSaved = userService.createUser(user);
    UserResponse userResponse = mapper.map(userSaved, UserResponse.class);

    return userResponse;
  }

  @GetMapping("/getOneUser/{userId}")
  public UserResponse getUserById(@PathVariable long userId) {

    User user = userService.getUserById(userId);
    UserResponse userResponse = mapper.map(user, UserResponse.class);

    return userResponse;
  }

  @GetMapping("/getOneUserWithJoke/{userId}")
  public UserResponseWithJoke getUserByIdWithJoke(@PathVariable long userId, @RequestParam(required = false) int amount) {

    User user = userService.getUserById(userId);
    UserResponseWithJoke userResponseWithJoke = mapper.map(user, UserResponseWithJoke.class);
    if (amount == 1) {
      userResponseWithJoke.setJokeRes(jokeService.getJoke());
    } else if (amount > 1) {
      userResponseWithJoke.setJokeRes(jokeService.getJokeResponse(amount));
    }

    return userResponseWithJoke;
  }

  @GetMapping("/list")
  public List<UserResponse> getAllUsers() {

    List<User> userList = userService.getAllUsers();

    return userList.stream().map(user -> mapper.map(user, UserResponse.class)).collect(Collectors.toList());
  }

  @PutMapping("/update/{userId}")
  public UserResponse updateUser(@Valid @RequestBody UserRequest userRequest, @PathVariable long userId) {

    User user = userService.updateUser(mapper.map(userRequest, User.class), userId);
    UserResponse userResponse = mapper.map(user, UserResponse.class);

    return userResponse;
  }

  @DeleteMapping("/delete/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable long userId) {
    userService.deleteUser(userId);
  }
}