package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Andrzej Kurczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.TeacherEntityFacade")
public class TeacherEntityFacade implements TeacherEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mze_PU")
    private EntityManager entityManager;

    @Override
    public Class<TeacherEntity> getEntityClass() {
        return TeacherEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<TeacherEntity> findAllNotInExam(ExamEntity examEntity) {
        TypedQuery<TeacherEntity> query = entityManager.createNamedQuery("findAllTeacherNotInExam", TeacherEntity.class);
        query.setParameter("exam", examEntity);
        return query.getResultList();
    }
}