package pl.lodz.p.it.ssbd2015;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Optional;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class Present<T> extends TypeSafeMatcher<Optional<T>> {

    @Override
    protected boolean matchesSafely(Optional<T> optional) {
        return optional.isPresent();
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("present");
    }

    @Factory
    public static <T> Matcher<Optional<T>> present() {
        return new Present<>();
    }

}
