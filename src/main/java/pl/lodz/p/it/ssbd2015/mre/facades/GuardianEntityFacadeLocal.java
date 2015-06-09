package pl.lodz.p.it.ssbd2015.mre.facades;

import pl.lodz.p.it.ssbd2015.entities.GuardianEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.Optional;

/**
 * Interfejs fasad odczytujących z bazy danych dane opiekunów.
 * @author Michał Sośnicki
 */
@Local
public interface GuardianEntityFacadeLocal extends Read<Long, GuardianEntity> {
    /**
     * Zwraca opiekuna o wskazanym loginie.
     * @param login Login osoby z rolą opiekuna.
     * @return Być może znaleziony opiekun.
     */
    Optional<GuardianEntity> findByLogin(String login);
}
