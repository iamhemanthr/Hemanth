package com.acme.talent.dto;

import com.acme.talent.domain.TalentStatus;
import jakarta.validation.constraints.NotNull;

public record StatusUpdateRequest(
  @NotNull TalentStatus newStatus,
  String reason,
  String changedBy
){}
