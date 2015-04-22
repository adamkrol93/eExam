package pl.lodz.p.it.ssbd2015.web.localization;

import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;

/**
 * Własny interpolator, pozwalający wykorzystywać pliki properties kodowane w UTF-8
 * @author Andrzej Kurczewski
 */
public class UTF8ResourceBundleMessageInterpolator extends ResourceBundleMessageInterpolator {
    public UTF8ResourceBundleMessageInterpolator() {
        super(new UTF8ResourceBundleLocator(ResourceBundleMessageInterpolator.USER_VALIDATION_MESSAGES));
    }
}