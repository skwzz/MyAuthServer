package com.skwzz.domain.member.controller;

import com.skwzz.domain.member.entity.Member;
import com.skwzz.global.annotation.LoginMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/member")
@RestController
public class MemberController {

    @GetMapping
    public ResponseEntity getMemberInfo(@LoginMember Member member){
        log.info(member.toString());
        return ResponseEntity.ok().body("ok");
    }
}
