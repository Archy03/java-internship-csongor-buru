package com.spring.javainternship.csongorburu.service;

import com.spring.javainternship.csongorburu.entity.Tag;
import com.spring.javainternship.csongorburu.exception.ResourceNotFoundException;
import com.spring.javainternship.csongorburu.repository.TagRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TagService {

  private final TagRepository tagRepository;

  public Tag createTag(Tag tag) {
    return tagRepository.save(tag);
  }

  public Tag getTagById(long id) {
    return tagRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Tag not found with id: " + id));
  }

  public List<Tag> getAllTags() {
    return tagRepository.findAll();
  }

  public void deleteTag(long id) {

    if (tagRepository.existsById(id)) {
      tagRepository.deleteById(id);
    }
  }
}
