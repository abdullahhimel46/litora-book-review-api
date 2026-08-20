package com.litora.bookreview.mapper;

import com.litora.bookreview.dto.BookRequest;
import com.litora.bookreview.dto.BookResponse;
import com.litora.bookreview.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookRequest request);

    BookResponse toResponse(Book book);

    void updateEntity(@MappingTarget Book book, BookRequest request);

}
