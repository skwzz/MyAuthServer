package com.skwzz.global.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.skwzz.config.SecurityConfig.PERMIT_ALL_PATHS;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenUtil jwtTokenUtil;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String accessToken = resolveToken(httpServletRequest);

        if(accessToken != null && jwtTokenUtil.validateToken(accessToken)){
            Authentication authentication = jwtTokenUtil.getAuthentication(accessToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.info("인증 성공 Username : {}", authentication.getName());
        }else{
            boolean isPermitAllPath = PERMIT_ALL_PATHS.stream()
                    .anyMatch(path -> antPathMatcher.match(path, httpServletRequest.getRequestURI()));
            if(!isPermitAllPath){
                log.error("유효한 JWT 토큰이 없습니다 : {}", httpServletRequest.getRequestURL());
            }
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization");
        if(StringUtils.hasText(accessToken) && StringUtils.startsWithIgnoreCase(accessToken, "Bearer ")) {
            return accessToken.substring(7);
        }
        return null;
    }
}
