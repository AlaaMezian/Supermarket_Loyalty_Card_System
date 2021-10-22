package org.example.lcs.service.operation;

public interface Operation<REQ, RES> {
    RES execute(REQ request);
}
