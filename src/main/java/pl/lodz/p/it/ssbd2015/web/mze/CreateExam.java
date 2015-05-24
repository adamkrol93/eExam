package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamTitleNotUnique;
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
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
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

    @PostConstruct
    private void initialize() {
        exam = new ExamEntity();
        questions = SelectableItem.wrap(examCreationService.findAllQuestions());
        teachers = SelectableItem.wrap(examCreationService.findAllTeachers());
    }

    public String getMessage() {
        return message;
    }

    public ExamEntity getExam() {
        return exam;
    }

    public List<SelectableItem<QuestionEntity>> getQuestions() {
        return questions;
    }

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
            } catch (ExamTitleNotUnique ex) {
                MessageUtils.addLocalizedMessage(ex.getCode(), "create-exam-form:title");
                return null;
            }

            setContext(CreateExam.class, bean -> bean.message = "mze.create_exam.created_message");
            return "createExam?faces-redirect=true&includeViewParams=true";
        });
    }
}
