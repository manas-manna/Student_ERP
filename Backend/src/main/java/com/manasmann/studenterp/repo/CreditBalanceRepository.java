package com.manasmann.studenterp.repo;

import com.manasmann.studenterp.entity.CreditBalance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CreditBalanceRepository extends JpaRepository<CreditBalance, Long> {
    Optional<CreditBalance> findByStudent_StudentId(Long studentId);
}
