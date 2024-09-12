package com.pragma.stock.domain.exception;

import com.pragma.stock.domain.enums.ErrorCodes;

public class BrandException extends RuntimeException {

    public BrandException(ErrorCodes message) { super(message.getValue());}

}
