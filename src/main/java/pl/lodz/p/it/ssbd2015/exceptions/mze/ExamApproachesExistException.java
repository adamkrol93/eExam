package pl.lodz.p.it.ssbd2015.exceptions.mze;

/**
 * Wyjątek sygnalizujący, iż do egzaminu istnieją podejścia, a próbowano dokonać czynności, która jest wtedy
 * niedopuszczalna, na przykład usunąć z niego pytanie.
 * @author Michał Sośnicki
 */
public class ExamApproachesExistException extends ExamManagementException {
    public ExamApproachesExistException(String message) {
        super(message);
    }

    public ExamApproachesExistException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getCode() {
        return super.getCode() + ".approaches_exist";
    }
}
