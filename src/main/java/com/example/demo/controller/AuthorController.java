package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import com.example.demo.service.AuthorNotFoundException;
import com.example.demo.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
@CrossOrigin(origins = "http://localhost:3000") // Replace with your React app's URL
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    
  //===============================Author signup ======================================================

    @PostMapping("/signup")
    public ResponseEntity<Author> signup(@RequestBody Author author) {
        Author newAuthor = authorService.signup(author);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAuthor);
    }
    
    @GetMapping("/hii")
    public String send() {
    	return "ramaditya07777";
    }
  //===============================Author Login ======================================================

    @PostMapping("/login")
    public ResponseEntity<Author> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Author author = authorService.login(email, password);
        return ResponseEntity.ok(author);
    }

  //===============================Add book to the db ======================================================

    @PostMapping("/addbooks")
    public ResponseEntity<?> addBook(@RequestParam String authorEmail, @RequestBody Book book) {
        try {
            Book newBook = authorService.addBook(authorEmail, book);
         // System.out.println("Boot added");
            return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
        } catch (Exception e) {
            // Log the exception for server-side debugging
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to add book. Please try again.");
        }
    }

//===============================fetch book by email id :  ======================================================

  @GetMapping("/books")
  public ResponseEntity<List<Book>> getAllBooks(@RequestParam String authorEmail) {
      try {
          List<Book> books = authorService.getAllBooks(authorEmail);
          System.out.println("try block");
          return ResponseEntity.ok(books);
      } catch (AuthorNotFoundException e) {
          // Handle the exception and return an appropriate response
          System.out.println("catch block");

          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }
  }
  
//===============================delete book by id======================================================


    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@RequestParam String authorEmail, @PathVariable Long bookId) {
        authorService.deleteBook(authorEmail, bookId);
        return ResponseEntity.noContent().build();
    }

  //===============================update book, update title, descriptiona nd link aslo=====================================================

    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(
            @RequestParam String authorEmail,
            @PathVariable Long bookId,
            @RequestBody Book updatedBook
    ) {
        Book updated = authorService.updateBook(authorEmail, bookId, updatedBook);
        return ResponseEntity.ok(updated);
    }
    //======================================get author Id By authorEmail for create new page and content=======================

	@Autowired
	AuthorRepository authorRepository;
    
    @GetMapping("/byEmail")
    public ResponseEntity<Long> getAuthorIdByEmail(@RequestParam String email) {
        Optional<Author> authorOptional = authorRepository.findByEmail(email);

        return authorOptional.map(author -> ResponseEntity.ok(author.getId()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    
}
