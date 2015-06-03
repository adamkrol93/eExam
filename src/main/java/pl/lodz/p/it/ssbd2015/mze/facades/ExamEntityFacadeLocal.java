package pl.lodz.p.it.ssbd2015.mze.facades;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Create;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;

/**
 * Interfejs do zarządzania Egzaminami w bazie danych.
 * @author Andrzej Kurczewski
 * @author Michał Sośnicki
 */
@Local
public interface ExamEntityFacadeLocal extends Read<Long, ExamEntity>, Merge<Long, ExamEntity>, Create<Long, ExamEntity> {
}
