package com.spring.javainternship.csongorburu.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.spring.javainternship.csongorburu.entity.Post;
import com.spring.javainternship.csongorburu.entity.Tag;
import com.spring.javainternship.csongorburu.entity.User;
import com.spring.javainternship.csongorburu.repository.PostRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;

import static java.util.Optional.of;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

  @Mock
  private PostRepository postRepository;

  @InjectMocks
  private PostService postService;

  private Post post1;

  @Before
  public void setUpInit() {

    User user1 = new User();
    post1 = new Post();
    post1.setId(700L);
    post1.setTitle("This is a title");
    post1.setContent("Something long content");
    post1.setCreatedAt(LocalDateTime.now());
    post1.setUpdatedAt(LocalDateTime.now());
    post1.setUser(user1);
  }

  @Test
  public void testCreatePostGivenExistingIdShouldReturnPost() {

    when(postRepository.save(post1)).thenReturn(post1);

    Post result = postService.createPost(post1);

    assertEquals(700L, result.getId());
    assertNotEquals(500L, result.getId());
  }

  @Test
  public void testGetPostByIdGivenExistingIdShouldReturnPost() {

    when(postRepository.findById(700L)).thenReturn(of(post1));

    Post result = postService.getPostById(700L);

    assertEquals(700L, result.getId());
    assertNotEquals(500L, result.getId());
  }

  @Test
  public void testGetAllPostsGivenSizeNumberShouldReturnListSize() {

    List<Post> list = new ArrayList<>();
    list.add(post1);

    when(postRepository.findAll()).thenReturn(list);
    Page<Post> result = postService.getAllPosts(0, 1, "id", "asc");

    assertEquals(1, result.getSize());
    assertNotEquals(3, result.getSize());
  }

  @Test
  public void testDeletePostReturnDeleteVerification() {

    doNothing().when(postRepository).deleteById(anyLong());
    when(postRepository.existsById(anyLong())).thenReturn(true);
    postService.deletePost(anyLong());
    verify(postRepository, times(1)).deleteById(anyLong());
  }
}