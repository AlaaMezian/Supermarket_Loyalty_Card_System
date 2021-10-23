package org.example.lcs.service.operation.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.requests.PurchaseRequest;
import org.example.lcs.common.responses.PurchaseResponse;
import org.example.lcs.service.handler.PurchaseOperationHandler;
import org.example.lcs.service.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PurchaseOperation implements Operation<PurchaseRequest, PurchaseResponse> {

    @Autowired
    private PurchaseOperationHandler purchaseOperationHandler;
    @Override
    public PurchaseResponse execute(PurchaseRequest request) {
        log.info("Start Executing Purchase Operation");
        return purchaseOperationHandler.handle(request);
    }
}
