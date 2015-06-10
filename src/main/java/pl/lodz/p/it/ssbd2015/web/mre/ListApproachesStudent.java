package pl.lodz.p.it.ssbd2015.web.mre;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Backing bean do strony z listą podejść studenta.
 * @author Michał Sośnicki
 */
@ManagedBean(name = "listApproachesStudentMRE")
@ViewScoped
public class ListApproachesStudent extends ListApproaches {

    private static final long serialVersionUID = 1L;

    private String message;

    /**
     * Wyszukuje listę podejść obecnie zalogowanego studetna korzystając z ziarna EJB.
     */
    @PostConstruct
    private void initializeModel() {
        expectApplicationException(() -> {
            approachList = approachesService.listAllForStudent();
            setContext(ListApproachesStudent.class, bean -> bean.message = message);
        });
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
