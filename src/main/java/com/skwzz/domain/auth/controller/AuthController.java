package com.skwzz.domain.auth.controller;

import com.skwzz.domain.auth.payload.request.SignUpDto;
import com.skwzz.domain.auth.payload.request.SignInDto;
import com.skwzz.domain.member.service.MemberService;
import com.skwzz.global.jwt.JwtTokenUtil;
import com.skwzz.global.jwt.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final MemberService memberService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/sign-up")
    public ResponseEntity<Boolean> signUp(@RequestBody SignUpDto signUpDto) throws Exception {
        memberService.signUp(signUpDto);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signIn(@RequestBody SignInDto signInDto){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(signInDto.getUsername(), signInDto.getPassword());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        TokenDto tokenDto = jwtTokenUtil.createAllTokens(((UserDetails)authentication.getPrincipal()).getUsername());
        return ResponseEntity.ok().body(tokenDto);
    }
}
