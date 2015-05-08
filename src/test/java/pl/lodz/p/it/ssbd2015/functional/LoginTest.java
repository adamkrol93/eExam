package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static pl.lodz.p.it.ssbd2015.TestUtils.getProbablyUniqueString;
import static pl.lodz.p.it.ssbd2015.TestUtils.getRandomAlphas;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class LoginTest extends BaseFunctionalTest {

    @Page
    private LoginPage loginPage;

    @Page
    private RegisterPage registerPage;

    @Page
    private ShowUserPage showUserPage;

    @FindBy(id = "login-error-message")
    private WebElement loginErrorMessage;

    @FindBy(id = "header-login-form:logout-button")
    private WebElement logoutButton;

    @Test
    public void shouldFailToLoginWithoutConfirmation() throws Exception {
        String login = getProbablyUniqueString();
        String password = getProbablyUniqueString();

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, "Michał", "Testnicki", "costam@gmail.com");

        Graphene.goTo(LoginPage.class);
        loginPage.login(login, password);

        assertThat("User got an error message.", loginErrorMessage.getText(),
                anyOf(containsString("Invalid login or password"), containsString("Niepoprawna nazwa użytkownika lub hasło")));
        assertThat("User still sees a login button.", !loginPage.isLogged(browser));
    }

    @Test
    public void shouldFailToLoginWithoutRegistration() throws Exception {
        String login = getProbablyUniqueString();
        String password = getProbablyUniqueString();

        Graphene.goTo(LoginPage.class);
        loginPage.login(login, password);

        assertThat("User got an error message.", loginErrorMessage.getText(),
                anyOf(containsString("Invalid login or password"), containsString("Niepoprawna nazwa użytkownika lub hasło")));
        assertThat("User still sees a login button.", !loginPage.isLogged(browser));
    }

    @Test
    public void shouldLoginAfterConfirmation() throws Exception {
        browser.manage().window().setSize(new Dimension(1920, 1080));
        String login = getProbablyUniqueString();
        String password = getProbablyUniqueString();
        String name = getRandomAlphas(10);
        String lastname = getRandomAlphas(10);
        String email = name + "@gmail.com";

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, name, lastname, email);

        Graphene.goTo(LoginPage.class);
        loginPage.loginAsAdmin();

        browser.get(deploymentUrl + ShowUserPage.urlTo(login));
        showUserPage.confirm(browser);
        showUserPage.toggleAGroup(browser);

        guardHttp(logoutButton).click();
        Graphene.goTo(LoginPage.class);
        loginPage.login(login, password);

        assertThat("User sees a logout button.", loginPage.isLogged(browser));

        loginPage.logout();
    }
}
