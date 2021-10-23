package org.example.lcs.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.lcs.common.enums.ResponseStatus;

import java.util.Date;


@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse extends BaseResponse {
    private String pointsEarned;
    private Date transactionDate;

    @Builder
    public PurchaseResponse(ResponseStatus status, String message, String pointsEarned, Date transactionDate) {
        super(status, message);
        this.pointsEarned = pointsEarned;
        this.transactionDate = transactionDate;
    }
}
