package com.litora.bookreview.service;

import com.litora.bookreview.dto.BookRequest;
import com.litora.bookreview.dto.BookResponse;
import com.litora.bookreview.model.Book;

import java.util.List;

public interface BookService {

    BookResponse createBook(BookRequest book);

    List<BookResponse> getAllBooks();

    BookResponse getBookById(Long id);

    BookResponse updateBook(Long id, BookRequest book);

    String deleteBook(Long id);
}
