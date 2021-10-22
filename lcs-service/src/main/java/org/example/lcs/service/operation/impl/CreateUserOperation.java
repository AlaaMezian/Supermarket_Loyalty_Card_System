package org.example.lcs.service.operation.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.requests.CreateUserAccountRequest;
import org.example.lcs.common.responses.UserCreatedResponse;
import org.example.lcs.service.handler.CreateUserAccountHandler;
import org.example.lcs.service.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateUserOperation implements Operation<CreateUserAccountRequest, UserCreatedResponse> {

    @Autowired
    private CreateUserAccountHandler createUserAccountHandler;

    @Override
    public UserCreatedResponse execute(CreateUserAccountRequest request) {
        return createUserAccountHandler.createUser(request);
    }
}
