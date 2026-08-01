package com.shaurya.librarymanagementsystem.service;

import com.shaurya.librarymanagementsystem.dto.request.AuthorRequest;
import com.shaurya.librarymanagementsystem.dto.response.AuthorResponse;

import java.util.List;

public interface AuthorService {

    AuthorResponse createAuthor(AuthorRequest authorRequest);

    AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest);

    void deleteAuthor(Long id);

    AuthorResponse getAuthorById(Long id);

    AuthorResponse getAuthorByName(String name);

    List<AuthorResponse> getAllAuthors();
}
