package com.stepintoprofession.registration_service.validate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<Phone, String> {
    @Override
    public void initialize(Phone constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        if (phone == null) {
            return true;
        }
        if (!phone.startsWith("+79") || phone.length() != 12) {
            return false;
        }
        try {
            Integer.parseInt(phone.substring(3));
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
