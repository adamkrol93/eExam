package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Location("login/login.xhtml")
public class LoginPage {

    @FindBy(css = "input[type=text]")
    private WebElement loginInput;

    @FindBy(css = "input[type=password]")
    private WebElement passwordInput;

    @FindBy(css = "input[type=submit]")
    private WebElement loginButton;

    @FindBy(id = "header-login-form:logout-button")
    private WebElement logoutButton;

    public void login(String login, String password) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        guardHttp(loginButton).click();
    }

    public void loginAsAdmin() {
        login("osoba", "nauczyciel");
    }

    public void logout() {
        guardHttp(logoutButton).click();
    }

    public boolean isLogged(WebDriver driver) {
        try {
            driver.findElement(By.id("header-login-form:logout-button"));
            return true;
        } catch (NoSuchElementException ex) {
            driver.findElement(By.id("header-login-form:login-button"));
            return false;
        }
    }
}
