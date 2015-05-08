package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Location("administrator/editUser.xhtml")
public class EditUserPage {

    @FindBy(id = "edit-form:message")
    private WebElement message;

    @FindBy(id = "edit-form:name")
    private WebElement nameInput;

    @FindBy(id = "edit-form:lastname")
    private WebElement lastnameInput;

    @FindBy(id = "edit-form:email")
    private WebElement emailInput;

    @FindBy(id = "edit-form:password")
    private WebElement passwordInput;

    @FindBy(id = "edit-form:confirm")
    private WebElement confirmInput;

    @FindBy(id = "edit-form:submit-button")
    private WebElement submitButton;

    public static String urlTo(String login) {
        return "administrator/editUser.xhtml?login=" + login;
    }

    public String getMessage() {
        return message.getText();
    }

    public String getName() {
        return nameInput.getAttribute("value");
    }

    public void setName(String name) {
        nameInput.sendKeys(name);
    }

    public String getLastname() {
        return lastnameInput.getAttribute("value");
    }

    public void setLastname(String lastname) {
        lastnameInput.sendKeys(lastname);
    }

    public String getEmail() {
        return emailInput.getAttribute("value");
    }

    public void setEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void setPassword(String password) {
        setPassword(password, password);
    }

    public void setPassword(String password, String confirm) {
        passwordInput.sendKeys(password);
        confirmInput.sendKeys(confirm);
    }

    public void submit() {
        guardHttp(submitButton).click();
    }
}
