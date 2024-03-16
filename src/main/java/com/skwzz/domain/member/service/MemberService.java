package com.skwzz.domain.member.service;

import com.skwzz.domain.auth.payload.request.SignUpDto;
import com.skwzz.domain.member.mapper.MemberMapper;
import com.skwzz.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void signUp(SignUpDto signUpDto){
        memberRepository.save(MemberMapper.dtoToEntity(signUpDto));
    }
}
