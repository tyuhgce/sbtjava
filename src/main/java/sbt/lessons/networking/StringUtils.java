package main.java.sbt.lessons.networking;

import java.math.BigInteger;
import java.security.SecureRandom;

public abstract class StringUtils {
    public static String getRandomString() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
