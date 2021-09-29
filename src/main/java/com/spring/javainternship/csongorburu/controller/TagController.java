package com.spring.javainternship.csongorburu.controller;

import com.spring.javainternship.csongorburu.dto.TagRequest;
import com.spring.javainternship.csongorburu.dto.TagResponse;
import com.spring.javainternship.csongorburu.entity.Tag;
import com.spring.javainternship.csongorburu.service.TagService;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

  private final TagService tagService;
  private final Mapper mapper;

  @GetMapping("/list")
  public List<TagResponse> getAllTags() {

    List<Tag> tagList = tagService.getAllTags();

    return tagList.stream().map(tag -> mapper.map(tag, TagResponse.class)).collect(
        Collectors.toList());
  }

  @GetMapping("/getone/{tagId}")
  public TagResponse getTagById(@PathVariable long tagId) {

    Tag tag = tagService.getTagById(tagId);
    TagResponse tagResponse = mapper.map(tag, TagResponse.class);

    return tagResponse;
  }

  @PostMapping("/create")
  @ResponseStatus(HttpStatus.CREATED)
  public TagResponse createTag(@Valid @RequestBody TagRequest tagRequest) {

    Tag tag = mapper.map(tagRequest, Tag.class);
    Tag tagSaved = tagService.createTag(tag);
    TagResponse tagResponse = mapper.map(tagSaved, TagResponse.class);
    return tagResponse;
  }

  @DeleteMapping("/delete/{tagId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteTag(@PathVariable long tagId) {
    tagService.deleteTag(tagId);
  }
}