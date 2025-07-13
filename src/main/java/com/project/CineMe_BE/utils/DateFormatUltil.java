package com.project.CineMe_BE.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class DateFormatUltil {

    public static LocalDate formatDate(String date) {
        if (date == null || date.isEmpty()) {
            return null;
        }
        DateTimeFormatter flexibleFormatter = new DateTimeFormatterBuilder()
                .appendValue(ChronoField.YEAR, 4)
                .appendLiteral('-')
                .appendValue(ChronoField.MONTH_OF_YEAR)
                .appendLiteral('-')
                .appendValue(ChronoField.DAY_OF_MONTH)
                .toFormatter();
        return LocalDate.parse(date, flexibleFormatter);
    }
}
