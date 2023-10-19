package com.example.mintazh.Mapper;

import com.example.mintazh.Dto.BookDto;
import com.example.mintazh.Entity.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper extends EntityMapper<BookDto, Book> {

    // Működik enélkül is!
    /*
    Book toEntity(BookDto dto);
    BookDto toDto(Book book);
    */

}
