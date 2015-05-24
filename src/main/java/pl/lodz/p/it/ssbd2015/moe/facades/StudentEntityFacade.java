package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.StudentEntity;
import pl.lodz.p.it.ssbd2015.entities.services.LoggingInterceptor;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.StudentGuardianForeignKeyException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.StudentIllegalArgumentException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.StudentManagementException;
import pl.lodz.p.it.ssbd2015.exceptions.moe.StudentOptimisticLockException;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

/**
 * Klasa obsługująca obsługę bazodanową encji Student.
 * @author Adam Król
 */
@Stateless(name = "pl.lodz.p.it.ssbd2015.moe.facades.StudentEntityFacade")
@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Interceptors(LoggingInterceptor.class)
public class StudentEntityFacade implements StudentEntityFacadeLocal {

    @PersistenceContext(unitName = "pl.lodz.p.it.ssbd2015.moe_PU")
    private EntityManager entityManager;

    @Override
    @DenyAll
    public Class<StudentEntity> getEntityClass() {
        return StudentEntity.class;
    }

    @Override
    @DenyAll
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public void edit(StudentEntity entity) throws ApplicationBaseException {
        try {
            StudentEntityFacadeLocal.super.edit(entity);
        }catch (IllegalArgumentException ex)
        {
            throw new StudentIllegalArgumentException(entity + " is an illegal argument to Merge.edit(e)",ex);
        }catch (OptimisticLockException ex)
        {
            throw  new StudentOptimisticLockException(entity + " is being edited by someone else",ex);
        }catch (PersistenceException ex)
        {
            if(ex.getMessage().contains("groups_groups_guardian_fkey"))
            {
                throw  new StudentGuardianForeignKeyException("Guardian id is incorrect for entity:" + entity, ex);
            }else{
                throw  new StudentManagementException("Persisting " + entity + " violated a database constraint.", ex);
            }
        }
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public Optional<StudentEntity> findById(Long id) {
        return StudentEntityFacadeLocal.super.findById(id);
    }

    @Override
    @RolesAllowed("ADD_STUDENTS_GUARDIAN_MOE")
    public List<StudentEntity> findAll() {
        return StudentEntityFacadeLocal.super.findAll();
    }
}
