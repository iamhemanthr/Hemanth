package com.acme.talent.repo;
import com.acme.talent.domain.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Long> {}
