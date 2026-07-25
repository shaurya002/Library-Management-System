package com.shaurya.librarymanagementsystem.service.impl;

import com.shaurya.librarymanagementsystem.dto.request.BookRequest;
import com.shaurya.librarymanagementsystem.dto.response.BookResponse;
import com.shaurya.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Override
    public BookResponse createBook(BookRequest bookRequest){
        return null;
    }

    @Override
    public BookResponse updateBook(Long id, BookRequest request){
        return null;
    }

    @Override
    public void deleteBook(Long id){

    }

    @Override
    public BookResponse getBookById(Long id){
        return null;
    }

    @Override
    public List<BookResponse> getAllBooks(){
        return null;
    }


}
