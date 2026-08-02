package com.shaurya.librarymanagementsystem.repositories;

import com.shaurya.librarymanagementsystem.model.entity.Member;
import com.shaurya.librarymanagementsystem.model.enums.MemberStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    List<Member> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName,
            String lastName
    );

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Member> findByMemberStatus(MemberStatus memberStatus);
    List<Member> findByMembershipDateBetween(LocalDate startDate, LocalDate endDate);

}
