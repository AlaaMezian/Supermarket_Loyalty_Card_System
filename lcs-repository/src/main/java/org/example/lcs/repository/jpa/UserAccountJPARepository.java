package org.example.lcs.repository.jpa;

import org.example.lcs.repository.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccountJPARepository extends JpaRepository<UserAccount, Long> {
    boolean existsByMobileOrIdCardNumber(String mobile, String idCardNumber);

    Optional<UserAccount> findByMobileOrIdCardNumber(String mobile, String idCardNumber);

    @Query(value = "select u from UserAccount u where u.totalPointAmount > 0 ")
    List<UserAccount> findAllUnclaimedAccounts();
}
