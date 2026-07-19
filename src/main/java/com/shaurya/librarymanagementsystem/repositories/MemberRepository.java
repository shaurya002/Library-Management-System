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

    Optional<Member> findByMemberId(Long memberId);

    List<Member> findByNameContainingIgnoreCase(String memberName);

    Optional<Member> findByEmail(String email);

    boolean existsByEmail(String email);

    List<Member> findByStatus(MemberStatus memberStatus);

    List<Member> findByMembershipDateBetween(LocalDate startDate, LocalDate endDate);

}
