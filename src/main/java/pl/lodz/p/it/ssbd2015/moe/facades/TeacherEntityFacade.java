package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by adam on 08.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.TeacherEntityFacade")
public class TeacherEntityFacade implements TeacherEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;

    @Override
    public Optional<TeacherEntity> findByLogin(String login) {
        TypedQuery<TeacherEntity> typedQuery = entityManager.createNamedQuery("findByLogin",TeacherEntity.class);
        typedQuery.setParameter("login",login);
        List<TeacherEntity> teacherEntities = typedQuery.getResultList();

        return Optional.ofNullable(typedQuery.getSingleResult());
    }

    @Override
    public Class<TeacherEntity> getEntityClass() {
        return TeacherEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
}
