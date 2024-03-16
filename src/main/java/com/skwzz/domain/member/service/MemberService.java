package com.skwzz.domain.member.service;

import com.skwzz.domain.auth.payload.request.SignUpDto;
import com.skwzz.domain.member.entity.Member;
import com.skwzz.domain.member.mapper.MemberMapper;
import com.skwzz.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpDto signUpDto){
        Member member = Member.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        memberRepository.save(member);
    }
}
