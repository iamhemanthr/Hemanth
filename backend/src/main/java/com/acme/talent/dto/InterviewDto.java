package com.acme.talent.dto;

import com.acme.talent.domain.InterviewResult;
import java.time.Instant;

public record InterviewDto(
  Long id, Long talentId, String roundName, String interviewer,
  String mode, Instant scheduledAt, InterviewResult result,
  String feedback, Integer rating, Instant recordedAt
) {}
