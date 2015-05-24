package pl.lodz.p.it.ssbd2015.web.mze;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.QuestionEntity;
import pl.lodz.p.it.ssbd2015.entities.TeacherEntity;
import pl.lodz.p.it.ssbd2015.exceptions.mze.ExamTitleNotUnique;
import pl.lodz.p.it.ssbd2015.mze.services.EditExamServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;
import pl.lodz.p.it.ssbd2015.web.localization.MessageUtils;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import java.io.Serializable;

/**
 * Backing bean dla formularza edycji egzaminu.
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "editExamMZE")
@ViewScoped
public class EditExam extends BaseContextBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EditExamServiceRemote editExamService;

    private long id;
    private ExamEntity exam;
    private String oldTitle;
    private transient DataModel<QuestionEntity> questions;
    private transient DataModel<TeacherEntity> teachers;

    private String message;

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            exam = editExamService.findById(id);
            oldTitle = exam.getTitle();
            questions = new ListDataModel<>(exam.getQuestions());
            teachers = new ListDataModel<>(exam.getTeachers());
            resetContext();
            setContext(EditExam.class, bean -> bean.id = id);
        });
    }

    public String saveExam() {
        return expectApplicationException(() -> {
            try {
                editExamService.editExam(exam);
            } catch (ExamTitleNotUnique ex) {
                message = null;
                MessageUtils.addLocalizedMessage(ex.getCode(), "edit-exam-form:title");
                return null;
            }
            setContext(EditExam.class, (bean -> {
                bean.id = id;
                bean.message = "mze.edit_exam.edited_message";
            }));
            return "editExam?faces-redirect=true&includeViewParams=true";
        });
    }

    public String removeTeacher() {
        throw new UnsupportedOperationException("Removing teacher is not supported" + " " + teachers.getRowData());
    }

    public String removeQuestion() {
        throw new UnsupportedOperationException("Removing questions is not supported" + " " + questions.getRowData());
    }

    public String addTeacher() {
        throw new UnsupportedOperationException("Adding teachers is not supported");
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
        return questions;
    }

    public DataModel<TeacherEntity> getTeachers() {
        return teachers;
    }

    public String getMessage() {
        return message;
    }

    public String getOldTitle() {
        return oldTitle;
    }
}
