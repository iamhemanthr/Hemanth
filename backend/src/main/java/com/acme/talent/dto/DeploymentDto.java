package com.acme.talent.dto;

import com.acme.talent.domain.DeploymentStatus;
import java.time.LocalDate;

public record DeploymentDto(
  Long id, Long talentId, String client, String project, String roleTitle,
  LocalDate startDate, LocalDate endDate, DeploymentStatus deploymentStatus, String remarks
) {}
