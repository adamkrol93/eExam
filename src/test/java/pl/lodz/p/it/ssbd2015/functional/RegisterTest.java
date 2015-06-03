package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static pl.lodz.p.it.ssbd2015.TestUtils.getProbablyUniqueString;
import static pl.lodz.p.it.ssbd2015.TestUtils.getRandomAlphas;

/**
/**
 * @author Michał Sośnicki
 */
public class RegisterTest extends BaseFunctionalTest {

    @Page
    private RegisterPage registerPage;

    @Page
    private LoginPage loginPage;

    @Page
    private ShowUserPage showUserPage;

    @FindBy(css = "label[for=\"register-form:login\"] > span[class=\"text-danger\"]")
    private WebElement registerLoginError;

    @FindBy(css = "label[for=\"register-form:password\"] > span[class=\"text-danger\"]")
    private WebElement registerPasswordError;

    @FindBy(css = "label[for=\"register-form:confirm\"] > span[class=\"text-danger\"]")
    private WebElement registerConfirmError;

    @Test
    public void shouldSeePersonAfterItRegisters() {
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

        assertThat(showUserPage.getLogin(), is(login));
        assertThat(showUserPage.getName(), is(name));
        assertThat(showUserPage.getLastname(), is(lastname));
        assertThat(showUserPage.getEmail(), is(email));

        loginPage.logout();
    }

    @Test
    public void shouldNotRegisterTwice() {
        String login = getProbablyUniqueString();
        String password = getProbablyUniqueString();
        String name = getRandomAlphas(10);
        String lastname = getRandomAlphas(10);
        String email = name + "@gmail.com";

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, name, lastname, email);

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, name, lastname, email);

        assertThat(registerLoginError.getText(), anyOf(
                containsString("Login nie jest unikalny"),
                containsString("Login is not unique")
        ));
    }

    @Test
    public void shouldNotRegisterWithShortPassword() {
        String login = getProbablyUniqueString();
        String password = "short"; // shorter than 6
        String name = getRandomAlphas(10);
        String lastname = getRandomAlphas(10);
        String email = name + "@gmail.com";

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, name, lastname, email);

        assertThat(registerPasswordError.getText(), anyOf(
                containsString("Hasło musi zawierać przynajmniej 6 znaków"),
                containsString("Password must have at least 6 characters")
        ));
    }

    @Test
    public void shouldRegisterWithPasswordOfLengthSix() throws InterruptedException {
        String login = getProbablyUniqueString();
        String password = "longer"; // (>=6) . length
        String name = getRandomAlphas(10);
        String lastname = getRandomAlphas(10);
        String email = name + "@gmail.com";

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, name, lastname, email);

        Graphene.goTo(LoginPage.class);
        loginPage.loginAsAdmin();

        browser.get(deploymentUrl + ShowUserPage.urlTo(login));

        assertThat(showUserPage.getLogin(), is(login));
        assertThat(showUserPage.getName(), is(name));
        assertThat(showUserPage.getLastname(), is(lastname));
        assertThat(showUserPage.getEmail(), is(email));

        loginPage.logout();
    }

    @Test
    public void shouldNotRegisterWithUnconfirmedPassword() {
        String login = getProbablyUniqueString();
        String password = getProbablyUniqueString();
        String confirm = getProbablyUniqueString();
        String name = getRandomAlphas(10);
        String lastname = getRandomAlphas(10);
        String email = name + "@gmail.com";

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, confirm, name, lastname, email);

        assertThat(registerConfirmError.getText(), anyOf(
                containsString("Podane hasła są różne"),
                containsString("Passwords are different")
        ));
    }
}
