package com.example.mintazh.Service;

import com.example.mintazh.Dto.BookDto;
import com.example.mintazh.Entity.Book;
import com.example.mintazh.Mapper.BookMapper;
import com.example.mintazh.Repository.BookRepository;
import com.example.mintazh.exception.BookNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    @Transactional
    public List<BookDto> findAllBooks() {
        return bookMapper.toDto(bookRepository.findAll());
    }

    @Transactional
    public BookDto findBooksById(Long id) {
        return bookMapper.toDto(getBookById(id, "csótány"));
    }

    @Transactional
    public BookDto saveNewBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    public BookDto updateBook(BookDto bookDto) {
        Long id = bookDto.getId();

        //Book book = bookRepository.findById(id).get();

        Book book = getBookById(id, "A felülírás hibás vagy nem létező ID miatt nem történt meg.");

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseDate(bookDto.getReleaseDate());
        book.setPrice(bookDto.getPrice());
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Transactional
    public ResponseEntity<String> deleteBook(Long id) {

        //Book book = bookRepository.findById(id).get();

        Book book = getBookById(id, "Nem létezik olyan ID-val könyv, amivel tötölni szeretne!");

        bookRepository.delete(book);
        String infoMessage = "Könyv törölve. ID=" + id + ", könyv neve='" + book.getTitle() + "'.";
        return ResponseEntity.ok(infoMessage);
    }

    private Book getBookById(Long id, String errorMessage) {
        return bookRepository.findById(id).orElseThrow(() -> {
            return new BookNotFoundException(errorMessage);
        });
    }

}
