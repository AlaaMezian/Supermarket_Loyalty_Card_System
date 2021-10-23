package org.example.lcs.common.requests;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
public class PurchaseRequest extends OperationRequest {

    @Min(value = 1, message = "purchase amount cant be less that one euro")
    private BigDecimal purchaseAmount;

}
