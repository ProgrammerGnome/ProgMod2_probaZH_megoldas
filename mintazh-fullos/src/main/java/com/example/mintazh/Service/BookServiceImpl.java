package com.example.mintazh.Service;

import com.example.mintazh.Dto.BookDto;
import com.example.mintazh.Entity.Book;
import com.example.mintazh.Exception.BookNotFoundException;
import com.example.mintazh.Mapper.BookMapper;
import com.example.mintazh.Repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "fileLogger")
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    private final BookMapper bookMapper;

    String errorMessage = "Hibás vagy nem létező ID.";

    @Override
    @Transactional
    public List<BookDto> findAllBooks() {
        return bookMapper.toDto(bookRepository.findAll());
    }

    @Override
    @Transactional
    public BookDto findBooksById(Long id) {
        return bookMapper.toDto(getBookById(id, "Nem találatm megfelelő könyvet az ID alapján."));
    }

    @Override
    @Transactional
    public BookDto saveNewBook(BookDto bookDto) {
        Book book = bookMapper.toEntity(bookDto);
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateBook(BookDto bookDto) {
        Long id = bookDto.getId();

        //Book book = bookRepository.findById(id).get();

        Book book = getBookById(id, errorMessage);

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setReleaseDate(bookDto.getReleaseDate());
        book.setPrice(bookDto.getPrice());
        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateBookById(Long id, BookDto bookDto) {
        bookDto.setId(id);
        return updateBook(bookDto);
    }

    @Override
    @Transactional
    public ResponseEntity<String> deleteBook(Long id) {

        //Book book = bookRepository.findById(id).get();

        Book book = getBookById(id, errorMessage);

        bookRepository.delete(book);
        String infoMessage = "Könyv törölve. ID=" + id + ", könyv neve='" + book.getTitle() + "'.";
        return ResponseEntity.ok(infoMessage);
    }

    @Override
    public Book getBookById(Long id, String errorMessage) {
        return bookRepository.findById(id).orElseThrow(() -> {
            return new BookNotFoundException(errorMessage);
        });
    }

}
