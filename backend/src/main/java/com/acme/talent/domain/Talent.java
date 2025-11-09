package com.acme.talent.domain;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;
import java.util.List;

@Entity @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Talent {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
  @Column(nullable=false) private String name;
  @Column(nullable=false, unique=true) private String email;
  private String phone;
  @Column(nullable=false) private String primarySkill;
  @ElementCollection private List<String> secondarySkills;
  private int yearsExp;
  private String location;
  @Enumerated(EnumType.STRING) private TalentStatus currentStatus = TalentStatus.AVAILABLE;

  @OneToMany(mappedBy="talent", cascade=CascadeType.ALL, orphanRemoval=true)
  private List<Interview> interviews;

  @OneToOne(mappedBy="talent", cascade=CascadeType.ALL)
  private Deployment deployment;

  private Instant createdAt = Instant.now();
  private Instant updatedAt = Instant.now();

  @PreUpdate
  public void onUpdate(){ this.updatedAt = Instant.now(); }
}
