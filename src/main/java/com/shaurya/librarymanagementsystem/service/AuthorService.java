package com.shaurya.librarymanagementsystem.service;

import com.shaurya.librarymanagementsystem.dto.request.AuthorRequest;
import com.shaurya.librarymanagementsystem.dto.response.AuthorResponse;
import com.shaurya.librarymanagementsystem.dto.response.PageResponse;

public interface AuthorService {

    AuthorResponse createAuthor(AuthorRequest authorRequest);

    AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest);

    void deleteAuthor(Long id);

    AuthorResponse getAuthorById(Long id);

    PageResponse<AuthorResponse> findByName(String name, int page);

    PageResponse<AuthorResponse> getAllAuthors(int page);
}
