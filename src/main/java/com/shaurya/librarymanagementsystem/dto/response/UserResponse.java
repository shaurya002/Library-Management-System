package com.shaurya.librarymanagementsystem.dto.response;

import com.shaurya.librarymanagementsystem.model.enums.Role;

public record UserResponse(
        Long id,
        String username,
        Role role,
        Long memberId
) {
}
