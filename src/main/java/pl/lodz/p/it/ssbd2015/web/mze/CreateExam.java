package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamEndBeforeStartException;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamTitleNotUniqueException;
import pl.lodz.p.it.ssbd2015.mze.services.ExamCreationServiceRemote;
import pl.lodz.p.it.ssbd2015.web.SelectableItem;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.web.localization.MessageUtils;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Backing bean dla formularza tworzenia nowego egzaminu.
 * Przetrzymuje encję, której pola można wypełniać np. z formularza, a potem przesłać do utrwalenia w bazie.
 * Pozwala także uzyskać dostęp do list nauczycieli i pytań, skąd można wybierać encje do powiązania
 * z tworzonym egzaminem.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "createExamMZE")
@ViewScoped
public class CreateExam extends BaseContextBean {

    private static final long serialVersionUID = 1L;

    @EJB
    private ExamCreationServiceRemote examCreationService;

    private String message;

    private ExamEntity exam;

    private List<SelectableItem<QuestionEntity>> questions;

    private List<SelectableItem<TeacherEntity>> teachers;

    @Override
    protected void doInContext() {
        resetContext();
    }

    /**
     * Wyszukuje zaraz po utworzeniu dostępnych nauczycieli i pytania, korzystając z ziarna do tworzenia egzaminu.
     */
    @PostConstruct
    private void initialize() {
        exam = new ExamEntity();
        questions = SelectableItem.wrap(examCreationService.findAllQuestions());
        teachers = SelectableItem.wrap(examCreationService.findAllTeachers());
    }

    /**
     * Zwraca jakiś pozytywny komunikat, informujący użytkownika o pomyślnie przeprowadzonej operacji.
     * @return Radosny komunikat.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Zwraca pustą z początku encje, której pola można wypełniać i następnie przesłać do systemu w celu utrwalenia.
     * @return Pusty egzamin do wypełnienia i być może przesłania w celu utrwalenia.
     */
    public ExamEntity getExam() {
        return exam;
    }

    /**
     * Zwraca listę zaznaczalnych obiektów opakowujących pytani. Utworzony egzamin będzie
     * na starcie powiązany z zaznaczonymi pytaniami z tej listy.
     * @return Lista zaznaczalnych obiektów opakowujących pytania.
     */
    public List<SelectableItem<QuestionEntity>> getQuestions() {
        return questions;
    }

    /**
     * Zwraca listę zaznaczalnych obiektów opakowujących nauczycieli. Utworzony egzamin będzie
     * na starcie powiązany z zaznaczonymi nauczycielami z tej listy.
     * @return Lista zaznaczalnych obiektów opakowujących nauczycieli.
     */
    public List<SelectableItem<TeacherEntity>> getTeachers() {
        return teachers;
    }

    /**
     * Metoda akcji wykonywanej podczas tworzenia nowego egzaminu
     * @return strona na która ma zostać przekierowany uzytkownik po wykonaniu akcji
     */
    public String createExam() {
        return expectApplicationException(() -> {
            List<Long> questionIds = questions.stream().filter(SelectableItem::isSelected)
                    .map(selected -> selected.getItem().getId()).collect(Collectors.toList());
            List<Long> teacherIds = teachers.stream().filter(SelectableItem::isSelected)
                    .map(selected -> selected.getItem().getId()).collect(Collectors.toList());

            try {
                examCreationService.create(exam, questionIds, teacherIds);
            } catch (ExamTitleNotUniqueException ex) {
                MessageUtils.addLocalizedMessage(ex.getCode(), "create-exam-form:title");
                return null;
            } catch (ExamEndBeforeStartException ex) {
                MessageUtils.addLocalizedMessage(ex.getCode(), "create-exam-form:date_end");
                return null;
            }

            setContext(CreateExam.class, bean -> bean.message = "mze.create_exam.created_message");
            return "createExam?faces-redirect=true&includeViewParams=true";
        });
    }
}
