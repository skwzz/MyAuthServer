package com.skwzz.config;

import com.skwzz.domain.member.service.MemberService;
import com.skwzz.global.resolver.LoginUserArgumentResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;

    public WebConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        MemberService memberService = applicationContext.getBean(MemberService.class);
        resolvers.add(new LoginUserArgumentResolver(memberService));
    }
}
