package com.spring.javainternship.csongorburu.repository;

import com.spring.javainternship.csongorburu.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}