package com.Test3.validation;

import com.Test3.model.Symbols;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    @Autowired
    private Symbols symbols;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return symbols.getSymbols().containsKey(value);
    }
}
