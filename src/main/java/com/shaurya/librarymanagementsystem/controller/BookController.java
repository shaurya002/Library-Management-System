package com.shaurya.librarymanagementsystem.controller;

import com.shaurya.librarymanagementsystem.dto.request.BookRequest;
import com.shaurya.librarymanagementsystem.dto.response.BookResponse;
import com.shaurya.librarymanagementsystem.dto.response.PageResponse;
import com.shaurya.librarymanagementsystem.model.enums.BookStatus;
import com.shaurya.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<PageResponse<BookResponse>> getAllBooks(@RequestParam(defaultValue = "0") int page){
        PageResponse<BookResponse> bookResponse = bookService.getAllBooks(page);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(@PathVariable Long id){
        BookResponse bookResponse = bookService.getBookById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @GetMapping("/search/title")
    public ResponseEntity<PageResponse<BookResponse>> findByTitle(@RequestParam String title, @RequestParam(defaultValue = "0") int page){
        PageResponse<BookResponse> bookResponse = bookService.findByTitle(title, page);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @GetMapping("/search/author")
    public ResponseEntity<PageResponse<BookResponse>> findByAuthorName(@RequestParam String authorName, @RequestParam(defaultValue = "0") int page){
        PageResponse<BookResponse> bookResponse = bookService.findByAuthors_NameContaining(page, authorName);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @GetMapping("/search/year")
    public ResponseEntity<PageResponse<BookResponse>> findByPublishedYearBetween(@RequestParam(defaultValue = "0") int page, @RequestParam Integer startYear, @RequestParam Integer endYear){
        PageResponse<BookResponse> bookResponse = bookService.findByPublishedYearBetween(page, startYear, endYear);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @GetMapping("/search/genre")
    public ResponseEntity<PageResponse<BookResponse>> findByGenre(@RequestParam(defaultValue = "0") int page, @RequestParam String genre){
        PageResponse<BookResponse> bookResponse = bookService.findByGenre(page, genre);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @GetMapping("/search/status")
    public ResponseEntity<PageResponse<BookResponse>> findByStatus(@RequestParam(defaultValue = "0") int page, @RequestParam BookStatus status){
        PageResponse<BookResponse> bookResponse = bookService.findByStatus(status, page);
        return ResponseEntity.status(HttpStatus.OK)
                .body(bookResponse);
    }

    @PutMapping("/{id}")
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
