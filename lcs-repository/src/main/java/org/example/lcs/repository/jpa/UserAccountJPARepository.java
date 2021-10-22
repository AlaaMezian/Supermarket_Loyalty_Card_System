package org.example.lcs.repository.jpa;

import org.example.lcs.repository.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccountJPARepository extends JpaRepository<UserAccount, Long> {
    boolean existsByMobileOrIdCardNumber(String mobile, String idCardNumber);

    Optional<UserAccount> findByMobile(String mobile);
    Optional<UserAccount> findByIdCardNumber(String idCardNumber);
}
