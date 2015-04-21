package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;
import java.util.Calendar;
import java.util.List;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mre.facades.ExamEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class ExamEntityFacade implements ExamEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mre_PU")
    private EntityManager entityManager;

    @Override
    public Class<ExamEntity> getEntityClass() {
        return ExamEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<ExamEntity> findByDate(Calendar timestamp) {
        TypedQuery<ExamEntity> examQuery = entityManager.createNamedQuery("findExamByDate", ExamEntity.class);
        examQuery.setParameter("date", timestamp, TemporalType.TIMESTAMP);
        return examQuery.getResultList();
    }
}
