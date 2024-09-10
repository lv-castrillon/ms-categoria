package com.pragma.stock.domain.exception;

import com.pragma.stock.domain.enums.ErrorCodes;

public class CategoryException extends RuntimeException {

    public CategoryException(ErrorCodes message) { super(message.getValue());}

}
