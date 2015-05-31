package pl.lodz.p.it.ssbd2015.web.moe;

import pl.lodz.p.it.ssbd2015.entities.ApproachEntity;
import pl.lodz.p.it.ssbd2015.moe.services.MarkApproachService;
import pl.lodz.p.it.ssbd2015.moe.services.MarkApproachServiceRemote;
import pl.lodz.p.it.ssbd2015.web.context.BaseContextBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Piotr on 2015-05-31.
 */
@ManagedBean(name = "rateApproachMOE")
@ViewScoped
public class RateApproach extends BaseContextBean implements Serializable {

    @EJB
    private MarkApproachServiceRemote markApproachService;

    long id;

    ApproachEntity approach;

    public ApproachEntity getApproach() {
        return approach;
    }

    public void setApproach(ApproachEntity approach) {
        this.approach = approach;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    protected void doInContext() {
        expectApplicationException(() -> {
            approach = markApproachService.findById(id);
        });
    }

    public String editApproach(){
        expectApplicationException(() -> {
            markApproachService.mark(approach.getAnswers());
        });
        return "listApproaches?faces-redirect=true";
    }

    public List<Integer> repeat(int times) {
        return IntStream.range(0, times).boxed().collect(Collectors.toList());
    }

}
