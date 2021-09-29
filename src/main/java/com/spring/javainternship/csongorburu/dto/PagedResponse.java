package com.spring.javainternship.csongorburu.dto;

import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResponse<T> {

  private int currentPage;
  private int itemsPerPage;
  private long totalItems;
  private Collection<T> items;
}
