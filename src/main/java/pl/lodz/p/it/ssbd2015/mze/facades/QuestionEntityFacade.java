package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * Implementacja interfejsu {@link QuestionEntityFacadeLocal}, pozwala na zarządzanie Pytaniami .
 * @author Andrzej Kurczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.QuestionEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class QuestionEntityFacade implements QuestionEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mze_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<QuestionEntity> getEntityClass() {
        return QuestionEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed("CREATE_QUESTION_MZE")
    public void create(QuestionEntity entity) throws ApplicationBaseException {
        QuestionEntityFacadeLocal.super.create(entity);
    }

    @Override
    @RolesAllowed("EDIT_QUESTION_MZE")
    public void edit(QuestionEntity entity) throws ApplicationBaseException {
        QuestionEntityFacadeLocal.super.edit(entity);
    }

    @Override
    @RolesAllowed("EDIT_QUESTION_MZE")
    public Optional<QuestionEntity> findById(Long id) {
        return QuestionEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed({"CREATE_EXAM_MZE", "LIST_QUESTIONS_MZE"})
    public List<QuestionEntity> findAll() {
        return QuestionEntityFacadeLocal.super.findAll();
    }
}
