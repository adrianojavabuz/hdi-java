package br.com.hdi.apijava.controller.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@RequiredArgsConstructor
public class ValidationFailureResponse {
    private final FieldError[] fieldErrors;
}
