package com.acme.talent;

import com.acme.talent.repo.TalentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class TalentRepositoryIT {
  @Autowired TalentRepository repo;
  @Test void contextLoads(){}
}
