package com.acme.talent.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

  private final JwtUtil jwtUtil;
  private final UserDetailsService uds;

  public JwtFilter(JwtUtil jwtUtil, UserDetailsService uds) {
    this.jwtUtil = jwtUtil;
    this.uds = uds;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain chain) throws ServletException, IOException {

    String path = request.getRequestURI();

    // Skip authentication for public routes
    if (path.startsWith("/auth")
      || path.startsWith("/swagger-ui")
      || path.startsWith("/v3/api-docs")) {
      chain.doFilter(request, response);
      return;
    }

    // Handle JWT for protected routes
    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {

      String token = header.substring(7);

      try {
        String username = jwtUtil.validateAndGetSubject(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
          UserDetails ud = uds.loadUserByUsername(username);

          UsernamePasswordAuthenticationToken auth =
            new UsernamePasswordAuthenticationToken(
              ud, null, ud.getAuthorities());

          SecurityContextHolder.getContext().setAuthentication(auth);
        }

      } catch (Exception ignored) {
        // Invalid token → ignore and continue without auth
      }
    }

    chain.doFilter(request, response);
  }
}
