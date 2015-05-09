package pl.lodz.p.it.ssbd2015.web.validators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.BeanValidator;
import javax.faces.validator.FacesValidator;

/**
 * @author Andrzej Kurczewski
 */
@FacesValidator("validateBeanIfNotEmpty")
public class NotEmptyBeanValidatorDecorator extends BeanValidator {
    static private Logger logger = LoggerFactory.getLogger(NotEmptyBeanValidatorDecorator.class);

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) {
        if(value != null && !value.toString().isEmpty()) {
            super.validate(context, component, value);
        } else {
            logger.info(String.format("Value is empty, validation of component %s skipped", component.getClientId()));
        }
    }
}
