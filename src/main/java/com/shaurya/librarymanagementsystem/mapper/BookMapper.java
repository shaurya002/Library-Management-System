package com.shaurya.librarymanagementsystem.mapper;

import com.shaurya.librarymanagementsystem.dto.request.BookRequest;
import com.shaurya.librarymanagementsystem.dto.response.BookResponse;
import com.shaurya.librarymanagementsystem.model.entity.Author;
import com.shaurya.librarymanagementsystem.model.entity.Book;
import com.shaurya.librarymanagementsystem.model.enums.BookStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class BookMapper {

    public Book toEntity(BookRequest request, Set<Author> authors){

        return Book.builder()
                .title(request.title())
                .isbn(request.isbn())
                .genre(request.genre())
                .publishedYear(request.publishedYear())
                .totalCopies(request.totalCopies())
                .availableCopies(request.totalCopies())
                .status(BookStatus.AVAILABLE)
                .authors(authors)
                .build();
    }

    public BookResponse toResponse(Book book){
        List<String> authors = book.getAuthors()
                .stream()
                .map(Author::getName)
                .toList();

        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getGenre(),
                book.getPublishedYear(),
                book.getTotalCopies(),
                book.getAvailableCopies(),
                book.getStatus(),
                authors
        );
    }
}
