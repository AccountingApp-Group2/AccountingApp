package com.example.accountingapp.config;

import com.example.accountingapp.service.SecurityService;
import com.example.accountingapp.service.impl.SecurityServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {

    private final SecurityService securityService;
    private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(SecurityService securityService, AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler = authSuccessHandler;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http
                .authorizeRequests()
                .antMatchers("/company/**").hasAuthority("Root")
                .antMatchers("/user/**").hasAnyAuthority("Root","Admin")
                .antMatchers("/product/**").hasAuthority("Manager")
                .antMatchers("/category/**").hasAuthority("Manager")
                .antMatchers("/client-vendor/**").hasAuthority("Manager")
                .antMatchers("/invoice/**").hasAnyAuthority("Manager","Employee")
                .antMatchers("/payment/**").hasAuthority("Manager")
                .antMatchers(
                        "/",
                        "/login",
                        "/assets/**",
                        "/css/**",
                        "/css-rtl/**",
                        "/data/**",
                        "/fonts/**",
                        "/img/**",
                        "/js/**",
                        "/libs/**",
                        "/vendors/**",
                        "/media/**",
                        "/vendor/**",
                        "/resources/**"
                ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .successHandler(authSuccessHandler)
                    .failureUrl("/login?error=true")
                    .permitAll()
                .and()
                .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                    .tokenValiditySeconds(120)
                    .key("cydeo")
                    .userDetailsService(securityService)
                .and().build();

    }

}
