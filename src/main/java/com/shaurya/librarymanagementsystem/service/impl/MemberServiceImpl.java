package com.shaurya.librarymanagementsystem.service.impl;

import com.shaurya.librarymanagementsystem.dto.request.MemberRequest;
import com.shaurya.librarymanagementsystem.dto.response.MemberResponse;
import com.shaurya.librarymanagementsystem.dto.response.PageResponse;
import com.shaurya.librarymanagementsystem.exception.DuplicateEmailException;
import com.shaurya.librarymanagementsystem.exception.InvalidMembershipDateRangeException;
import com.shaurya.librarymanagementsystem.exception.MemberNotFoundException;
import com.shaurya.librarymanagementsystem.mapper.MemberMapper;
import com.shaurya.librarymanagementsystem.model.entity.Member;
import com.shaurya.librarymanagementsystem.model.enums.MemberStatus;
import com.shaurya.librarymanagementsystem.repositories.MemberRepository;
import com.shaurya.librarymanagementsystem.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    private final int pageSize = 7;

    @Override
    @Transactional
    public MemberResponse createMember(MemberRequest request){
        if(memberRepository.existsByEmail(request.email())){
            throw new DuplicateEmailException(
                    "Member with email '" + request.email() + "' already exists."
            );
        }
        Member member = memberMapper.toEntity(request);

        member.setMembershipDate(LocalDate.now());
        member.setMemberStatus(MemberStatus.ACTIVE);

        Member savedMember = memberRepository.save(member);
        return memberMapper.toResponse(savedMember);
    }

    @Override
    @Transactional
    public MemberResponse updateMember(Long id, MemberRequest request){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member by id " + id + " not found"));

        if(!member.getEmail().equals(request.email())
                && memberRepository.existsByEmail(request.email())){
            throw new DuplicateEmailException(
                    "Member with email '" + request.email() + "' already exists."
            );
        }

        member.setFirstName(request.firstName());
        member.setLastName(request.lastName());
        member.setEmail(request.email());
        member.setPhone(request.phone());

        Member updatedMember = memberRepository.save(member);
        return memberMapper.toResponse(updatedMember);
    }


    @Override
    @Transactional
    public void deleteMember(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member by id " + id + " not found"));
        memberRepository.delete(member);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponse getMemberById(Long id){
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberNotFoundException("Member by id " + id + " not found"));
        return memberMapper.toResponse(member);
    }
    @Override
    @Transactional(readOnly = true)
    public MemberResponse getMemberByEmail(String email){
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberNotFoundException("Member by email " + email + " not found"));
        return memberMapper.toResponse(member);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<MemberResponse> getAllMembers(int page){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<Member> memberPage = memberRepository.findAll(pageable);
        List<MemberResponse> members = memberPage.getContent().stream().map(memberMapper::toResponse).toList();
        return new PageResponse<>(
                members,
                memberPage.getNumber(),
                memberPage.getSize(),
                memberPage.getTotalElements(),
                memberPage.getTotalPages(),
                memberPage.isFirst(),
                memberPage.isLast()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<MemberResponse> findByMemberStatus(MemberStatus memberStatus, int page){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<Member> memberPage = memberRepository.findByMemberStatus(memberStatus, pageable);
        List<MemberResponse> members = memberPage.getContent().stream().map(memberMapper::toResponse).toList();
        return new PageResponse<>(
                members,
                memberPage.getNumber(),
                memberPage.getSize(),
                memberPage.getTotalElements(),
                memberPage.getTotalPages(),
                memberPage.isFirst(),
                memberPage.isLast()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<MemberResponse> findByMembershipDateBetween(LocalDate startDate, LocalDate endDate, int page){
        LocalDate currentDate = LocalDate.now();
        if(startDate.isAfter(endDate)){
            throw new InvalidMembershipDateRangeException(
                    "Start date cannot be after end date"
            );
        }
        if(endDate.isAfter(currentDate)){
            throw new InvalidMembershipDateRangeException(
                    "End date cannot be in the future"
            );
        }

        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));
        Page<Member> memberPage = memberRepository.findByMembershipDateBetween(startDate, endDate, pageable);
        List<MemberResponse> members = memberPage.getContent().stream().map(memberMapper::toResponse).toList();
        return new PageResponse<>(
                members,
                memberPage.getNumber(),
                memberPage.getSize(),
                memberPage.getTotalElements(),
                memberPage.getTotalPages(),
                memberPage.isFirst(),
                memberPage.isLast()
        );
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<MemberResponse> findByName(String name, int page) {

        Pageable pageable =
                PageRequest.of(page, pageSize, Sort.by(Sort.Direction.ASC, "firstName"));

        Page<Member> memberPage =
                memberRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name, pageable);

        List<MemberResponse> members =
                memberPage.getContent()
                        .stream()
                        .map(memberMapper::toResponse)
                        .toList();

        return new PageResponse<>(
                members,
                memberPage.getNumber(),
                memberPage.getSize(),
                memberPage.getTotalElements(),
                memberPage.getTotalPages(),
                memberPage.isFirst(),
                memberPage.isLast()
        );
    }
}
