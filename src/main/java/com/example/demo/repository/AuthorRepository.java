package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Optional<Author> findByEmail(String email);
}
