package web;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Named("exceptionHandler")
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
            return  exception.getClass().getCanonicalName() + ": " + exception.getMessage();
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
