package com.litora.bookreview.mapper;

import com.litora.bookreview.dto.BookRequest;
import com.litora.bookreview.dto.BookResponse;
import com.litora.bookreview.model.Book;

public class BookMapper {

    public static Book toEntity(BookRequest request) {
        Book book = new Book();

        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setGenre(request.genre());
        book.setPublishedYear(request.publishedYear());

        return book;
    }

    public static BookResponse toResponse(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getAuthor(),
                book.getGenre(),
                book.getPublishedYear()
        );
    }

    public static void updateEntity(Book book, BookRequest request) {
        book.setTitle(request.title());
        book.setAuthor(request.author());
        book.setGenre(request.genre());
        book.setPublishedYear(request.publishedYear());
    }

}
