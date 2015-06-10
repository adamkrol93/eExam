package pl.lodz.p.it.ssbd2015.exceptions;

/**
 * Klasy bazowa dla wyjątków aplikacyjnych.
 * @author Adam Król
 */
@javax.ejb.ApplicationException(rollback = true)
public abstract class ApplicationBaseException extends Exception {

    public ApplicationBaseException(String message) {
        super(message);
    }

    public ApplicationBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Zwraca kod komunikatu tego wyjątku. Kod ten to ciąg znaków, który można wykorzystać w widoku do powiązania
     * z wyjątkami wiadomości przyjaznych użytkownikom. Umożliwia to wykorzystanie pola message, znajdującego
     * się w wyjątkach, do przekazania komunikatu o błędu przeznaczonego dla programisty.
     * W tym systemie kody są unikalne dla każdego wyjątku i ściśle powiązane z hierarchią wyjątków, gdyż klasy
     * dziedziczące wyjątków doklejają do kodów rodziców identyfikujące ich człony.
     * @return Ciąg znaków identyfikujący ten wyjątek.
     */
    public String getCode() {
        return "application.exception";
    }
}
