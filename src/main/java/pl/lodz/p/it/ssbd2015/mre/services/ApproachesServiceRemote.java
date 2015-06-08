package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs pozwalający na zarządzani zakończonymi podejściami, a raczej na ich wyświetlanie.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface ApproachesServiceRemote {

    /**
     * Zwraca listę podejść ucznia zalogowanemu uczniowi. Korzysta z SessionContextu,  * by zdobyć dane
     * zalogowanego użytkownika, a następnie przez fasady wyszukuje encję Student odpowiadającą mu.
     * Dla studenta zwraca po prostu jego listę podejść.
     * @return Lista podejść obecnie zalogowanego ucznia.
     * @throws ApplicationBaseException Jeżeli np. osoba wywołująca nie jest studentem.
     */
    List<ApproachEntity> listAllForStudent() throws ApplicationBaseException;

    /**
     * Zwraca listę podejść ucznia opiekunowi. Korzysta z SessionContextu, by zdobyć dane zalogowanego użytkownika,
     * a następnie przez fasady wyszukuje encję Guardin odpowiadającą mu. Dla opiekuna zwraca konkatenację podejść jego podopiecznych.
     * @return Lista podejść wszystkich uczniów obecnie zalogowanego opiekuna.
     * @throws ApplicationBaseException Jeżeli np. nie uda się odnaleźć encji obecnie zalogowanego opiekuna.
     */
    List<ApproachEntity> listAllForGuardian() throws ApplicationBaseException;

    /**
     * Uczeń lub opiekun wyświetlają szczegóły zakończonego podejścia. Jako szczegóły rozumiemy
     * czas rozpoczęcia i zakończenia, wszystkie pytania i odpowiedzi, a także, jeśli podejście zostało już ocenione,
     * ocenę oraz nauczyciela, który ją wystawił.
     * @param id Klucz główny w bazie szukanego podejścia.
     * @return Podejście o szukanym id.
     * @throws ApplicationBaseException Rzucany jak podejście o takim id nie istnieje.
     */
    ApproachEntity findById(long id) throws ApplicationBaseException;

    /**
     * Wyświetla studentowi listę wszystich egzaminów do których może rozpocząć podejście.
     * @return Lista egzaminów do których może przystąpić student
     * @throws ApplicationBaseException Rzucany, kiedy metoda nie zwróci odpowiednich egzaminów
     */
    List<ExamEntity> findAvailableExams() throws ApplicationBaseException;
}
