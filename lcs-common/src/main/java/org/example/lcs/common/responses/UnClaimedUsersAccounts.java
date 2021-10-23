package org.example.lcs.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.lcs.common.enums.ResponseStatus;

import java.util.List;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class UnClaimedUsersAccounts extends BaseResponse {
    private List<UseResponse> usersAccount;

    @Builder
    public UnClaimedUsersAccounts(ResponseStatus status, String message, List<UseResponse> usersAccount) {
        super(status, message);
        this.usersAccount = usersAccount;

    }
}
