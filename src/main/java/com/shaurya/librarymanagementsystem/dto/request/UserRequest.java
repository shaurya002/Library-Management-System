package com.shaurya.librarymanagementsystem.dto.request;

import com.shaurya.librarymanagementsystem.model.enums.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest(
        @NotBlank
        String username,

        @NotBlank
        String password,

        @NotNull
        Role role,

        Long memberId
) {
}
