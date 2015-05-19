package pl.lodz.p.it.ssbd2015.mze.services;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.exceptions.ApplicationBaseException;

import javax.ejb.Remote;
import java.util.List;

/**
 * Interfejs pozwalający na wyświetlenie wszystkich egzaminów oraz skolnowanie egzaminu.
 * @author Bartosz Ignaczewski
 */
@Remote
public interface ExamListServiceRemote {


    /**
     * Metoda pozwala na wyszukanie wszystkich egzaminów w systemie.
     * @return Lista ({@link List}) z egzaminami w systemie
     */
    List<ExamEntity> findAll();

    /**
     * Odszukuje egzamin po idku na liście ustawionej wcześniej w findAll i tworzy jego kopię.
     * Pola kontrolujące odpowiedzialność - czyli id twórcy, ostatnio modyfikującego, daty, itd. są oczywiście
     * ustawiane na nowo.
     * TODO: Imho nie jest tu potrzebna transakcja aplikacyjna. To, że user otrzyma kopię tego, co ostatnio widział,
     * choć egzamin uległ zmianie, jest wręcz pożądane!
     * @param examId Identyfikator egzaminu do skopiowania.
     * @throws ApplicationBaseException Rzucany, gdy egzamin nie został odnaleziony i jeżeli jakimś cudem baza nie chce przyjąć kopii.
     */
    void cloneExam(long examId) throws ApplicationBaseException;

}
