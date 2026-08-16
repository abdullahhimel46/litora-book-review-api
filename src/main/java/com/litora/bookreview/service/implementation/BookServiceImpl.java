package com.litora.bookreview.service.implementation;

import com.litora.bookreview.dto.BookRequest;
import com.litora.bookreview.dto.BookResponse;
import com.litora.bookreview.mapper.BookMapper;
import com.litora.bookreview.model.Book;
import com.litora.bookreview.repository.BookRepository;
import com.litora.bookreview.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public BookResponse createBook(BookRequest request) {
        // check if the book already exists in the database by title and author
        boolean existingBook = bookRepository.existsByTitleAndAuthor(
                request.title(), request.author());
        if (existingBook) {
            throw new RuntimeException(
                    "Book already exists with title: " + request.title() + " and author: " + request.author());
        }

        Book book = BookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);

        return BookMapper.toResponse(savedBook);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().
                stream().
                map(BookMapper::toResponse).
                toList();
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        return BookMapper.toResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        BookMapper.updateEntity(existingBook, book);

        return BookMapper.toResponse(bookRepository.save(existingBook));
    }

    @Override
    public String deleteBook(Long id) {
        boolean exists = bookRepository.existsById(id);

        if (!exists) {
            throw new RuntimeException("Book not found with id: " + id);
        }

        bookRepository.deleteById(id);
        return "Book deleted successfully with id: " + id;
    }
}
