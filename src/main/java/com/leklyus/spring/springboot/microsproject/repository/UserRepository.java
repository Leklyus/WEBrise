package com.leklyus.spring.springboot.microsproject.repository;

import com.leklyus.spring.springboot.microsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
