package com.agrotep.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level= AccessLevel.PRIVATE)
public class ValidationErrorResponse extends ErrorResponse {

    final List<ErrorDescription> errors;

    public ValidationErrorResponse(String message, List<ErrorDescription> errors) {
        super(message);
        this.errors = errors;
    }
}