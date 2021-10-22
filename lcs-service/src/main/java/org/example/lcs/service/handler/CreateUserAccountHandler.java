package org.example.lcs.service.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.exceptions.DataBaseOperationException;
import org.example.lcs.common.requests.CreateUserAccountRequest;
import org.example.lcs.common.responses.UserCreatedResponse;
import org.example.lcs.repository.entity.UserAccount;
import org.example.lcs.repository.jpa.UserAccountJPARepository;
import org.example.lcs.service.mappers.UserAccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CreateUserAccountHandler {

    @Autowired
    private UserAccountJPARepository userAccountJPARepository;

    public UserCreatedResponse createUser(CreateUserAccountRequest userAccountRequest) {
        try {
            log.info("Start saving user details");
            UserAccount userAccount = userAccountJPARepository.save(UserAccountMapper.toEntity(userAccountRequest));
            log.info("user details saved {}", userAccount);
            return createResponse(userAccount);
        } catch (DataAccessException e) {
            log.error("Failed to create UserAccount", e);
            throw new DataBaseOperationException("failed to save user account");
        }
    }

    private UserCreatedResponse createResponse(UserAccount userAccount) {
        log.info("mapping response for the following user account ", userAccount);

        UserCreatedResponse userResponse = UserAccountMapper.toResponse(userAccount);
        userResponse.setMessage("User Created Successfully");
        userResponse.setStatus(ResponseStatus.SUCCESS);

        log.info("response mapped {}", userResponse);
        return userResponse;
    }

    ;
}
