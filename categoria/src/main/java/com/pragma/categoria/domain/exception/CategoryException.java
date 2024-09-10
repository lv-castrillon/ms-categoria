package com.pragma.categoria.domain.exception;

import com.pragma.categoria.domain.enums.ErrorCodes;

public class CategoryException extends RuntimeException {

    public CategoryException(ErrorCodes message) { super(message.getValue());}

}
