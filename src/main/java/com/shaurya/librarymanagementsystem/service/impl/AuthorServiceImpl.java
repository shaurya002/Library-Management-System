package com.shaurya.librarymanagementsystem.service.impl;

import com.shaurya.librarymanagementsystem.dto.request.AuthorRequest;
import com.shaurya.librarymanagementsystem.dto.response.AuthorResponse;
import com.shaurya.librarymanagementsystem.exception.AuthorNotFoundException;
import com.shaurya.librarymanagementsystem.mapper.AuthorMapper;
import com.shaurya.librarymanagementsystem.model.entity.Author;
import com.shaurya.librarymanagementsystem.repositories.AuthorRepository;
import com.shaurya.librarymanagementsystem.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    @Transactional
    public AuthorResponse createAuthor(AuthorRequest authorRequest){
        if(authorRepository.existsByName(authorRequest.name())){
            throw new DuplicateAuthorException("Author with name '" + authorRequest.name() + "' already exists.");
        }
        Author author = authorMapper.toEntity(authorRequest);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toResponse(savedAuthor);
    }

    @Override
    @Transactional
    public AuthorResponse updateAuthor(Long id, AuthorRequest authorRequest) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new AuthorNotFoundException("Author not found with id: " + id));
        if (!author.getName().equals(authorRequest.name())
                && authorRepository.existsByName(authorRequest.name())) {

            throw new DuplicateAuthorException(
                    "Author with name '" + authorRequest.name() + "' already exists."
            );
        }
        author.setName(authorRequest.name());
        author.setBiography(authorRequest.biography());
        Author updatedAuthor = authorRepository.save(author);
        return authorMapper.toResponse(updatedAuthor);
    }

    @Override
    @Transactional
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));

        if (!author.getBooks().isEmpty()) {
            throw new AuthorDeletionException(
                    "Cannot delete author because they are associated with existing books."
            );
        }
        authorRepository.delete(author);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + id));
        return authorMapper.toResponse(author);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse getAuthorByName(String name) {
        Author author = authorRepository.findByName(name)
                .orElseThrow(() ->
                        new AuthorNotFoundException(
                                "Author with name '" + name + "' not found."
                        ));

        return authorMapper.toResponse(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuthorResponse> getAllAuthor() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toResponse)
                .collect(Collectors.toList());
    }
}
