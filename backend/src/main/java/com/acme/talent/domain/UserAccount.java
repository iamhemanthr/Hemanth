package com.acme.talent.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class UserAccount {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @Column(nullable=false, unique=true) private String username;
  @Column(nullable=false) private String password;
  private String roles; // CSV e.g. ROLE_ADMIN,ROLE_USER
  private boolean enabled = true;
}
