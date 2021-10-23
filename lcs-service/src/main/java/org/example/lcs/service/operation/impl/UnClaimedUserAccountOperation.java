package org.example.lcs.service.operation.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.responses.UnClaimedUsersAccounts;
import org.example.lcs.service.handler.UnClaimedUserAccountHandler;
import org.example.lcs.service.operation.NoRequestBodyOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnClaimedUserAccountOperation implements NoRequestBodyOperation<UnClaimedUsersAccounts> {

    @Autowired
    private UnClaimedUserAccountHandler unClaimedUserAccountHandler;

    @Override
    public UnClaimedUsersAccounts execute() {
        log.info("Start Executing get un claimed user account");
        return unClaimedUserAccountHandler.getUnClaimedPositiveUserAccounts();
    }


}
