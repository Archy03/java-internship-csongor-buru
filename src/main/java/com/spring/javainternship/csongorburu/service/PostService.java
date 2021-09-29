package com.spring.javainternship.csongorburu.service;

import com.spring.javainternship.csongorburu.entity.Post;
import com.spring.javainternship.csongorburu.entity.Tag;
import com.spring.javainternship.csongorburu.entity.User;
import com.spring.javainternship.csongorburu.exception.BadInputException;
import com.spring.javainternship.csongorburu.exception.ResourceNotFoundException;
import com.spring.javainternship.csongorburu.repository.PostRepository;
import com.spring.javainternship.csongorburu.repository.TagRepository;
import com.spring.javainternship.csongorburu.repository.UserRepository;
import com.spring.javainternship.csongorburu.utility.Mapper;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;
  private final Mapper mapper;
  private final UserRepository userRepository;
  private final TagRepository tagRepository;

  public Post createPost(Post post) {

    post.setUser(getPostUser(post));
    post.setTags(getPostTags(post));

    return postRepository.save(post);
  }

  private List<Tag> getPostTags(Post post) {

    Set<Long> tagIds = new HashSet<>();
    post.getTags().forEach(tag -> tagIds.add(tag.getId()));

    return tagRepository.findAllById(tagIds);
  }

  private User getPostUser(Post post) {

    return userRepository.findOne(Example.of(post.getUser())).orElseThrow(
        () -> new ResourceNotFoundException("User not found with id: " + post.getUser().getId()));
  }

  public Post getPostById(long id) {

    return postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
  }

  public Page<Post> getAllPosts(int page, int size, String sort, String direction) {

    if (page < 0 || size < 0) throw new BadInputException("Cannot be a negative number");
    Pageable pageable = PageRequest.of(page, size, Sort.by(Direction.fromString(direction), sort));

    return postRepository.findAll(pageable);
  }

  public Post updatePost(Post post, long id) {

    Post existingPost = postRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    mapper.map(post, existingPost);

    return postRepository.save(existingPost);
  }

  public void deletePost(long id) {

    if (postRepository.existsById(id)) {
      postRepository.deleteById(id);
    }
  }
}