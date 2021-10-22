package org.example.lcs.common.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;

@Data
@NoArgsConstructor
public class CreateUserAccountRequest {

    private String name;
    private String surName;
    private String mobileNumber;
    private String idCardNumber;

    @AssertTrue(message = "mobileNumber or idCardNumber should be sent to create user account")
    private boolean isValid() {
        return mobileNumber != null || idCardNumber != null;
    }
}
