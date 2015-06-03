package pl.lodz.p.it.ssbd2015.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Bean udostępnia informację o zaistniałej sytuacji wyjątkowej.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "exceptionHandler")
@RequestScoped
public class ExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Exception exception;
    private String uuid;


    @PostConstruct
    public void applyStatusCode() {
        UUID uuid = UUID.randomUUID();
        this.uuid = uuid.toString();
        exception = (Exception) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.exception");
    }

    public Integer getStatusCode() {
        return (Integer) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.status_code");
    }

    public String getRequestURI() {
        return (String) FacesContext.getCurrentInstance().getExternalContext().
                getRequestMap().get("javax.servlet.error.request_uri");
    }

    public String getMessage() {
        if (exception != null) {
            return  exception.getClass().getCanonicalName();
        }
        else {
            return (String) FacesContext.getCurrentInstance().getExternalContext().
                    getRequestMap().get("javax.servlet.error.message");
        }
    }

    /**
     * Metoda wyciąga inormacje o wyjątku.
     * Jeżeli jest to klasa {@link ApplicationBaseException} to wyciąga z niej kod internacjonalizacji, jeżeli nie to wyświetla informacje o wewnętrznym błędzie serwera.
     * Metoda loguje również UUID błędu który wystąpił, aby łatwiej było go znaleźć.
     * @return Komunikat do wyświetlenia na stronie
     */
    public String getPlainMessage() {
        logger.error("Error with uuid: " + getUuid());
        logger.error(getUuid(), exception);
        if (exception != null) {
            try{
                if(exception.getCause()!= null)
                {
                    return ((ApplicationBaseException)exception.getCause()).getCode();
                }
                return "application.exception.500";
            }catch (ClassCastException ex) {
                return "application.exception.500";
            }
        }
        else {
            return "application.exception.500";
        }
    }

    public List<ErrorTracePair> getDetails() {
        Throwable throwable = exception;

        List<ErrorTracePair> tracePairs = new ArrayList<>();
        while (throwable != null) {
            StackTraceElement[] traceElems = throwable.getStackTrace();
            String[] traceStrings = new String[traceElems.length];
            for (int i = 0; i < traceElems.length; ++i) {
                traceStrings[i] = traceElems[i].toString();
            }
            tracePairs.add(
                    new ErrorTracePair(throwable.getClass().getCanonicalName() + ": "
                            + throwable.getMessage(), traceStrings));
            throwable = throwable.getCause();
        }

        return tracePairs;
    }

    public class ErrorTracePair {
        private String exception;
        private String[] trace;

        public ErrorTracePair(String exception, String[] trace) {
            this.exception = exception;
            this.trace = trace;
        }

        public String getException() {
            return exception;
        }

        public String[] getTrace() {
            return trace;
        }
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
