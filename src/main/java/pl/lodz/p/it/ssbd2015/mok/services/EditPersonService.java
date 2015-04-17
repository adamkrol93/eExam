package pl.lodz.p.it.ssbd2015.mok.services;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.services.BaseStatefulService;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonEntityNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;

import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Marcin on 2015-04-17.
 */
@Stateful(name = "pl.lodz.p.it.ssbd2015.mok.services.EditPersonService")
@Interceptors(LoggingInterceptor.class)
public class EditPersonService extends BaseStatefulService implements EditPersonServiceRemote{

    private PersonEntity personEntity;

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    @Override
    public PersonEntity findPersonForEdit(String login) throws PersonEntityNotFoundException {
        if(personEntity == null) {
            this.personEntity = personEntityFacade.findByLogin(login).orElseThrow(() -> new PersonEntityNotFoundException("exception.user_not_found"));
        }
        return personEntity;
    }

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
     * @return zaszyfrowane hasło w MD5 może być null
     */
    private String md5(String input) throws NoSuchAlgorithmException {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(input.getBytes(), 0, input.length());
            BigInteger i = new BigInteger(1,m.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
