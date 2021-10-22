package org.example.lcs.common.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.lcs.common.enums.ResponseStatus;


@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseResponse extends BaseResponse {
    private String pointsEarned;
    @Builder
    public PurchaseResponse(ResponseStatus status, String message, String pointsEarned) {
        super(status, message);
        this.pointsEarned = pointsEarned;
    }
}
