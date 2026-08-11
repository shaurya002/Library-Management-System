package com.shaurya.librarymanagementsystem.controller;

import com.shaurya.librarymanagementsystem.dto.request.AuthorRequest;
import com.shaurya.librarymanagementsystem.dto.response.AuthorResponse;
import com.shaurya.librarymanagementsystem.dto.response.PageResponse;
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

    @GetMapping("/name")
    public ResponseEntity<PageResponse<AuthorResponse>> findAuthorsByName(@RequestParam String name, @RequestParam(defaultValue = "0") int page){
        PageResponse<AuthorResponse> authorResponses = authorService.findByName(name, page);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponses);
    }

    @GetMapping
    public ResponseEntity<PageResponse<AuthorResponse>> getAllAuthors(@RequestParam(defaultValue = "0") int page){
        PageResponse<AuthorResponse> authorResponses = authorService.getAllAuthors(page);
        return ResponseEntity.status(HttpStatus.OK).body(authorResponses);
    }
}
