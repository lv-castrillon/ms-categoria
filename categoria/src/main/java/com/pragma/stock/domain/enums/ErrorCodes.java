package com.pragma.stock.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

public interface ErrorCodes {
    String getValue();

    @AllArgsConstructor
    @Getter
    enum CategoryError implements ErrorCodes {
        DUPLICATE_NAMES("Nombre duplicado"),
        DESCRIPTION_NULL("La descripción no puede ser null"),
        DESCRIPTION_NAME_LENGTH("La descripción o el nombre exceden el tamaño máximo caracteres");
        private final String value;
    }

    @AllArgsConstructor
    @Getter
    enum BrandError implements ErrorCodes {
        DUPLICATE_NAMES("Nombre duplicado"),
        DESCRIPTION_NULL("La descripción no puede ser null"),
        DESCRIPTION_NAME_LENGTH("La descripción o el nombre exceden el tamaño máximo caracteres");
        private final String value;
    }
}
