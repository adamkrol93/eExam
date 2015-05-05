package pl.lodz.p.it.ssbd2015.mok.managers;

import pl.lodz.p.it.ssbd2015.entities.*;
import pl.lodz.p.it.ssbd2015.entities.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.mok.exceptions.PersonNotFoundException;
import pl.lodz.p.it.ssbd2015.mok.facades.GroupsStubEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.facades.PersonEntityFacadeLocal;
import pl.lodz.p.it.ssbd2015.mok.localization.Text;
import pl.lodz.p.it.ssbd2015.mok.utils.PasswordUtils;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Arrays;

/**
 * Created by adam on 24.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mok.managers.PersonManager")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class PersonManager implements PersonManagerLocal {

    @EJB
    private PersonEntityFacadeLocal personEntityFacade;

    @EJB
    private GroupsStubEntityFacadeLocal groupsStubEntityFacade;

    @EJB
    private PersonManagerLocal personManagerLocal;

    @EJB
    private EmailManagerLocal emailManager;

    @EJB
    private PersonManagerLocal personManager;

    @Resource
    private SessionContext sessionContext;

    @Override
    @RolesAllowed("ALL_LOGGED")
    public void editPerson(PersonEntity oldOne, PersonEntity newOne) throws ApplicationBaseException {
        oldOne.setName(newOne.getName());
        oldOne.setLastName(newOne.getLastName());
        oldOne.setEmail(newOne.getEmail());
        if (newOne.getPassword() != null && !newOne.getPassword().isEmpty()
                && !oldOne.getPassword().equals(newOne.getPassword())) {
            oldOne.setPassword(PasswordUtils.hashPassword(newOne.getPassword()));
        }
        personEntityFacade.edit(oldOne);
    }

    @Override
    @PermitAll
    public PersonEntity getPerson(String login) throws ApplicationBaseException {
        PersonEntity personEntity = personEntityFacade.findByLogin(login)
                .orElseThrow(() -> new PersonNotFoundException("Person with login: "+login+ "does not exists"));
        return personEntity;
    }

    @Override
    @RolesAllowed("CONFIRM_ACCOUNT_MOK")
    public void confirmPerson(PersonEntity personEntity) throws ApplicationBaseException {
        personEntity.setConfirm(true);
	    Text text = new Text();
	    emailManager.sendEmail(personEntity.getEmail(), text.getString("account.confirm"), text.getString("account.confirmed"));
	    personEntityFacade.edit(personEntity);
    }

    @Override
    @RolesAllowed("ACTIVATE_ACCOUNT_MOK")
        public void togglePersonActivation(PersonEntity personEntity) throws ApplicationBaseException {
        personEntityFacade.edit(personEntity);
        personEntity.setActive(!personEntity.isActive());
        personEntityFacade.edit(personEntity);
	    Text text = new Text();
	    if (personEntity.isActive()) {
		    emailManager.sendEmail(personEntity.getEmail(), text.getString("account.unlock"), text.getString("account.unlocked"));
	    } else {
		    emailManager.sendEmail(personEntity.getEmail(), text.getString("account.lock"), text.getString("account.locked"));
	    }
    }

    @Override
    @RolesAllowed("CHANGE_GROUP_MOK")
    public void toggleGroupActivation(PersonEntity personEntity, long id) throws ApplicationBaseException {
        boolean found = false;

        for (GroupsStubEntity groupsStub : personEntity.getGroupStubs()) {
            if (groupsStub.getId() == id) {
                groupsStub.setActive(!groupsStub.isActive());
                groupsStubEntityFacade.edit(groupsStub);
                found = true;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("The Person has no Group with id = " + id);
        }

	    Text text = new Text();
        emailManager.sendEmail(personEntity.getEmail(), text.getString("group.change"), text.getString("group.changed"));
    }

    @Override
    @PermitAll
    public boolean checkUniqueness(String login) {
        return !personEntityFacade.findByLogin(login).isPresent();
    }

    @Override
    @PermitAll
    public void register(PersonEntity newPerson) throws ApplicationBaseException {
        newPerson.setPassword(PasswordUtils.hashPassword(newPerson.getPassword()));
        newPerson.setActive(true);
        personManager.assignAllGroups(newPerson);
        personEntityFacade.create(newPerson);
	    Text text = new Text();
        emailManager.sendEmail(newPerson.getEmail(), text.getString("account.created"), text.getString("account.just.created"));
    }

    @Override
    @PermitAll
    public void assignAllGroups(PersonEntity person) {
        Arrays.asList(new AdministratorStubEntity(), new ExaminerStubEntity(), new GuardianStubEntity(),
                new StudentStubEntity(), new TeacherStubEntity())
                .forEach(group -> {
                    person.getGroupStubs().add(group);
                    group.setPerson(person);
                });
    }

    @Override
    public boolean hasGroup(String group) throws ApplicationBaseException {
        String login = sessionContext.getCallerPrincipal().getName();

        PersonEntity personEntity = this.getPerson(login);
        for (GroupsStubEntity grubStub : personEntity.getGroupStubs()) {
            if (grubStub.getName().equals(group)) {
                if (grubStub.isActive()) {
                    return true;
                }
            }
        }
        return false;
    }

}
