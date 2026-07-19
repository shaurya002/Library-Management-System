package com.shaurya.librarymanagementsystem.repositories;

import com.shaurya.librarymanagementsystem.model.entity.Book;
import com.shaurya.librarymanagementsystem.model.entity.BorrowRecord;
import com.shaurya.librarymanagementsystem.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BorrowRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByMember(Member member);

    List<BorrowRecord> findByBook(Book book);

    List<BorrowRecord> findByMemberAndReturnDateIsNull(Member member);

    List<BorrowRecord> findByDueDateBeforeAndReturnDateIsNull(LocalDate today);

    long countByMemberAndReturnDateIsNull(Member member);

    boolean existsByMemberAndReturnDateIsNull(Member member);
}
