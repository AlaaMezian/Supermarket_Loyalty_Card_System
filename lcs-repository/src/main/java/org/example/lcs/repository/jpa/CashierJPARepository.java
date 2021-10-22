package org.example.lcs.repository.jpa;

import org.example.lcs.repository.entity.Cashier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CashierJPARepository extends JpaRepository<Cashier, Long> {
}
