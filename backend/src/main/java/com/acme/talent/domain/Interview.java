package com.acme.talent.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Interview {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  @ManyToOne(optional=false) private Talent talent;
  private String roundName;
  private String interviewer;
  private String mode;
  private Instant scheduledAt;
  @Enumerated(EnumType.STRING) private InterviewResult result = InterviewResult.PENDING;
  @Column(length=2000) private String feedback;
  private Integer rating;
  private Instant recordedAt = Instant.now();
}
