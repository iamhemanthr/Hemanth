package com.acme.talent.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Deployment {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

  @OneToOne @JoinColumn(name="talent_id", unique=true) private Talent talent;
  private String client;
  private String project;
  private String roleTitle;
  private LocalDate startDate;
  private LocalDate endDate;
  @Enumerated(EnumType.STRING) private DeploymentStatus deploymentStatus = DeploymentStatus.PLANNED;
  private String remarks;
}
