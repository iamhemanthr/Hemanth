package com.acme.talent.mapper;

import com.acme.talent.domain.Talent;
import com.acme.talent.dto.TalentDto;

public class TalentMapper {
  public static TalentDto toDto(Talent t){
    return new TalentDto(t.getId(), t.getName(), t.getEmail(), t.getPhone(),
      t.getPrimarySkill(), t.getSecondarySkills(), t.getYearsExp(), t.getLocation(),
      t.getCurrentStatus(), t.getCreatedAt(), t.getUpdatedAt());
  }
}
