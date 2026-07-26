package com.shaurya.librarymanagementsystem.service.impl;

import com.shaurya.librarymanagementsystem.dto.request.BookRequest;
import com.shaurya.librarymanagementsystem.dto.response.BookResponse;
import com.shaurya.librarymanagementsystem.exception.AuthorNotFoundException;
import com.shaurya.librarymanagementsystem.exception.BookNotFoundException;
import com.shaurya.librarymanagementsystem.exception.DuplicateIsbnException;
import com.shaurya.librarymanagementsystem.mapper.BookMapper;
import com.shaurya.librarymanagementsystem.model.entity.Author;
import com.shaurya.librarymanagementsystem.model.entity.Book;
import com.shaurya.librarymanagementsystem.model.enums.BookStatus;
import com.shaurya.librarymanagementsystem.repositories.AuthorRepository;
import com.shaurya.librarymanagementsystem.repositories.BookRepository;
import com.shaurya.librarymanagementsystem.service.BookService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;

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
    @Transactional
    public BookResponse updateBook(Long id, BookRequest request){
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        if(request.isbn() != null && !request.isbn().equals(book.getIsbn())){
            if(bookRepository.existsByIsbn(request.isbn())){
                throw new DuplicateIsbnException("Book with ISBN " + request.isbn() + " already exists.");
            }
            book.setIsbn(request.isbn());
        }

        if(request.title() != null){
            book.setTitle(request.title());
        }

        if(request.genre() != null){
            book.setGenre(request.genre());
        }

        if(request.publishedYear() != null){
            book.setPublishedYear(request.publishedYear());
        }

        if(request.totalCopies() != null){
            int difference = request.totalCopies() - book.getTotalCopies();
            book.setTotalCopies(request.totalCopies());
            book.setAvailableCopies(book.getAvailableCopies() + difference);
        }

        if(request.authorIds() != null && !request.authorIds().isEmpty()){
            List<Author> authors = authorRepository.findAllById(request.authorIds());
            if(authors.size()!=request.authorIds().size()){
                throw new AuthorNotFoundException("Author Ids do not match.");
            }
            book.setAuthors(new HashSet<>(authors));
        }

        Book updatedBook = bookRepository.save(book);
        return bookMapper.toResponse(updatedBook);
    }

    @Override
    @Transactional
    public void deleteBook(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with id: " + id));
        bookRepository.delete(book);
    }

    @Override
    @Transactional
    public BookResponse getBookById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new BookNotFoundException(
                                "Book not found with id: " + id));
        return bookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public List<BookResponse> findByTitle(String title){
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<BookResponse> findByAuthors_NameContaining(String authorName){
        List<Book> books = bookRepository.findByAuthors_NameContaining(authorName);
        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<BookResponse> findByPublishedYearBetween(Integer startYear, Integer endYear){
        List<Book> books = bookRepository.findByPublishedYearBetween(startYear,endYear);
        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<BookResponse> findByGenre(String genre) {
        List<Book> books = bookRepository.findByGenre(genre);
        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public List<BookResponse> findByStatus(BookStatus status) {
        List<Book> books = bookRepository.findByStatus(status);
        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public BookResponse findByIsbn(String isbn){
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookNotFoundException("Book with ISBN " + isbn + " not found."));
        return bookMapper.toResponse(book);
    }

    @Override
    @Transactional
    public List<BookResponse> getAllBooks(){
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::toResponse)
                .toList();
    }
}
