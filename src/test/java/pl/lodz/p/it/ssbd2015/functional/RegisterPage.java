package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Location("login/register.xhtml")
public class RegisterPage {

    @FindBy(id = "register-form:login")
    private WebElement loginInput;

    @FindBy(id = "register-form:password")
    private WebElement passwordInput;

    @FindBy(id = "register-form:confirm")
    private WebElement confirmPasswordInput;

    @FindBy(id = "register-form:name")
    private WebElement nameInput;

    @FindBy(id = "register-form:lastname")
    private WebElement lastnameInput;

    @FindBy(id = "register-form:email")
    private WebElement emailInput;

    @FindBy(id = "register-form:submit-button")
    private WebElement submitButton;

    public void register(String login, String password, String name, String lastname, String email) {
        register(login, password, password, name, lastname, email);
    }

    public void register(String login, String password, String confirmPassword, String name, String lastname, String email) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        confirmPasswordInput.sendKeys(confirmPassword);
        nameInput.sendKeys(name);
        lastnameInput.sendKeys(lastname);
        emailInput.sendKeys(email);
        guardHttp(submitButton).click();
    }
}
