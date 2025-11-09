package com.acme.talent.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class StatusHistory {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  @ManyToOne(optional=false) private Talent talent;
  @Enumerated(EnumType.STRING) private TalentStatus oldStatus;
  @Enumerated(EnumType.STRING) private TalentStatus newStatus;
  private String changedBy;
  @Column(length=1000) private String reason;
  private Instant changedAt = Instant.now();
}
