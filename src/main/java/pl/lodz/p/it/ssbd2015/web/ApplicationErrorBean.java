package pl.lodz.p.it.ssbd2015.web;

import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * @author Adam Król
 */
@ManagedBean(name = "applicationError")
@RequestScoped
public class ApplicationErrorBean extends BaseContextBean {

    private String exceptionMessage;

    @Override
    protected void doInContext() {
        resetContext();
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
