package org.example.lcs.common.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum ResponseStatus {

    SUCCESS("Success"),
    FAILED("Failed"),
    BAD_REQUEST("BardRequest");

    private String status;
    private static Map<String, ResponseStatus> statuses = new HashMap<>();


    static {
        Stream.of(ResponseStatus.values()).forEach(status -> statuses.put(status.status, status));
    }

    ResponseStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static ResponseStatus getStatusByName(String status) {
        return statuses.get(status);
    }
}
