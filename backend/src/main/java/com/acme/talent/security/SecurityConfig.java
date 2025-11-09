package com.acme.talent.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  private final JwtFilter jwtFilter;
  private final UserDetailsService uds;

  public SecurityConfig(JwtFilter jwtFilter, UserDetailsService uds){
    this.jwtFilter = jwtFilter; this.uds = uds;
  }

  @Bean PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

  @Bean AuthenticationProvider authProvider(){
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(uds);
    p.setPasswordEncoder(passwordEncoder());
    return p;
  }

  @Bean AuthenticationManager authenticationManager(AuthenticationConfiguration cfg) throws Exception {
    return cfg.getAuthenticationManager();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(csrf -> csrf.disable());
    http.authorizeHttpRequests(auth -> auth
      .requestMatchers("/auth/**","/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
      .anyRequest().authenticated()
    );
    http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    http.cors(c->{});
    return http.build();
  }
}
