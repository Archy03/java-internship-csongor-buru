package com.spring.javainternship.csongorburu.service;

import com.spring.javainternship.csongorburu.entity.User;
import com.spring.javainternship.csongorburu.exception.ResourceNotFoundException;
import com.spring.javainternship.csongorburu.repository.UserRepository;
import com.spring.javainternship.csongorburu.utility.Mapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final Mapper mapper;

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public User getUserById(long id) {
    return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  public User updateUser(User user, long id) {

    User existingUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    mapper.map(user, existingUser);

    return userRepository.save(existingUser);
  }

  public void deleteUser(long id) {

    if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
    }
  }
}
