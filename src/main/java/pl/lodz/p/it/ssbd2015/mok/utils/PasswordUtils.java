package pl.lodz.p.it.ssbd2015.mok.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Michał Sośnicki <sosnicki.michal@gmail.com>
 */
public class PasswordUtils {

    private static final Logger logger = LoggerFactory.getLogger(PasswordUtils.class);

    /**
     * Funkcja szyfrująca hasło na md5
     * @param password dane do zaszyfrowania
     * @return zaszyfrowane hasło w MD5 może być null
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new BigInteger(1, bytes).toString(16);
        } catch (NoSuchAlgorithmException ex) {
            logger.error("Cannot hash password");
            return "";
        }
    }

}
