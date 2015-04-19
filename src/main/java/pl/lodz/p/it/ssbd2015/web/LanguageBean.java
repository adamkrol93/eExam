package pl.lodz.p.it.ssbd2015.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Bean udostępnia możliwość zmiany języka.
 * @author Created by Bartosz Ignaczewski on 11.04.15.
 */
@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	private static Map<String, Object> countries;

	static {
		countries = new LinkedHashMap<>();
		countries.put("Polski", new Locale("pl"));
		countries.put("English", new Locale("en"));
	}

	public Locale getLocale() {
		return locale;
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public void countryLocaleCodeChanged(ValueChangeEvent e) {
		String newLocaleValue = e.getNewValue().toString();

		countries.entrySet().stream().filter(entry -> entry.getValue().toString().equals(newLocaleValue)).forEach(entry -> {
			locale = (Locale) entry.getValue();
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
		});
	}

}