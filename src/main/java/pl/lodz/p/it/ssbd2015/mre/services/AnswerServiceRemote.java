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

    Long createApproach(String title) throws ApplicationBaseException;
    /**
     * Wyszukuje w bazie encje o podanym id
     * @param approachId identyfikator, którego szukamy
     * @return ApproachEntity jeżeli coś znajdzie
     * @throws ApplicationBaseException
     */
    ApproachEntity findById(long approachId) throws ApplicationBaseException;
    /**
     * Funkcja edycji podejścia. Edytuje dane podejścia a dokładniej odpowiedzi użytkownika na pytania.
     * Uczeń poznaje treść pytań i udziela odpowiedzi.
     * @param answers odpowiedzi udzielone przez użytkownika
     */
    void editApproach(List<AnswerEntity> answers) throws ApplicationBaseException;

    void endApproach() throws ApplicationBaseException;
}