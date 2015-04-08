package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by tobiasz_kowalski on 08.04.15.
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.ExamEntitySerializableFacade")
public class ExamEntitySerializableFacade implements ExamEntitySerializableFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moeser_PU")
    private EntityManager entityManager;

    @Override
    public Class<ExamEntity> getEntityClass() {

        return ExamEntity.class;
    }

    @Override
    public EntityManager getEntityManager() {

        return entityManager;
    }
}
