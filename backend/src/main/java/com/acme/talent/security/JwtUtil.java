package com.acme.talent.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {
  private final Key key = Keys.hmacShaKeyFor("very-secret-and-long-demo-key-please-change-123456".getBytes());
  private final long expiryMs = 8 * 60 * 60 * 1000; // 8h

  public String generate(String username){
    Date now = new Date();
    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(now)
      .setExpiration(new Date(now.getTime() + expiryMs))
      .signWith(key, SignatureAlgorithm.HS256)
      .compact();
  }

  public String validateAndGetSubject(String token){
    return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
  }
}
