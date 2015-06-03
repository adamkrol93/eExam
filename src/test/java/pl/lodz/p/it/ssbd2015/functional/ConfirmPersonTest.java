package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static pl.lodz.p.it.ssbd2015.TestUtils.getProbablyUniqueString;
import static pl.lodz.p.it.ssbd2015.TestUtils.getRandomAlphas;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Michał Sośnicki
 */
public class ConfirmPersonTest extends BaseFunctionalTest {

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
    public void shouldBeConfirmedAfterConfirmation() throws Exception {
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

        assertThat("User is unconfirmed.", !showUserPage.isConfirmed(browser));

        showUserPage.confirm(browser);

        assertThat("User is confirmed.", showUserPage.isConfirmed(browser));

        loginPage.logout();
    }
}
