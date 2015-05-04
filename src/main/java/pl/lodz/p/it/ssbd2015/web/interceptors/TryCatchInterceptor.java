package pl.lodz.p.it.ssbd2015.web.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.web.ApplicationErrorBean;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import java.util.Arrays;

/**
 * Created by adam on 02.05.15.
 */
public class TryCatchInterceptor {


    private static final Logger logger = LoggerFactory.getLogger(TryCatchInterceptor.class);

    @AroundInvoke
    public Object tryCatch(InvocationContext ctx) throws Exception {
        Object result;
        try {
            result = ctx.proceed();
        } catch (ApplicationBaseException e) {
            logger.error("Error occurred while editing PersonEntity: " + Arrays.toString(ctx.getParameters()), e);
            ((BaseContextBean) ctx.getTarget()).setContext(ApplicationErrorBean.class, bean -> bean.setExceptionMessage(e.getCode()));
            return "/error/applicationError?faces-redirect=true&uuid=" + ((BaseContextBean) ctx.getTarget()).getUuid();
        }

        return result;
    }
}
