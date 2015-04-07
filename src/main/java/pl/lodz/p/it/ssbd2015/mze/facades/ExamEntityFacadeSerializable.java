package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Bartosz Ignaczewski
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.mze.facades.ExamEntityFacadeSerializable")
public class ExamEntityFacadeSerializable implements ExamEntityFacadeSerializableLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.mzeser_PU")
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
