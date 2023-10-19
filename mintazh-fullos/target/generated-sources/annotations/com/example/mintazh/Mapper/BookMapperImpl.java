package com.example.mintazh.Mapper;

import com.example.mintazh.Dto.BookDto;
import com.example.mintazh.Entity.Book;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-19T19:12:18+0200",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.9 (Azul Systems, Inc.)"
)
@Component
public class BookMapperImpl implements BookMapper {

    @Override
    public Book toEntity(BookDto dto) {
        if ( dto == null ) {
            return null;
        }

        Book book = new Book();

        book.setId( dto.getId() );
        book.setTitle( dto.getTitle() );
        book.setAuthor( dto.getAuthor() );
        book.setReleaseDate( dto.getReleaseDate() );
        book.setPrice( dto.getPrice() );

        return book;
    }

    @Override
    public BookDto toDto(Book entity) {
        if ( entity == null ) {
            return null;
        }

        BookDto bookDto = new BookDto();

        bookDto.setId( entity.getId() );
        bookDto.setTitle( entity.getTitle() );
        bookDto.setAuthor( entity.getAuthor() );
        bookDto.setReleaseDate( entity.getReleaseDate() );
        bookDto.setPrice( entity.getPrice() );

        return bookDto;
    }

    @Override
    public List<Book> toEntity(List<BookDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Book> list = new ArrayList<Book>( dtoList.size() );
        for ( BookDto bookDto : dtoList ) {
            list.add( toEntity( bookDto ) );
        }

        return list;
    }

    @Override
    public List<BookDto> toDto(List<Book> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BookDto> list = new ArrayList<BookDto>( entityList.size() );
        for ( Book book : entityList ) {
            list.add( toDto( book ) );
        }

        return list;
    }
}
