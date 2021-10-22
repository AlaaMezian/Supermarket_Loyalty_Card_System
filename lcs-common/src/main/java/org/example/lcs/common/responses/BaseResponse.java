package org.example.lcs.common.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.lcs.common.enums.ResponseStatus;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString

public class BaseResponse {

    protected ResponseStatus status;
    protected String message;
}
