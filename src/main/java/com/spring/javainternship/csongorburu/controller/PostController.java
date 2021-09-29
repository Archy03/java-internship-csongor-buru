package com.spring.javainternship.csongorburu.controller;

import com.spring.javainternship.csongorburu.dto.PagedResponse;
import com.spring.javainternship.csongorburu.dto.PostRequest;
import com.spring.javainternship.csongorburu.dto.PostResponseBrief;
import com.spring.javainternship.csongorburu.dto.PostResponseDetailed;
import com.spring.javainternship.csongorburu.entity.Post;
import com.spring.javainternship.csongorburu.service.PostService;
import com.spring.javainternship.csongorburu.utility.Mapper;
import io.swagger.annotations.ApiParam;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;
  private final Mapper mapper;

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public PostResponseDetailed createPost(@Valid @RequestBody PostRequest postRequest) {

    Post post = mapper.map(postRequest, Post.class);
    Post postSaved = postService.createPost(post);
    PostResponseDetailed postResponseDetailed = mapper.map(postSaved, PostResponseDetailed.class);
    return postResponseDetailed;
  }

  @GetMapping("/getone/{postId}")
  public PostResponseDetailed getPostById(@PathVariable long postId) {

    Post post = postService.getPostById(postId);
    PostResponseDetailed postResponseDetailed = mapper.map(post, PostResponseDetailed.class);

    return postResponseDetailed;
  }

  @GetMapping("/list")
  public PagedResponse<PostResponseBrief> getAllPosts(
      int page,
      @RequestParam(defaultValue = "1") int size,
      @ApiParam(defaultValue = "id",
          value = "Sorting options",
          allowableValues = "id, title, content, createdAt, updatedAt") String sort,
      @ApiParam(defaultValue = "asc",
          value = "Order options",
          allowableValues = "asc, desc") String order) {

    Page<Post> pagePosts = postService.getAllPosts(page, size, sort, order);
    Page<PostResponseBrief> postResponseBriefs = pagePosts.map(
        post -> mapper.map(post, PostResponseBrief.class));

    PagedResponse<PostResponseBrief> pagedResponse = new PagedResponse<>();
    pagedResponse.setCurrentPage(postResponseBriefs.getNumber());
    pagedResponse.setItemsPerPage(postResponseBriefs.getNumberOfElements());
    pagedResponse.setTotalItems(postResponseBriefs.getTotalElements());
    pagedResponse.setItems(postResponseBriefs.getContent());

    return pagedResponse;
  }

  @PutMapping("/update/{postId}")
  public PostResponseBrief updatePost(@Valid @RequestBody PostRequest postRequest,
      @PathVariable long postId) {

    Post post = postService.updatePost(mapper.map(postRequest, Post.class), postId);
    PostResponseBrief postResponseBrief = mapper.map(post, PostResponseBrief.class);

    return postResponseBrief;
  }

  @DeleteMapping("/delete/{postId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deletePost(@PathVariable long postId) {
    postService.deletePost(postId);
  }
}