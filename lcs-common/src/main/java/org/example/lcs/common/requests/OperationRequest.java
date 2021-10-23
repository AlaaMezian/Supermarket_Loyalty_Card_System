package org.example.lcs.common.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OperationRequest {
    private Long cashierId;
    private String mobileNumber;
    private String idCardNumber;
}
