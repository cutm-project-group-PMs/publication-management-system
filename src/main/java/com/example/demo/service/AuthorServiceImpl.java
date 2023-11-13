package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

  //===================================Sign up ==========================

    @Override
    public Author signup(Author author) {
        // Check if the email is already registered
        if (authorRepository.findByEmail(author.getEmail()).isPresent()) {
            throw new RuntimeException("Email is already registered");
        }

        // Save the new author
        return authorRepository.save(author);
    }

    
  //===================================Login ==========================

    @Override
    public Author login(String email, String password) {
        // Validate credentials and return the author
        return authorRepository.findByEmail(email)
                .filter(author -> author.getPassword().equals(password))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
//===================================get all by book by Author email==========================
    @Override
    public List<Book> getAllBooks(String authorEmail) throws AuthorNotFoundException{
        
            return bookRepository.findByAuthorEmail(authorEmail);
    }
    
  //===================================Add book==========================

    @Override
    public Book addBook(String authorEmail, Book book) {
        // Get the author by email
        Author author = authorRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Set the author for the book
         book.setAuthor(author);
         book.setTitle(book.getTitle());
         book.setDescription(book.getDescription());
         book.setAuthor(author);
         book.setLink(book.getLink()); // new field


        // Save the new book
        return bookRepository.save(book);
    }
  //===================================Delete book by author email, book id=========================

    @Override
    public void deleteBook(String authorEmail, Long bookId) {
        // Get the author by email
        Author author = authorRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Get the book by id
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if the book belongs to the author
        if (!book.getAuthor().equals(author)) {
            throw new RuntimeException("You are not authorized to delete this book");
        }

        // Delete the book
        bookRepository.delete(book);
    }
  //==================================update book by authorEmail, authorId========================

    @Override
    public Book updateBook(String authorEmail, Long bookId, Book updatedBook) {
        // Get the author by email
        Author author = authorRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new RuntimeException("Author not found"));

        // Get the existing book by id
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if the book belongs to the author
        if (!existingBook.getAuthor().equals(author)) {
            throw new RuntimeException("You are not authorized to update this book");
        }

        // Update the book details
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setDescription(updatedBook.getDescription());
        existingBook.setLink(updatedBook.getLink());
        // Save the updated book
        return bookRepository.save(existingBook);
    }
}
