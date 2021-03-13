package org.example.config.datetime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class DataTimeConfig {

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<>() {
            @Override
            public LocalDate parse(String text, Locale locale) {
                return LocalDate.parse(text, DateTimeFormatter.ISO_DATE);
            }

            @Override
            public String print(LocalDate localDate, Locale locale) {
                return DateTimeFormatter.ISO_DATE.format(localDate);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<>() {
            @Override
            public LocalDateTime parse(String text, Locale locale) {
                return LocalDateTime.parse(text, DateTimeFormatter.ISO_DATE_TIME);
            }

            @Override
            public String print(LocalDateTime localDateTime, Locale locale) {
                return DateTimeFormatter.ISO_DATE_TIME.format(localDateTime);
            }
        };
    }

    @Bean
    public Formatter<LocalTime> localTimeFormatter() {
        return new Formatter<>() {
            @Override
            public LocalTime parse(String text, Locale locale) {
                return LocalTime.parse(text, DateTimeFormatter.ISO_LOCAL_TIME);
            }

            @Override
            public String print(LocalTime localTime, Locale locale) {
                return DateTimeFormatter.ISO_LOCAL_TIME.format(localTime);
            }
        };
    }

    @Bean
    public Formatter<OffsetDateTime> offsetDateTimeFormatter() {
        return new Formatter<>() {
            @Override
            public OffsetDateTime parse(String text, Locale locale) {
                return OffsetDateTime.parse(text, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            }

            @Override
            public String print(OffsetDateTime offsetDateTime, Locale locale) {
                return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime);
            }
        };
    }
}
