package com.litora.bookreview.service.implementation;

import com.litora.bookreview.dto.BookRequest;
import com.litora.bookreview.dto.BookResponse;
import com.litora.bookreview.exception.BookAlreadyExistsException;
import com.litora.bookreview.exception.BookNotFoundException;
import com.litora.bookreview.exception.DeletionFailedException;
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
    private final BookMapper bookMapper;

    @Override
    public BookResponse createBook(BookRequest request) {
        // check if the book already exists in the database by title and author
        boolean existingBook = bookRepository.existsByTitleAndAuthor(
                request.title(), request.author());
        if (existingBook) {
            throw new BookAlreadyExistsException(
                    "Book already exists with title: " + request.title() + " and author: " + request.author());
        }


        Book book = bookMapper.toEntity(request);
        Book savedBook = bookRepository.save(book);

        return bookMapper.toResponse(savedBook);
    }

    @Override
    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().
                stream().
                map(bookMapper::toResponse).
                toList();
    }

    @Override
    public BookResponse getBookById(Long id) {
        Book book = bookRepository.findById(id).
                orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        return bookMapper.toResponse(book);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        bookMapper.updateEntity(existingBook, book);

        return bookMapper.toResponse(bookRepository.save(existingBook));
    }

    @Override
    public String deleteBook(Long id) {
        //case 1: if book doesn't exist in the database or deleted earlier
        boolean exists = bookRepository.existsById(id);

        if (!exists) {
            throw new DeletionFailedException("Deletion failed: Book not found with id: " + id);
        }
        

        // case-2: DB level error: Unexpected error while deleting from the DB
        try{
            bookRepository.deleteById(id);
        } catch (Exception ex) {
            throw new DeletionFailedException("Falied to delete book with id: "+id+".It might be linked to other recoreds.");
        }

        return "Book deleted successfully with id: " + id;
    }
}
