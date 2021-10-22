package org.example.lcs.service.operation.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.service.operation.Operation;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PurchaseOperation implements Operation<PurchaseRequest, PurchaseResponse> {

    @Override
    public PurchaseResponse execute(PurchaseRequest request) {
        return null;
    }
}
