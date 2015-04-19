package pl.lodz.p.it.ssbd2015.entities.validators;

import pl.lodz.p.it.ssbd2015.mok.services.PeopleService;

import javax.ejb.EJB;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Walidator dla adnotacli {@link NotExistsAsPersonLoginInDatabase}
 * @author Andrzej Kurczewski
 */
public class PersonLoginExistenceInDatabaseValidator implements ConstraintValidator<NotExistsAsPersonLoginInDatabase, String> {
    @EJB
    PeopleService peopleService;

    @Override
    public void initialize(NotExistsAsPersonLoginInDatabase constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return peopleService.checkUniqueness(value);
    }
}