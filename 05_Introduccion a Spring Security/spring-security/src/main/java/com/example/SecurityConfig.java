package com.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig  {

    @Bean
    public UserDetailsService userDetailsService(){
        var userDetailsService = new InMemoryUserDetailsManager();
        UserDetails user1 = User.builder()
                .username("postman")
                .password(this.passwordEncoder().encode("postman"))
                .authorities("read")
                .build();

        UserDetails user2 = User.builder()
                .username("browser")
                .password(this.passwordEncoder().encode("postman"))
                .authorities("read")
                .build();

        userDetailsService.createUser(user1);
        userDetailsService.createUser(user2);
        return userDetailsService;
    }

    /*
El objetivo de este bean es que las contraseñas no se almacenen en texto plano
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
