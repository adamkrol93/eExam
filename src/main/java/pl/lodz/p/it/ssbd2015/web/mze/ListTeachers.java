package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.mze.services.ExamsServiceRemote;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;

/**
 * Backing bean do strony z listą wszystkich nauczycieli.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listTeachersMZE")
@RequestScoped
public class ListTeachers {

    @EJB
    private ExamsServiceRemote examsService;

    private List<TeacherEntity> teachers;

    /**
     * Po utworzeniu beana odszukuje nauczycieli w systemie do wyświetlenia.
     */
    @PostConstruct
    private void initializeModel(){
        teachers = examsService.findAllTeachers();
    }

    public List<TeacherEntity> getTeachers(){
        return teachers;
    }
}
