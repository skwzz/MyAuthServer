package com.skwzz.domain.member.mapper;

import com.skwzz.domain.auth.payload.request.SignUpDto;
import com.skwzz.domain.member.entity.Member;

public class MemberMapper {

    public static Member dtoToEntity(SignUpDto signUpDto){
        return Member.builder()
                .username(signUpDto.getUsername())
                .password(signUpDto.getPassword())
                .build();
    }
}
