package pl.lodz.p.it.ssbd2015;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;
import pl.lodz.p.it.ssbd2015.entities.PersonEntity;

import java.util.Calendar;
import java.util.Random;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class TestUtils {

    private static final Random random = new Random();

    public static ExamEntity makeValidExam(ExaminerEntity examiner) {
        ExamEntity exam = new ExamEntity();
        exam.setTitle("Exam" + getProbablyUniqueString());
        exam.setCountTakeExam(random.nextInt());
        exam.setDateStart(Calendar.getInstance());
        exam.setDateEnd(Calendar.getInstance());
        exam.setDuration(random.nextInt());
        exam.setCountQuestion(random.nextInt());
        exam.setCreator(examiner);
        exam.setDateAdd(Calendar.getInstance());
        return exam;
    }

    public static PersonEntity makeValidPerson() {
        PersonEntity person = new PersonEntity();
        person.setLogin("Person" + getProbablyUniqueString());
        person.setName(getRandomString());
        person.setLastName(getRandomString());
        person.setPassword(getRandomString());
        person.setEmail(getRandomString() + "@gmail.com");
        person.setDateAdd(Calendar.getInstance());
        return person;
    }

    public static String getRandomString() {
        return Integer.toUnsignedString(random.nextInt(), 36);
    }

    public static String getProbablyUniqueString() {
        return getRandomString() + getRandomString() + getRandomString();
    }

}
