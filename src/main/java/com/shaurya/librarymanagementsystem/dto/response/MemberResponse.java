package com.shaurya.librarymanagementsystem.dto.response;

import com.shaurya.librarymanagementsystem.model.enums.MemberStatus;

import java.time.LocalDate;

public record MemberResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        LocalDate membershipDate,
        MemberStatus memberStatus
) {
}
