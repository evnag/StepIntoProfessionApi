package com.stepintoprofession.registration_service.validate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckDateValidator.class)
public @interface DateFormat {

    String message() default "Date format is not valid. Try yyyy-MM-dd";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String pattern();
}
