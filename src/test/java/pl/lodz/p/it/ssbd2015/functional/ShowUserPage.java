package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

/**
 * @author Michał Sośnicki
 */
@Location("administrator/showUser.xhtml")
public class ShowUserPage {

    @FindBy(id = "person-message")
    private WebElement message;

    @FindBy(id = "person-login")
    private WebElement login;

    @FindBy(id = "person-name")
    private WebElement name;

    @FindBy(id = "person-lastname")
    private WebElement lastname;

    @FindBy(id = "person-email")
    private WebElement email;

    @FindBy(id = "confirm-form:confirm-button")
    private WebElement confirmButton;

    @FindBy(id = "activate-form:activate-button")
    private WebElement activateButton;

    @FindBy(id = "groups-list-form")
    private WebElement groupsForm;

    public static String urlTo(String login) {
        return "administrator/showUser.xhtml?login=" + login;
    }

    public String getMessage() {
        return message.getText();
    }

    public String getLogin() {
        return login.getText().substring(login.getText().indexOf(':') + 2);
    }

    public String getName() {
        return name.getText().substring(name.getText().indexOf(':') + 2);
    }

    public String getLastname() {
        return lastname.getText().substring(lastname.getText().indexOf(':') + 2);
    }

    public String getEmail() {
        return email.getText().substring(email.getText().indexOf(':') + 2);
    }

    public void confirm(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.confirm = function(){return true;}");
        guardHttp(confirmButton).click();
    }

    public boolean isConfirmed(WebDriver driver) {
        try {
            return !confirmButton.isDisplayed();
        } catch (NoSuchElementException ex) {
            return true;
        }
    }

    public void toggleActive(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.confirm = function(){return true;}");
        guardHttp(activateButton).click();
    }

    public boolean isActive() {
        return activateButton.getText().contains("Aktywna") || activateButton.getText().contains("Active");
    }

    public void toggleAGroup(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.confirm = function(){return true;}");
        List<WebElement> elements = groupsForm.findElements(By.tagName("input"));
        guardHttp(elements.get(1)).click();
    }

}
