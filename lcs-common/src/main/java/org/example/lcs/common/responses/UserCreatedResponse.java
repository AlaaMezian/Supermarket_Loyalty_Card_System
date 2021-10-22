package org.example.lcs.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserCreatedResponse extends BaseResponse{
    private String surName;
    private String name;
    private String mobileNumber;
    private String idCardNumber;
}
