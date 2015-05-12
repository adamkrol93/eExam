package pl.lodz.p.it.ssbd2015.mok.facades;

import pl.lodz.p.it.ssbd2015.entities.PersonEntity;
import pl.lodz.p.it.ssbd2015.entities.facade.Create;
import pl.lodz.p.it.ssbd2015.entities.facade.Merge;
import pl.lodz.p.it.ssbd2015.entities.facade.Read;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * Interfejs definiujący dozwolone operacje na obiektach {@link PersonEntity} w kontekście bazy danych
 * @author Andrzej Kurczewski
 */
@Local
public interface PersonEntityFacadeLocal extends Merge<Long, PersonEntity>, Create<Long, PersonEntity>, Read<Long, PersonEntity>{
    /**
     * Metoda wyszukująca obiekt typu {@link PersonEntity} o podanym loginie w bazie danych
     * @param login login uzytkownika, którego chcemy wyszukać
     * @return obiekt typu Optional z PersonEntity lub nullem
     */
    Optional<PersonEntity> findByLogin(String login);

    /**
     * Metoda pozwalająca odnaleźć wszystkich uytkowników w których imieniu, nazwisku lub e-mailu występuje podana fraza.
     * @param phrase fraza której szukamy wśrów wymienionych wcześniej pól obiektu {@link PersonEntity}
     * @return listę ze znalezionymi osobami
     */
    List<PersonEntity> findByPhrase(String phrase);
}
