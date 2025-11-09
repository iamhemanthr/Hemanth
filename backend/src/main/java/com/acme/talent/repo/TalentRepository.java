package com.acme.talent.repo;
import com.acme.talent.domain.Talent;
import org.springframework.data.jpa.repository.*;
public interface TalentRepository extends JpaRepository<Talent, Long>, JpaSpecificationExecutor<Talent> {}
