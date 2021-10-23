package org.example.lcs.common.responses;

import lombok.*;
import org.example.lcs.common.enums.ResponseStatus;

import java.util.Date;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponse extends BaseResponse {
    private Date transactionDate;

    public OperationResponse(ResponseStatus status, String message, Date transactionDate) {
        super(status, message);
        this.transactionDate = transactionDate;
    }
}
