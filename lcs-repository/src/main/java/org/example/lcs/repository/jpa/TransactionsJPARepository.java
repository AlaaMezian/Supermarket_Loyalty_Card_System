package org.example.lcs.repository.jpa;

import org.example.lcs.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TransactionsJPARepository extends JpaRepository<Transaction, Long> {
}
