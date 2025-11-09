package com.acme.talent.dto;

import com.acme.talent.domain.TalentStatus;
import java.time.Instant;
import java.util.List;

public record TalentDto(
  Long id, String name, String email, String phone,
  String primarySkill, List<String> secondarySkills,
  int yearsExp, String location, TalentStatus currentStatus,
  Instant createdAt, Instant updatedAt
) {}
