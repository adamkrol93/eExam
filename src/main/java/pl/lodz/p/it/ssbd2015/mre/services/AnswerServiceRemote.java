package pl.lodz.p.it.ssbd2015.mre.services;

import pl.lodz.p.it.ssbd2015.entities.AnswerEntity;
import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs do zarządzania podejściami. Pozwala na rozpoczęcie, zakończenie i edycję podejścia.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface AnswerServiceRemote {

    /**
     * Uczeń rozpoczyna podejście do egzaminu. Losowana jest pula pyta dla tego podejścia.
     * @param title Tytuł podejścia
     * @return id utworzonego podejścia
     * @throws ApplicationBaseException Rzucany, kiedy nie utworzy podejścia
     */
    long createApproach(String title) throws ApplicationBaseException;
    /**
     * Wyszukuje w bazie encje o podanym id
     * @param approachId identyfikator, którego szukamy
     * @return ApproachEntity jeżeli coś znajdzie
     * @throws ApplicationBaseException Rzucany, kiedy nie znajdzie encji w bazie
     */
    ApproachEntity findById(long approachId) throws ApplicationBaseException;
    /**
     * Uczeń poznaje treść pytań i udziela odpowiedzi.
     * Korzystam z metody pomocniczej {@link AnswerServiceRemote#findById(long approachId)}
     * do znalezienia podejścia którego odpowiedzi mają zostać zmienione
     * @param answers odpowiedzi udzielone przez użytkownika
     * @throws ApplicationBaseException Rzucany, kiedy nie zedytuje podejścia
     */
    void editApproach(List<AnswerEntity> answers) throws ApplicationBaseException;

    /**
     * Uczeń kończy podejście przed upływem maksymalnego czasu podejścia, dając w ten sposób możliwość
     * wcześniejszej oceny podejścia nauczycielowi. Podejście do zakończenia jest odnalezione w ramach przypadku MRE.2
     * w metodzie AnswerServiceRemote.findById(), ponieważ przypadek jest częścią tego samego widoku
     * @throws ApplicationBaseException Rzucany, kiedy nie uda się zakończyć podejścia
     */
    void endApproach() throws ApplicationBaseException;
}
