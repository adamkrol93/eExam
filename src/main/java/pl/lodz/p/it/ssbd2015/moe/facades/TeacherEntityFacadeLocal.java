package pl.lodz.p.it.ssbd2015.moe.facades;

import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.Optional;

/**
 * Interfejs definiujący operacje dozwolone do wykonania na encji
 * @author Andrzej Kurczewski
 * @author Adam Król
 */
@Local
public interface TeacherEntityFacadeLocal extends Read<Long,TeacherEntity>{
    /**
     * Metoda wyszukuje w bazie danych nauczyciela o podanym loginie.
     * @param login login nauczyciela którego szukamy
     * @return Optionl typu TeacherEnityt z encją znalezioną w bazie lub nullem
     */
    Optional<TeacherEntity> findByLogin(String login);
}
