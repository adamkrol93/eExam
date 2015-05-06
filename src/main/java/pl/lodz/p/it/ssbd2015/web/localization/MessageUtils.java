package pl.lodz.p.it.ssbd2015.web.localization;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * Klasa użytkowa, ułatwiająca ręczne dodawanie wiadomości,
 * w szczególności komunikatów o błędach, dla wybranych pól formularza.
 * @author Andrzej Kurczewski
 */
public class MessageUtils {

    /**
     * Metoda dodaje wiadomość o zadanym kodzie do zadanego pola z istotnością na poziomie ERROR
     * @param code klucz wiadomości
     * @param field identyfikator pola, dla którego przypisujemy wiadomość
     */
    public static void addLocalizedMessage(String code, String field) {
        addLocalizedMessage(code, field, FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Metoda dodaje wiadomość o zadanym kodzie do zadanego pola z zadaną istotnością
     * @param code klucz wiadomości
     * @param field identyfikator pola, dla którego przypisujemy wiadomość
     * @param severity poziom istotności komunikatu
     */
    public static void addLocalizedMessage(String code, String field, FacesMessage.Severity severity) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        String message = facesContext.getApplication()
                                     .evaluateExpressionGet(facesContext, String.format("#{i18n['%s']}", code), String.class);
        facesContext.addMessage(field, new FacesMessage(severity, message, message));
    }
}
