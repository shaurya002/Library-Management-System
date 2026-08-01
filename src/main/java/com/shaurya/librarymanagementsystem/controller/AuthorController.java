package com.shaurya.librarymanagementsystem.controller;

import com.shaurya.librarymanagementsystem.dto.request.AuthorRequest;
import com.shaurya.librarymanagementsystem.dto.response.AuthorResponse;
import com.shaurya.librarymanagementsystem.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    @PostMapping
    public ResponseEntity<AuthorResponse> addAuthor(@Valid @RequestBody AuthorRequest authorRequest){
        AuthorResponse authorResponse = authorService.createAuthor(authorRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthor(@PathVariable Long id){
        AuthorResponse authorResponse = authorService.getAuthorById(id);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequest authorRequest){
        AuthorResponse authorResponse = authorService.updateAuthor(id, authorRequest);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponse);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AuthorResponse> getAuthorByName(@PathVariable String name){
        AuthorResponse authorResponse = authorService.getAuthorByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponse);
    }

    @GetMapping
    public ResponseEntity<List<AuthorResponse>> getAllAuthors(){
        List<AuthorResponse> authorResponses = authorService.getAllAuthors();
        return ResponseEntity.status(HttpStatus.OK).body(authorResponses);
    }
}
