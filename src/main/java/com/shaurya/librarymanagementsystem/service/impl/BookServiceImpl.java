package com.shaurya.librarymanagementsystem.service.impl;

import com.shaurya.librarymanagementsystem.dto.request.BookRequest;
import com.shaurya.librarymanagementsystem.dto.response.BookResponse;
import com.shaurya.librarymanagementsystem.exception.AuthorNotFoundException;
import com.shaurya.librarymanagementsystem.exception.DuplicateIsbnException;
import com.shaurya.librarymanagementsystem.mapper.BookMapper;
import com.shaurya.librarymanagementsystem.model.entity.Author;
import com.shaurya.librarymanagementsystem.model.entity.Book;
import com.shaurya.librarymanagementsystem.model.enums.BookStatus;
import com.shaurya.librarymanagementsystem.repositories.BookRepository;
import com.shaurya.librarymanagementsystem.service.BookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    @Transactional
    public BookResponse createBook(BookRequest bookRequest){
        if(bookRepository.existsByIsbn(bookRequest.isbn())){
            throw new DuplicateIsbnException("Book with ISBN " + bookRequest.isbn() + " already exists.");
        }
        List<Author> authors = authorRepository.findAllById(bookRequest.authorIds());
        if(authors.size()!=bookRequest.authorIds().size()){
            throw new AuthorNotFoundException("Author Ids do not match.");
        }
        Book book = bookMapper.toEntity(
                bookRequest,
                new HashSet<>(authors)
        );

        book.setAvailableCopies(book.getTotalCopies());
        book.setStatus(BookStatus.AVAILABLE);

        Book savedBook = bookRepository.save(book);
        return bookMapper.toResponse(savedBook);
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request){
        return null;
    }

    @Override
    public void deleteBook(Long id){

    }

    @Override
    public BookResponse getBookById(Long id){
        return null;
    }

    @Override
    public List<BookResponse> getAllBooks(){
        return null;
    }


}
