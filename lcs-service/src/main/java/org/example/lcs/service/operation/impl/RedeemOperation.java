package org.example.lcs.service.operation.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.lcs.common.requests.RedeemRequest;
import org.example.lcs.common.responses.RedeemResponse;
import org.example.lcs.service.handler.RedeemOperationHandler;
import org.example.lcs.service.operation.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RedeemOperation implements Operation<RedeemRequest, RedeemResponse>  {

    @Autowired
    private RedeemOperationHandler redeemOperationHandler;

    @Override
    public RedeemResponse execute(RedeemRequest request) {
        log.info("Start Executing Redeem Operation");
        return  redeemOperationHandler.handle(request);
    }
}
