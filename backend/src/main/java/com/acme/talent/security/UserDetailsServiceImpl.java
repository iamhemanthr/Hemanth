package com.acme.talent.security;

import com.acme.talent.repo.UserAccountRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  private final UserAccountRepository repo;
  public UserDetailsServiceImpl(UserAccountRepository repo){ this.repo = repo; }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    var ua = repo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Not found"));
    var authorities = Arrays.stream(ua.getRoles().split(","))
        .map(String::trim).filter(s->!s.isBlank())
        .map(r -> "ROLE_" + r.replace("ROLE_",""))
        .map(org.springframework.security.core.authority.SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
    return new org.springframework.security.core.userdetails.User(ua.getUsername(), ua.getPassword(), ua.isEnabled(), true, true, true, authorities);
  }
}
