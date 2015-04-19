package pl.lodz.p.it.ssbd2015.entities.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Adnotacja pozwalająca oznaczyć pola typu {@link String}, które nie mogą występować jako login w bazie danych.
 * Walidowana przez {@link PersonLoginExistenceInDatabaseValidator}
 * @author Andrzej Kurczewski
 */
@Target( {ElementType.PARAMETER, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonLoginExistenceInDatabaseValidator.class)
@Documented
public @interface NotExistsAsPersonLoginInDatabase {
    String message() default "Exists as login in database";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
