package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Marcin on 2015-04-17.
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.EditPersonService")
@Interceptors(LoggingInterceptor.class)
public class EditPersonService extends BaseStatefulService implements EditPersonServiceRemote {

    private PersonEntity personEntity;

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    /**
     * Funkcja zwracająca informacje o Użytkowniku na podstawie loginu.
     * Funkcja ustawia również stanowa zmienna @personEntity
     * @param login
     * @return
     */
    @Override
    public PersonEntity findPersonForEdit(String login) throws PersonEntityNotFoundException {
        if(personEntity == null) {
            this.personEntity = personEntityFacade.findByLogin(login).orElseThrow(() -> new PersonEntityNotFoundException("exception.user_not_found"));
        }
        return personEntity;
    }
    /**
     * Funkcja edycji użytkownika. Edytuje dane uzytkownika
     * @param person dane osobe użytkownika
     */
    @Override
    public void editPerson(PersonEntity person) throws NoSuchAlgorithmException{
        this.personEntity = personEntityFacade.edit(personEntity);
        personEntity.setName(person.getName());
        personEntity.setLastName(person.getLastName());
        personEntity.setEmail(person.getEmail());
        personEntity.setPassword(md5(person.getPassword()));
    }
    /**
     * Funkcja szyfrująca hasło na md5
     * @param input dane do zaszyfrowania
     */
    private String md5(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("MD5");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
