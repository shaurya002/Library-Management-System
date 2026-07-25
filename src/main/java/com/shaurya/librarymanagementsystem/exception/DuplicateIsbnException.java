package com.shaurya.librarymanagementsystem.exception;

public class DuplicateIsbnException extends RuntimeException{
    public DuplicateIsbnException(String message){
        super(message);
    }
}
