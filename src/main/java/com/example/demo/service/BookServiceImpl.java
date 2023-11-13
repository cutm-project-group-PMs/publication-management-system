package com.example.demo.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooksByAuthorEmail(String authorEmail) {
        return bookRepository.findByAuthorEmail(authorEmail);
    }

	@Override
	  public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
