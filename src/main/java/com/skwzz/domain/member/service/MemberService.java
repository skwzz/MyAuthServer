package com.skwzz.domain.member.service;

import com.skwzz.domain.auth.payload.request.SignUpDto;
import com.skwzz.domain.member.entity.Member;
import com.skwzz.domain.member.mapper.MemberMapper;
import com.skwzz.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signUp(SignUpDto signUpDto) throws Exception {

        Optional<Member> maybeMember = memberRepository.findByUsername(signUpDto.getUsername());
        if(maybeMember.isPresent()) throw new Exception("이미 등록된 사용자 입니다.");

        Member member = Member.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .build();
        memberRepository.save(member);
    }

    public Member findMemberInfo(String username){
        return memberRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
}
