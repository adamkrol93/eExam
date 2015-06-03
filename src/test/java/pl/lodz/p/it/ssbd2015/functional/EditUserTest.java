package pl.lodz.p.it.ssbd2015.functional;

import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.Page;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static pl.lodz.p.it.ssbd2015.TestUtils.getProbablyUniqueString;
import static pl.lodz.p.it.ssbd2015.TestUtils.getRandomAlphas;

/**
 * @author Michał Sośnicki
 */
public class EditUserTest extends BaseFunctionalTest {

    @Page
    private LoginPage loginPage;

    @Page
    private RegisterPage registerPage;

    @Page
    private ShowUserPage showUserPage;

    @Page
    private EditUserPage editUserPage;

    @Test
    public void shouldContainPersonDataAtFirst() throws Exception {
        String login = getProbablyUniqueString();
        String password = getProbablyUniqueString();
        String name = getRandomAlphas(10);
        String lastname = getRandomAlphas(10);
        String email = name + "@gmail.com";

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, name, lastname, email);

        Graphene.goTo(LoginPage.class);
        loginPage.loginAsAdmin();

        browser.get(deploymentUrl + EditUserPage.urlTo(login));

        assertThat(editUserPage.getName(), is(name));
        assertThat(editUserPage.getLastname(), is(lastname));
        assertThat(editUserPage.getEmail(), is(email));

        loginPage.logout();
    }

    @Test
    public void shouldSucceedChangingNothingWhenNoChangesAreMade() throws Exception {
        String login = getProbablyUniqueString();
        String password = getProbablyUniqueString();
        String name = getRandomAlphas(10);
        String lastname = getRandomAlphas(10);
        String email = name + "@gmail.com";

        Graphene.goTo(RegisterPage.class);
        registerPage.register(login, password, name, lastname, email);

        Graphene.goTo(LoginPage.class);
        loginPage.loginAsAdmin();

        browser.get(deploymentUrl + EditUserPage.urlTo(login));

        editUserPage.submit();

        assertThat(editUserPage.getMessage(), anyOf(
            containsString("Dane użytkownika zostały zmienione"),
            containsString("Person data has been changed")
        ));
        assertThat(editUserPage.getName(), is(name));
        assertThat(editUserPage.getLastname(), is(lastname));
        assertThat(editUserPage.getEmail(), is(email));

        loginPage.logout();
    }

    @Test
    public void shouldNotChangePasswordWhenThePasswordFieldsAreLeftEmpty() throws Exception {
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

        browser.get(deploymentUrl + EditUserPage.urlTo(login));
        editUserPage.submit();

        loginPage.logout();

        Graphene.goTo(LoginPage.class);
        loginPage.login(login, password);

        assertThat("Person could login with old password.", loginPage.isLogged(browser));

        loginPage.logout();
    }

    @Test
    public void shouldChangePasswordSimply() throws Exception {
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

        browser.get(deploymentUrl + EditUserPage.urlTo(login));
        String newPassword = getProbablyUniqueString();
        editUserPage.setPassword(newPassword);

        editUserPage.submit();

        loginPage.logout();

        Graphene.goTo(LoginPage.class);
        loginPage.login(login, newPassword);

        assertThat("Person can login with new password.", loginPage.isLogged(browser));

        loginPage.logout();
    }

}
