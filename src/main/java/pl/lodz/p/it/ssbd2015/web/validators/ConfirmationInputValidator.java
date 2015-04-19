package pl.lodz.p.it.ssbd2015.web.validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Walidator wykorzystywany, kiedy istnieją dwa pola, które mają być identyczne.
 * Jest ciche wymaganie,
 * @author Andrzej Kurczewski
 */
@FacesValidator("confirmationInputValidator")
public class ConfirmationInputValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String originalText = (String) component.getAttributes().get("toConfirm");
        String confirmText = (String) value;
        if ((confirmText != null && !confirmText.equals(originalText)) || confirmText == null && originalText != null) {
            String errorMessage = (String) component.getAttributes().get("confirmationErrorMessage");
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage));
        }
    }
}
