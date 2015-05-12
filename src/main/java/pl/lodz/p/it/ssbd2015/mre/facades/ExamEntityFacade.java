package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.*;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class ExamEntityFacade implements ExamEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<ExamEntity> getEntityClass() {
        return ExamEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    @RolesAllowed("END_APPROACH_MRE")
    public void edit(ExamEntity entity) throws ApplicationBaseException {
        ExamEntityFacadeLocal.super.edit(entity);
    }

    @Override
    @RolesAllowed("END_APPROACH_MRE")
    public Optional<ExamEntity> findById(Long id) {
        return ExamEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("END_APPROACH_MRE")
    public List<ExamEntity> findAll() {
        return ExamEntityFacadeLocal.super.findAll();
    }


    @Override
    @RolesAllowed("LIST_AVAILABLE_EXAMS")
    public List<ExamEntity> findByDate(Calendar timestamp) {
        TypedQuery<ExamEntity> examQuery = entityManager.createNamedQuery("findExamByDate", ExamEntity.class);
        examQuery.setParameter("date", timestamp, TemporalType.TIMESTAMP);
        return examQuery.getResultList();
    }

    @Override
    @RolesAllowed("START_SOLVING_EXAM_MRE")
    public Optional<ExamEntity> findByTitle(String title) {
        TypedQuery<ExamEntity> examQuery = entityManager.createNamedQuery("findExamByTitle", ExamEntity.class);
        examQuery.setParameter("title", title);

        try {
            ExamEntity examEntity = examQuery.getSingleResult();
            return Optional.of(examEntity);
        }
        catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
