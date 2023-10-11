package com.example.mintazh.Mapper;

import com.example.mintazh.Dto.BookDto;
import com.example.mintazh.Entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper extends EntityMapper<BookDto, Book> {

    Book toEntity(BookDto dto);

}
