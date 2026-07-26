package com.shaurya.librarymanagementsystem.controller;

import com.shaurya.librarymanagementsystem.dto.request.BookRequest;
import com.shaurya.librarymanagementsystem.dto.response.BookResponse;
import com.shaurya.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookResponse> createBook(@Valid @RequestBody BookRequest bookRequest){
        BookResponse bookResponse = bookService.createBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookResponse);
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks(){
        List<BookResponse> bookResponse = bookService.getAllBooks();
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        BookResponse bookResponse = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequest bookRequest){
        BookResponse bookResponse = bookService.updateBook(id, bookRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
