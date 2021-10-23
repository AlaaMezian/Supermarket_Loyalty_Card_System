package org.example.lcs.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.responses.UnClaimedUsersAccounts;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.example.lcs.service.mappers.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UnClaimedUserAccountHandler {

    @Autowired
    private UserAccountJPARepository userAccountJPARepository;

    public UnClaimedUsersAccounts getUnClaimedPositiveUserAccounts() {
        List<UserAccount> userAccounts = userAccountJPARepository.findAllUnclaimedAccounts();
        return UserAccountMapper.toUnClaimedUserAccountResponse(userAccounts);
    }
}
