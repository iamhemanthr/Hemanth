package com.acme.talent.repo;
import com.acme.talent.domain.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface DeploymentRepository extends JpaRepository<Deployment, Long> {
  Optional<Deployment> findByTalentId(Long talentId);
}
