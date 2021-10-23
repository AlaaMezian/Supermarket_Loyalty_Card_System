package org.example.lcs.service.operation;

public interface NoRequestBodyOperation<RES> extends Operation<RES, RES> {
    RES execute();
}
