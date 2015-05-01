package pl.lodz.p.it.ssbd2015.web;

import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Bean udostępnia informację o zaistniałej sytuacji wyjątkowej.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@ManagedBean(name = "exceptionHandler")
@RequestScoped
public class ExceptionHandler {

    private Exception exception;

    @PostConstruct
    public void applyStatusCode() {
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
    public String getPlainMessage()
    {
        if (exception != null) {
            try{
                return ((ApplicationBaseException)exception.getCause()).getCode();
            }catch (ClassCastException ex) {
                return exception.getLocalizedMessage();
            }
        }
        else {
            return (String) FacesContext.getCurrentInstance().getExternalContext().
                    getRequestMap().get("javax.servlet.error.message");
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

}
