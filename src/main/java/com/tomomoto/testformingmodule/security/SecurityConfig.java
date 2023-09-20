package com.tomomoto.testformingmodule.security;

import com.tomomoto.testformingmodule.entities.User;
import com.tomomoto.testformingmodule.repos.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findUserByUsername(username).get(0);
            if (user != null) {
                return user;
            }
            throw new UsernameNotFoundException("User " + username + " was not found.");
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(httpReqCustomizer -> httpReqCustomizer
                        .requestMatchers("/", "/login", "/register", "/style/*", "/images/*")
                        .permitAll()
                        .requestMatchers("/logout", "/home/**", "/subjects", "/tests/{subject}/**")
                        .hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/users/**")
                        .hasRole("ADMIN"))
                .formLogin(loginCustomizer -> loginCustomizer
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/home"))
                .logout(logoutCustomizer -> logoutCustomizer
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"))
                .build();

//        .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL))) another way to clear all cashes and cookies
    }
}
