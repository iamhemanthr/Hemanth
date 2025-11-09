package com.acme.talent.web;

import com.acme.talent.domain.UserAccount;
import com.acme.talent.repo.UserAccountRepository;
import com.acme.talent.security.JwtUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {
  private final UserAccountRepository repo;
  private final PasswordEncoder encoder;
  private final JwtUtil jwt;

  public AuthController(UserAccountRepository r, PasswordEncoder e, JwtUtil j){ this.repo=r; this.encoder=e; this.jwt=j; }

  public record LoginReq(@NotBlank String username, @NotBlank String password){}
  public record RegisterReq(@NotBlank String username, @NotBlank String password){}

  @PostMapping("/register")
  public Map<String,Object> register(@RequestBody RegisterReq req){
    var ua = UserAccount.builder().username(req.username()).password(encoder.encode(req.password())).roles("ROLE_USER").enabled(true).build();
    repo.save(ua);
    return Map.of("status","ok");
  }

  @PostMapping("/login")
  public Map<String,Object> login(@RequestBody LoginReq req){
    var ua = repo.findByUsername(req.username()).orElseThrow();
    if (!encoder.matches(req.password(), ua.getPassword())) throw new RuntimeException("Bad credentials");
    return Map.of("token", jwt.generate(ua.getUsername()));
  }
}
