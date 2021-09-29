package com.spring.javainternship.csongorburu.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String content;
  @ManyToOne
  private User user;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "post")
  private List<Comment> comments = new ArrayList<>();
  @ManyToMany
  @JoinTable(
      name = "tag_post",
      joinColumns = @JoinColumn(name = "post_id"),
      foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT),
      inverseJoinColumns = @JoinColumn(name = "tag_id"),
      inverseForeignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT)
  )
  private List<Tag> tags;
  @CreationTimestamp
  private LocalDateTime createdAt;
  @UpdateTimestamp
  private LocalDateTime updatedAt;
}