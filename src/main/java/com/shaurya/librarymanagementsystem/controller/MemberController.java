package com.shaurya.librarymanagementsystem.controller;

import com.shaurya.librarymanagementsystem.dto.request.MemberRequest;
import com.shaurya.librarymanagementsystem.dto.response.MemberResponse;
import com.shaurya.librarymanagementsystem.model.enums.MemberStatus;
import com.shaurya.librarymanagementsystem.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<MemberResponse> createMember(@Valid @RequestBody MemberRequest memberRequest){
        MemberResponse memberResponse = memberService.createMember(memberRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(memberResponse);
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers(){
        List<MemberResponse> memberResponseList = memberService.getAllMembers();
        return ResponseEntity.status(HttpStatus.OK).body(memberResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> getMemberById(@PathVariable Long id){
        MemberResponse member = memberService.getMemberById(id);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<MemberResponse> getMemberByEmail(@PathVariable String email){
        MemberResponse member = memberService.getMemberByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @GetMapping("/status/{memberStatus}")
    public ResponseEntity<List<MemberResponse>> getMembersByStatus(@PathVariable MemberStatus memberStatus){
        List<MemberResponse> members = memberService.findByMemberStatus(memberStatus);
        return ResponseEntity.status(HttpStatus.OK).body(members);
    }

    @GetMapping("/membership-date")
    public ResponseEntity<List<MemberResponse>> getMembersByMembershipDate(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {

        List<MemberResponse> members =
                memberService.findByMembershipDateBetween(startDate, endDate);

        return ResponseEntity.ok(members);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MemberResponse> updateMember(@Valid @RequestBody MemberRequest memberRequest, @PathVariable Long id){
        MemberResponse memberResponse = memberService.updateMember(id, memberRequest);
        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}


