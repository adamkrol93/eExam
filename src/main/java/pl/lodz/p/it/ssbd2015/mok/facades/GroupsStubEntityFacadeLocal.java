package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * @author Andrzej Kurczewski
 */
@Local
public interface GroupsStubEntityFacadeLocal {
    void insert(GroupsStubEntity groupsStubEntity);
    void update(GroupsStubEntity groupsStubEntity);
    Optional<GroupsStubEntity> find(Object id);
    List<GroupsStubEntity> findAll();
}
