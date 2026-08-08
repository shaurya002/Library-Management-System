package com.shaurya.librarymanagementsystem.service;

import com.shaurya.librarymanagementsystem.dto.request.BorrowRecordRequest;
import com.shaurya.librarymanagementsystem.dto.response.BorrowRecordResponse;

import java.util.List;

public interface BorrowRecordService {

    BorrowRecordResponse borrowBook(BorrowRecordRequest request);

    BorrowRecordResponse returnBook(Long borrowRecordId);

    BorrowRecordResponse getBorrowRecordById(Long borrowRecordId);

    List<BorrowRecordResponse> getBorrowRecordsByMember(Long memberId);

    List<BorrowRecordResponse> getBorrowRecordsByBook(Long bookId);

    List<BorrowRecordResponse> getActiveBorrowRecordsByMember(Long memberId);

    List<BorrowRecordResponse> getOverdueBorrowRecords();

    List<BorrowRecordResponse> getAllBorrowRecords();

}
