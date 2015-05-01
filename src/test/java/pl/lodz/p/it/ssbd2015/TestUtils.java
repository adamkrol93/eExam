package pl.lodz.p.it.ssbd2015;

import pl.lodz.p.it.ssbd2015.entities.ExamEntity;
import pl.lodz.p.it.ssbd2015.entities.ExaminerEntity;

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

    public static String getRandomString() {
        return Integer.toUnsignedString(random.nextInt(), 36);
    }

    private static final char[] ALPHAS = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
                                          'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'w', 'x', 'y', 'z' };

    public static String getRandomAlphas(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            sb.append(ALPHAS[random.nextInt(ALPHAS.length)]);
        }
        return sb.toString();
    }

    public static String getProbablyUniqueString() {
        return getRandomString() + getRandomString() + getRandomString();
    }

    public static Calendar makeCalendar(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar;
    }
}
