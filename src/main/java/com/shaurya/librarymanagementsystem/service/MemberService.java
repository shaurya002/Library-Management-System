package com.shaurya.librarymanagementsystem.service;

import com.shaurya.librarymanagementsystem.dto.request.MemberRequest;
import com.shaurya.librarymanagementsystem.dto.response.MemberResponse;
import com.shaurya.librarymanagementsystem.model.enums.MemberStatus;

import java.time.LocalDate;
import java.util.List;

public interface MemberService {

    MemberResponse createMember(MemberRequest request);
    MemberResponse updateMember(Long id, MemberRequest request);
    void deleteMember(Long id);
    MemberResponse getMemberById(Long id);
    MemberResponse getMemberByEmail(String email);
    List<MemberResponse> getAllMembers();
    List<MemberResponse> findByMemberStatus(MemberStatus memberStatus);
    List<MemberResponse> findByMembershipDateBetween(
            LocalDate startDate,
            LocalDate endDate
    );
}
