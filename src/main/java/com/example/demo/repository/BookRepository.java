package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorEmail(String authorEmail);
    
    //==============used to add links to the book not used now later use=====================
    
//    Optional<Book> findByTitle(String title);
//    
//    Optional<Book> findByLink(String link);

}
