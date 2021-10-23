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
public class RedeemResponse extends OperationResponse {
    private String pointsRedeemed;

    @Builder
    public RedeemResponse(ResponseStatus status, String message, String pointsRedeemed, Date transactionDate) {
        super(status, message, transactionDate);
        this.pointsRedeemed = pointsRedeemed;
    }
}
