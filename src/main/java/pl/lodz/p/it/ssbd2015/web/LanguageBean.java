package pl.lodz.p.it.ssbd2015.web;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Bean udostępnia możliwość zmiany języka.
 * @author Created by Bartosz Ignaczewski on 11.04.15.
 * @author Andrzej Kurczewski
 */
@ManagedBean(name = "language")
@SessionScoped
public class LanguageBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String localeCode = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();

	private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

	private static List<Map.Entry<String, Object>> countries;

	static {
		Map<String, Object> countriesMap = new LinkedHashMap<>();
		countriesMap.put("Polski", new Locale("pl"));
		countriesMap.put("English", new Locale("en"));
		countries = countriesMap.entrySet()
								.stream()
								.sorted(Comparator.comparing(Map.Entry::getKey))
								.collect(Collectors.toList());
	}

	public Locale getLocale() {
		return locale;
	}

	public List<Map.Entry<String, Object>> getCountriesInMap() {
		return countries;
	}

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}


	/**
	 * Pozwala zmienić język strony dla uzytkownika, utrzymywany w sesji.
	 * @param newLocale Nowa wartość języka do ustawienia
	 */
	public void changeLocale(Locale newLocale) {
		locale = newLocale;
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}
}