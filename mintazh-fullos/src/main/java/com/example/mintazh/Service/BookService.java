package com.example.mintazh.Service;

import com.example.mintazh.Dto.BookDto;
import com.example.mintazh.Entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    List<BookDto> findAllBooks();
    BookDto findBooksById(Long id);
    BookDto saveNewBook(BookDto bookDto);
    BookDto updateBook(BookDto bookDto);
    BookDto updateBookById(Long id, BookDto bookDto);
    ResponseEntity<String> deleteBook(Long id);
    Book getBookById(Long id, String errorMessage);
}
