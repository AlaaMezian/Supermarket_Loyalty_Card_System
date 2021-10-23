package org.example.lcs.service.mappers;

import org.example.lcs.common.enums.ResponseStatus;
import org.example.lcs.common.requests.CreateUserAccountRequest;
import org.example.lcs.common.responses.UnClaimedUsersAccounts;
import org.example.lcs.common.responses.UseResponse;
import org.example.lcs.repository.entity.UserAccount;

import java.util.ArrayList;
import java.util.List;


public class UserAccountMapper {

    public static UserAccount toEntity(CreateUserAccountRequest userAccountRequest) {
        return UserAccount.builder()
                .idCardNumber(userAccountRequest.getIdCardNumber())
                .mobile(userAccountRequest.getMobileNumber())
                .name(userAccountRequest.getName())
                .surName(userAccountRequest.getSurName())
                .build();
    }

    public static UseResponse toResponse(UserAccount userAccount) {
        return UseResponse.builder()
                .mobileNumber(userAccount.getMobile())
                .idCardNumber(userAccount.getIdCardNumber())
                .name(userAccount.getName())
                .surName(userAccount.getSurName())
                .build();

    }

    public static UnClaimedUsersAccounts toUnClaimedUserAccountResponse(List<UserAccount> accountsToMap) {
        if (accountsToMap.size() == 0) {
            return UnClaimedUsersAccounts.builder()
                    .message("No UnClaimed Users Account found")
                    .status(ResponseStatus.SUCCESS)
                    .build();
        }
        List<UseResponse> userAccountsResponse = new ArrayList<>();
        UnClaimedUsersAccounts unClaimedUsersAccounts = new UnClaimedUsersAccounts();
        for (UserAccount account : accountsToMap) {
            UseResponse userResponse = UseResponse.builder()
                    .mobileNumber(account.getMobile())
                    .idCardNumber(account.getIdCardNumber())
                    .name(account.getName())
                    .surName(account.getSurName())
                    .totalPointsAmount(String.valueOf(account.getTotalPointAmount()))
                    .build();
            userAccountsResponse.add(userResponse);
        }
        unClaimedUsersAccounts.setUsersAccount(userAccountsResponse);
        unClaimedUsersAccounts.setMessage("User Accounts Fetched Successfully");
        unClaimedUsersAccounts.setStatus(ResponseStatus.SUCCESS);
        return unClaimedUsersAccounts;
    }
}
