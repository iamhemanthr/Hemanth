package com.acme.talent.service;

import com.acme.talent.domain.*;
import com.acme.talent.dto.StatusUpdateRequest;
import com.acme.talent.repo.*;
import com.acme.talent.spec.TalentSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TalentService {
  private final TalentRepository talentRepo;
  private final StatusHistoryRepository historyRepo;
  private final DeploymentRepository deployRepo;
  private final InterviewRepository interviewRepo;

  public TalentService(TalentRepository t, StatusHistoryRepository h, DeploymentRepository d, InterviewRepository i){
    this.talentRepo=t; this.historyRepo=h; this.deployRepo=d; this.interviewRepo=i;
  }

  public Page<Talent> search(TalentStatus status, String skill, String location, Integer minExp, Pageable pageable){
    Specification<Talent> spec = Specification.where(TalentSpecifications.status(status))
      .and(TalentSpecifications.primarySkillLike(skill))
      .and(TalentSpecifications.locationEquals(location))
      .and(TalentSpecifications.minExp(minExp));
    return talentRepo.findAll(spec, pageable);
  }

  @Transactional
  public Talent updateStatus(Long id, StatusUpdateRequest req){
    var t = talentRepo.findById(id).orElseThrow();
    var old = t.getCurrentStatus();
    t.setCurrentStatus(req.newStatus());
    var h = StatusHistory.builder().talent(t).oldStatus(old).newStatus(req.newStatus()).reason(req.reason()).changedBy(req.changedBy()).build();
    historyRepo.save(h);
    return t;
  }
}
