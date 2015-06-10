package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamEndBeforeStartException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamTitleNotUniqueException;
import pl.lodz.p.it.ssbd2015.mze.services.EditExamServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.web.localization.MessageUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.util.List;

/**
 * Backing bean dla formularza edycji egzaminu. Wspiera spory zestaw przypadków użycia: edycję, dodawanie i usuwanie
 * nauczycieli, usuwanie pytań.
 * @author Andrzej Kurczewski
 * @author Tobiasz Kowalski
 * @author Michał Sośnicki
 */
@ManagedBean(name = "editExamMZE")
@ViewScoped
public class EditExam extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private EditExamServiceRemote editExamService;

    private long id;
    private ExamEntity exam;
    private String oldTitle;

    private List<QuestionEntity> questionList;
    private List<TeacherEntity> teacherList;
    private List<TeacherEntity> teachersNotInExamList;

    private transient DataModel<QuestionEntity> questions;
    private transient DataModel<TeacherEntity> teachers;
    private transient DataModel<TeacherEntity> teachersNotInExam;

    private String message;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            exam = editExamService.findById(id);
            oldTitle = exam.getTitle();
            questionList = exam.getQuestions();
            teacherList = exam.getTeachers();
            teachersNotInExamList = editExamService.findAllNotInExam();
            setContext(EditExam.class, bean -> bean.id = id);
        });
    }

    /**
     * Metoda zapisuje zmiany dokonane w egzaminie. Po wykonaniu przekierowuje na tę samą stronę z odpowiednią
     * wiadomością ustawioną w kontekstmapie, jeśli się udało lub wprowadzając wiadomości przy odpowiednich
     * okienkach formularza, jeśli nie.
     * @return strona na którą przekierowywuje po skończonej operacji
     */
    public String saveExam() {
        return expectApplicationException(() -> {
            try {
                editExamService.editExam(exam);
            } catch (ExamTitleNotUniqueException ex) {
                message = null;
                MessageUtils.addLocalizedMessage(ex.getCode(), "edit-exam-form:title");
                return null;
            } catch (ExamEndBeforeStartException ex) {
                message = null;
                MessageUtils.addLocalizedMessage(ex.getCode(), "edit-exam-form:date_end");
                return null;
            }
            setContext(EditExam.class, bean -> {
                bean.id = id;
                bean.message = "mze.edit_exam.edited_message";
            });
            return "editExam?faces-redirect=true&includeViewParams=true";
        });
    }

    /**
     * Metoda usuwająca nauczyciela z egzaminu. Ustawia też
     * kontekst w ContextMap, by użytkownik wrócił do formularza edycji po odświeżeniu strony z odpowiednią wiadomością.
     * @return String z outcome, który pokieruje JSF.
     */
    public String removeTeacher() {
        return expectApplicationException(() -> {
            editExamService.removeTeacher(teachers.getRowData().getId());
            setContext(EditExam.class, bean -> {
                bean.id = id;
                bean.message = "mze.edit_exam.teacher_removed_message";
            });
            return "editExam?faces-redirect=true&includeViewParams=true";
        });
    }

    /**
     * Obsługuje operację usunięcia pytania przez wywołanie odpowiedniej metody z ziarna EJb. Ustawia też
     * kontekst w ContextMap, by użytkownik wrócił do formularza edycji po odświeżeniu strony.
     * @return String z outcome, który pokieruje JSF.
     */
    public String removeQuestion() {
        return expectApplicationException(() -> {
            editExamService.removeQuestion(questions.getRowData().getId());

            setContext(EditExam.class, bean -> {
                bean.id = id;
                bean.message = "mze.edit_exam.question_removed_message";
            });
            return "editExam?faces-redirect=true&includeViewParams=true";
        });
    }

    /**
     * Obsługuje operacje dodania nauczyciela przez wywołanie odpowiedniej metody z ziarna EJB. Ustawia też
     * kontekst w ContextMap by użytkownik wrócił do formularza edycji po odświeżeniu strony.
     * @return strona na którą przekierowywuje po skończonej operacji
     */
    public String addTeacher() {
        return expectApplicationException(()->{
            editExamService.addTeacher(teachersNotInExam.getRowData().getId());

            setContext(EditExam.class, bean -> {
                bean.id = id;
                bean.message = "mze.edit_exam.teacher_add_message";
            });
            return "editExam?faces-redirect=true&includeViewParams=true";
        });

    }

    public ExamEntity getExam() {
        return exam;
    }

    public void setExam(ExamEntity exam) {
        this.exam = exam;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DataModel<QuestionEntity> getQuestions() {
        if (questions == null) {
            questions = new ListDataModel<>(questionList);
        }
        return questions;
    }

    public DataModel<TeacherEntity> getTeachers() {
        if (teachers == null) {
            teachers = new ListDataModel<>(teacherList);
        }
        return teachers;
    }

    public DataModel<TeacherEntity> getTeachersNotInExam() {
        if (teachersNotInExam == null) {
            teachersNotInExam = new ListDataModel<>(teachersNotInExamList);
        }
        return teachersNotInExam;
    }

    public String getMessage() {
        return message;
    }

    public String getOldTitle() {
        return oldTitle;
    }
}
