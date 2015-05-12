package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.GroupsStubEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;

import javax.ejb.Local;

/**
 * Interfejs definiujący metody potrzebne do zarządzania encjami typu {@link GroupsStubEntity}
 * @author Andrzej Kurczewski
 */
@Local
public interface GroupsStubEntityFacadeLocal extends Merge<Long, GroupsStubEntity>{

}
