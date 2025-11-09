package com.acme.talent.repo;
import com.acme.talent.domain.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface InterviewRepository extends JpaRepository<Interview, Long> {
  List<Interview> findByTalentId(Long talentId);
}
