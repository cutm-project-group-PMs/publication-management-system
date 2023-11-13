package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Author;
import com.example.demo.model.Book;

public interface AuthorService {

    Author signup(Author author);

    Author login(String email, String password);

   List<Book> getAllBooks(String authorEmail) throws AuthorNotFoundException;
    
    

    Book addBook(String authorEmail, Book book);

    void deleteBook(String authorEmail, Long bookId);

    Book updateBook(String authorEmail, Long bookId, Book updatedBook);
}
