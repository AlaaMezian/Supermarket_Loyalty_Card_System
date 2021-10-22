package org.example.lcs.service.mappers;

import org.example.lcs.common.requests.CreateUserAccountRequest;
import org.example.lcs.common.responses.UserCreatedResponse;
import org.example.lcs.repository.entity.UserAccount;


public class UserAccountMapper {

    public static UserAccount toEntity(CreateUserAccountRequest userAccountRequest){
        return UserAccount.builder()
                .idCardNumber(userAccountRequest.getIdCardNumber())
                .mobile(userAccountRequest.getMobileNumber())
                .name(userAccountRequest.getName())
                .surName(userAccountRequest.getSurName())
                .build();
    }

    public static UserCreatedResponse toResponse(UserAccount userAccount){
        return UserCreatedResponse.builder()
                .mobileNumber(userAccount.getMobile())
                .idCardNumber(userAccount.getIdCardNumber())
                .name(userAccount.getName())
                .surName(userAccount.getSurName())
                .build();

    }
}
