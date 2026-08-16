package com.litora.bookreview.service.implementation;

import com.litora.bookreview.model.Book;
import com.litora.bookreview.repository.BookRepository;
import com.litora.bookreview.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public Book createBook(Book book) {
        // check if the book already exists in the database by title and author
        boolean existingBook = bookRepository.existsByTitleAndAuthor(
                book.getTitle(), book.getAuthor()
        );
        if (existingBook) {
            throw new RuntimeException("Book already exists with title: " + book.getTitle() + " and author: " + book.getAuthor());
        }

        return bookRepository.save(book);

    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
//        Optional<Book> book = bookRepository.findById(id);
//        if (book.isPresent()) {
//            return book.get();
//        }else {
//            throw new RuntimeException("Book not found with id: " + id);
//        }
        return bookRepository.findById(id).
                orElseThrow(() -> new RuntimeException
                        ("Book not found with id: " + id)
                );
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (!existingBook.isPresent()) {
            throw new RuntimeException("Book not found with id: " + id);
        }

        Book surelyExistingBook = existingBook.get();

        surelyExistingBook.setTitle(book.getTitle());
        surelyExistingBook.setAuthor(book.getAuthor());
        surelyExistingBook.setGenre(book.getGenre());
        surelyExistingBook.setPublishedYear(book.getPublishedYear());

        return bookRepository.save(surelyExistingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (!existingBook.isPresent()) {
            throw new RuntimeException("Book not found with id: " + id);
        }

        bookRepository.deleteById(id);
    }
}
