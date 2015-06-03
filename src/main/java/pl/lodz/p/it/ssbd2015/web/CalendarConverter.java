package pl.lodz.p.it.ssbd2015.web;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;
import java.util.Calendar;
import java.util.Date;

/**
 * Konwerter JSFowy dla typu Calendar z biblioteki Javy.
 * Rozszerza standardowy DateTimeConverter, starając się zachować wszystkie jego funkcje.
 * @author Michał Sośnicki
 */
@FacesConverter("calendarConverter")
public class CalendarConverter extends DateTimeConverter {

    public CalendarConverter() {
        super.setPattern("yyyy-MM-dd HH:mm");
    }

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Date date = (Date) super.getAsObject(context, component, value);
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            return calendar;
        }
        else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Calendar calendar = (Calendar) value;
        Date date = calendar != null ? calendar.getTime() : null;
        return super.getAsString(context, component, date);
    }

}
