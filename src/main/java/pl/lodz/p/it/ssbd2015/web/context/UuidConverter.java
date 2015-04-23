package pl.lodz.p.it.ssbd2015.web.context;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.UUID;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
@FacesConverter(value = "uuidConverter")
public class UuidConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return UUID.fromString(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();
    }
}
