package com.shaurya.librarymanagementsystem.service;

import com.shaurya.librarymanagementsystem.dto.request.BookRequest;
import com.shaurya.librarymanagementsystem.dto.response.BookResponse;
import com.shaurya.librarymanagementsystem.model.enums.BookStatus;

import java.util.List;

public interface BookService {

    BookResponse createBook(BookRequest request);
    BookResponse updateBook(Long id, BookRequest request);
    void deleteBook(Long id);
    BookResponse getBookById(Long id);
    List<BookResponse> findByTitle(String title);
    List<BookResponse> findByPublishedYearBetween(Integer startYear, Integer endYear);
    List<BookResponse> findByAuthors_NameContaining(String authorName);
    List<BookResponse> findByGenre(String genre);
    List<BookResponse> findByStatus(BookStatus status);
    BookResponse findByIsbn(String isbn);
    List<BookResponse> getAllBooks();

}
