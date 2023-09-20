package com.dev.esthomy.config;

import com.dev.esthomy.models.Account;
import com.dev.esthomy.repository.AccountRepository;
import com.dev.esthomy.security.JwtAuthenticationEntryPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    //toDo: seperate class - secconfig - appconfig.
    private UserDetailsService userDetailsService;

    private AccountRepository accountRepository;
    private JwtAuthenticationEntryPoint authenticationEntryPoint;


    public UserDetailsService userDetailsService()
    {
     return username -> accountRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Account not found."));
    }

}
