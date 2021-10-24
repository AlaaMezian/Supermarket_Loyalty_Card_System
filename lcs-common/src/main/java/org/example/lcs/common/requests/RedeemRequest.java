package org.example.lcs.common.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;

@Data
@NoArgsConstructor
public class RedeemRequest extends OperationRequest {

    private int pointToRedeem;
    private int numberOfWaterPackets;


    @AssertTrue(message = "point to redeem have to be multiply of 100")
    private boolean isValid() {
        if (pointToRedeem != 0) {
            return pointToRedeem % 100 == 0 && pointToRedeem >= 100;
        } else {
            return true;
        }
    }
}
