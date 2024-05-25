package com.example.utils;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;
import java.util.UUID;

public class Utility {

    public static String generateRandomString(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    public static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    public static String generateUniqueIdentifier() {
        return UUID.randomUUID().toString();
    }
}
