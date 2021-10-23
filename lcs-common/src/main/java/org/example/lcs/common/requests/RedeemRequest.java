package org.example.lcs.common.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
public class RedeemRequest extends OperationRequest {

    @Min(value = 100, message = "points to redeem have to bee 100 or more")
    private int pointToRedeem;
    private int numberOfWaterPackets;


    @AssertTrue(message = "point to redeem have to be multiply of 100")
    private boolean isValid() {
        return pointToRedeem % 100 == 0;
    }
}
