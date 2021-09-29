package com.spring.javainternship.csongorburu.utility;

import com.spring.javainternship.csongorburu.dto.CommentRequest;
import com.spring.javainternship.csongorburu.dto.CommentResponse;
import com.spring.javainternship.csongorburu.dto.PostRequest;
import com.spring.javainternship.csongorburu.dto.PostResponseDetailed;
import com.spring.javainternship.csongorburu.entity.Comment;
import com.spring.javainternship.csongorburu.entity.Post;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class Mapper {

  private final MapperFacade mapperFacade;
  private final MapperFactory mapperFactory;

  public Mapper() {
    mapperFactory = new DefaultMapperFactory.Builder().mapNulls(false).build();
    defineMappingFields();
    mapperFacade = mapperFactory.getMapperFacade();
  }

  private void defineMappingFields() {

    mapperFactory.classMap(PostResponseDetailed.class, Post.class)
        .field("tagResponses{id}", "tags{id}")
        .field("tagResponses{name}", "tags{name}")
        .field("commentResponseBriefs{userId}", "comments{user.id}")
        .field("commentResponseBriefs{text}", "comments{text}")
        .byDefault()
        .register();
    mapperFactory.classMap(PostRequest.class, Post.class)
        .field("userId", "user.id")
        .field("tagIds{}", "tags{id}")
        .byDefault()
        .register();
    mapperFactory.classMap(CommentRequest.class, Comment.class)
        .field("userId", "user.id")
        .field("postId", "post.id")
        .byDefault()
        .register();
    mapperFactory.classMap(CommentResponse.class, Comment.class)
        .field("userId", "user.id")
        .field("postId", "post.id")
        .byDefault()
        .register();
  }

  public <S, D> D map(S source, Class<D> destinationClass) {
    return mapperFacade.map(source, destinationClass);
  }

  public <S, D> void map(S source, D destination) {
    mapperFacade.map(source, destination);
  }
}