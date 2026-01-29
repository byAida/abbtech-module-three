package com.abbtech.exception.base;

public enum BaseErrorEnum implements BaseErrorService {

    BASE_BUSINESS_ERROR("BASE-BUSINESS-ERROR-001", "BASE_BUSINESS_ERROR", 400),

    BASE_TECH_ERROR("BASE-TECH-ERROR-001", "BASE_TECH_ERROR", 500),

    BASE_GATEWAY_TIMEOUT("BASE-GATEWAY-TIMEOUT-001", "BASE_GATEWAY_TIMEOUT", 504),

    BASE_SERVER_ERROR("BASE-SERVER-ERROR-001", "BASE_SERVER_ERROR", 503),

    BASE_VALIDATION_ERROR("BASE-VALIDATION-ERROR-001", "BASE_VALIDATION_ERROR", 400);

    final String message;

    final int httpStatus;

    final String errorCode;

    BaseErrorEnum(String errorCode, String message, int httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }
}

