package com.shaurya.librarymanagementsystem.service;

import com.shaurya.librarymanagementsystem.dto.request.UserRequest;
import com.shaurya.librarymanagementsystem.dto.response.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest request);
}
