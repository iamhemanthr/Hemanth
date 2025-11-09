package com.acme.talent.spec;

import com.acme.talent.domain.Talent;
import com.acme.talent.domain.TalentStatus;
import org.springframework.data.jpa.domain.Specification;

public class TalentSpecifications {
  public static Specification<Talent> status(TalentStatus status){
    return (root, q, cb) -> status == null ? null : cb.equal(root.get("currentStatus"), status);
  }
  public static Specification<Talent> primarySkillLike(String skill){
    return (root, q, cb) -> (skill==null||skill.isBlank()) ? null : cb.like(cb.lower(root.get("primarySkill")), "%" + skill.toLowerCase() + "%");
  }
  public static Specification<Talent> locationEquals(String loc){
    return (root, q, cb) -> (loc==null||loc.isBlank()) ? null : cb.equal(cb.lower(root.get("location")), loc.toLowerCase());
  }
  public static Specification<Talent> minExp(Integer min){
    return (root, q, cb) -> (min==null) ? null : cb.greaterThanOrEqualTo(root.get("yearsExp"), min);
  }
}
