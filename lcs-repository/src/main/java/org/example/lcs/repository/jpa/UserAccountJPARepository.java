package org.example.lcs.repository.jpa;

import org.example.lcs.repository.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountJPARepository extends JpaRepository<UserAccount, Long> {

}
