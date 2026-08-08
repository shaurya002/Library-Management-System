package com.shaurya.librarymanagementsystem.controller;

import com.shaurya.librarymanagementsystem.dto.request.BorrowRecordRequest;
import com.shaurya.librarymanagementsystem.dto.response.BorrowRecordResponse;
import com.shaurya.librarymanagementsystem.service.BorrowRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/borrow-records")
public class BorrowRecordController {

    private final BorrowRecordService borrowRecordService;

    @PostMapping
    public ResponseEntity<BorrowRecordResponse> createBorrowRecord(@Valid @RequestBody BorrowRecordRequest borrowRecordRequest){
        BorrowRecordResponse response = borrowRecordService.borrowBook(borrowRecordRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{borrowId}/return")
    public ResponseEntity<BorrowRecordResponse> returnBorrowRecord(@PathVariable Long borrowId){
        BorrowRecordResponse response = borrowRecordService.returnBook(borrowId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{borrowId}")
    public ResponseEntity<BorrowRecordResponse> getBorrowRecord(@PathVariable Long borrowId){
        BorrowRecordResponse response = borrowRecordService.getBorrowRecordById(borrowId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BorrowRecordResponse>> getAllBorrowRecord(){
        List<BorrowRecordResponse> response = borrowRecordService.getAllBorrowRecords();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<BorrowRecordResponse>> getBorrowRecordsByMemberId(@PathVariable Long memberId){
        List<BorrowRecordResponse> response = borrowRecordService.getBorrowRecordsByMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<List<BorrowRecordResponse>> getBorrowRecordsByBookId(@PathVariable Long bookId){
        List<BorrowRecordResponse> response = borrowRecordService.getBorrowRecordsByBook(bookId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/members/{memberId}/active")
    public ResponseEntity<List<BorrowRecordResponse>> getActiveRecordsByMemberId(@PathVariable Long memberId){
        List<BorrowRecordResponse> response = borrowRecordService.getActiveBorrowRecordsByMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/overdueRecords")
    public ResponseEntity<List<BorrowRecordResponse>> getOverdueRecords(){
        List<BorrowRecordResponse> response = borrowRecordService.getOverdueBorrowRecords();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
