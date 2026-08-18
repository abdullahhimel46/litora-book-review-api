package com.litora.bookreview.controller;


import com.litora.bookreview.dto.BookRequest;
import com.litora.bookreview.dto.BookResponse;
import com.litora.bookreview.model.Book;
import com.litora.bookreview.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
@Tag(name = "Book management API", description = "Endpoints for managing books using Spring JDBC")
public class BookController {
    private final BookService bookService;

    // create a new book
    @PostMapping
    @Operation(summary = "Create a new book")
    public BookResponse createBook(@RequestBody BookRequest book) {
        return bookService.createBook(book);
    }

    // get all books
    @GetMapping()
    @Operation(summary = "Get all books")
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    // get a book by id
    @GetMapping("/{id}")
    @Operation(summary = "Get a book by id")
    public BookResponse getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    // update a book by id
    @PutMapping("/{id}")
    @Operation(summary = "Update a book by id")
    public BookResponse updateBook(@PathVariable Long id, @RequestBody BookRequest book) {
        return bookService.updateBook(id, book);
    }

    // delete a book by id
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a book by id")
    public String deleteBook(@PathVariable Long id) {
        return bookService.deleteBook(id);
    }


}
