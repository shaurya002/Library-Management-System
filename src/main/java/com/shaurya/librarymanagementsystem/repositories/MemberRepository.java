package com.shaurya.librarymanagementsystem.repositories;

import com.shaurya.librarymanagementsystem.model.entity.Member;
import com.shaurya.librarymanagementsystem.model.enums.MemberStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    Page<Member> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable
    );

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    Page<Member> findByMemberStatus(MemberStatus memberStatus, Pageable pageable);
    Page<Member> findByMembershipDateBetween(LocalDate startDate, LocalDate endDate, Pageable pageable);

}
