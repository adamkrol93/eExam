package pl.lodz.p.it.ssbd2015.entities.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Objects;

/**
 * Interceptor do logowania transakcji bazodanowych.
 * Wykorzsytywany przez wszystkie fasady, aby zapisać w dzienniku zdzarzeń informacje o przetwarzanej transakcji.
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class LoggingInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Resource
    private SessionContext sessionContext;

    /**
     * Metoda interceptora, która loguje informacje o wywołaniach metod oraz rezultatach tych wywołań.
     * @param context Kontekst wywołania przekazany nam przez framework
     * @return Object to co zwróciła metoda
     * @throws Exception to co rzuciła metoda
     */
    @AroundInvoke
    public Object logMethodInvoked(InvocationContext context) throws Exception {
        if (!logger.isInfoEnabled()) {
            return context.proceed();
        }

        String className = context.getTarget().getClass().getCanonicalName();
        String methodName = context.getMethod().getName();
        String personLogin = sessionContext.getCallerPrincipal().getName();

        StringBuilder params = new StringBuilder();
        if (context.getParameters() != null) {
            for (Object param : context.getParameters()) {
                if (params.length() > 0) {
                    params.append(", ");
                }
                params.append(param);
            }
        }

        logger.info("{}.{}({}) has been invoked by person {}",
                className, methodName, params, personLogin
        );

        Object result;
        try {
            result = context.proceed();
        } catch (Exception ex) {
            StringBuilder causes = new StringBuilder();

            Throwable cause = ex.getCause();
            while (cause != null) {
                if (causes.length() > 0) {
                    causes.append(", ");
                }
                causes.append(cause);
                cause = cause.getCause();
            }

            logger.error("{}.{}({}) invoked by person {} has thrown an exception {}: {}. Causes: [{}]",
                    className, methodName, params, personLogin,
                    ex, ex.getLocalizedMessage(), causes
            );

            throw ex;
        }

        String resultString = context.getMethod().getReturnType().equals(Void.TYPE) ? "void" : Objects.toString(result);
        logger.info("{}.{}({}) invoked by person {} has returned {}",
                className, methodName, params, personLogin, resultString
        );

        return result;
    }

}
