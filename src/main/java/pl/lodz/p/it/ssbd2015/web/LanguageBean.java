package pl.lodz.p.it.ssbd2015.web;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Bartosz Ignaczewski on 11.04.15.
 */
@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode;

	private Locale locale;

	private static Map<String, Object> countries;

	static {
		countries = new LinkedHashMap<String, Object>();
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

	public void countryLocaleCodeChanged(ValueChangeEvent e){

		String newLocaleValue = e.getNewValue().toString();

		countries.entrySet().stream().filter(entry -> entry.getValue().toString().equals(newLocaleValue)).forEach(entry -> {
			locale = (Locale) entry.getValue();
			FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);

		});

	}

}