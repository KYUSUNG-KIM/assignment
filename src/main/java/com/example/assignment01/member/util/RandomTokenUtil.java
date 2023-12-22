package com.example.assignment01.member.util;


import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RandomTokenUtil {

    public static String generateRandomToken() {

        StringBuilder builder = new StringBuilder();
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMddHHmmss"));
        String randomStringValue = RandomStringUtils.randomAlphanumeric(13);

        builder.append(randomStringValue);
        builder.append(formatDate);

        return builder.toString();
    }

}
