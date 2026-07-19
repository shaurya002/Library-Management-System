package com.shaurya.librarymanagementsystem.repositories;

import com.shaurya.librarymanagementsystem.model.entity.Book;
import com.shaurya.librarymanagementsystem.model.enums.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByTitleContainingIgnoreCase(String title);

    List <Book> findByGenre(String genre);

    List<Book> findByStatus(BookStatus status);

    boolean existsByIsbn(String isbn);

    List<Book> findByAuthors_NameContaining(String name);

    List<Book> findByPublishedYearBetween(Integer startYear, Integer endYear);
}
