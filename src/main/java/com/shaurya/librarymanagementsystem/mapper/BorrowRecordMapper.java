package com.shaurya.librarymanagementsystem.mapper;

import com.shaurya.librarymanagementsystem.dto.response.BorrowRecordResponse;
import com.shaurya.librarymanagementsystem.model.entity.BorrowRecord;
import org.springframework.stereotype.Component;

@Component
public class BorrowRecordMapper {

    public BorrowRecordResponse toResponse(BorrowRecord borrowRecord) {
        if (borrowRecord == null) {
            return  null;
        }

        return new BorrowRecordResponse(
                borrowRecord.getId(),
                borrowRecord.getBook().getId(),
                borrowRecord.getBook().getTitle(),
                borrowRecord.getMember().getId(),
                borrowRecord.getMember().getFirstName()
                + " " + borrowRecord.getMember().getLastName(),
                borrowRecord.getBorrowDate(),
                borrowRecord.getDueDate(),
                borrowRecord.getReturnDate(),
                borrowRecord.getFine(),
                borrowRecord.getBorrowStatus()
        );
    }
}
