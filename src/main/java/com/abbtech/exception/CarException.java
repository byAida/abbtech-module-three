package com.abbtech.exception;

import com.abbtech.exception.base.BaseErrorService;
import com.abbtech.exception.base.BaseException;
import lombok.Getter;

@Getter
public class CarException extends BaseException {

    private static final long serialVersionUID = 1L;

    public CarException(BaseErrorService baseErrorService, Throwable throwable, Object... args) {
        super(baseErrorService, throwable, args);
    }
    public CarException(BaseErrorService baseErrorService, Object... args) {
        super(baseErrorService, args);
    }
}

