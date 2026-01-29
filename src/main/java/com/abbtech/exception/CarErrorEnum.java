package com.abbtech.exception;


import com.abbtech.exception.base.BaseErrorService;

public enum CarErrorEnum implements BaseErrorService {


    CAR_NOT_FOUND("CAR_NOT_FOUND-0001", "CAR_NOT_FOUND", 404),

    BRAND_NOT_FOUND("BRAND_NOT_FOUND-0001", "BRAND_NOT_FOUND", 404),

    YOUR_ERROR_DESCRIPTION_CASE_HERE("YOUR_ERROR_DESCRIPTION_CASE_HERE-0002", "YOUR_ERROR_DESCRIPTION_CASE_HERE", 400);

    final String message;

    final int httpStatus;

    final String errorCode;

    CarErrorEnum(String errorCode, String message, int httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}

