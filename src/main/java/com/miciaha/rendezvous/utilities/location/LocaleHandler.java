package com.miciaha.rendezvous.utilities.location;

import javafx.beans.property.StringProperty;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Properties;

/**
 * The type Locale handler.
 */
public class LocaleHandler {

    /**
     * Get language string.
     *
     * @return the string
     */
    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * Set text.
     *
     * @param text     the text
     * @param property the property
     */
    public static void setText(StringProperty text, String property) {
        try {
            text.setValue(textProperties().getProperty(property));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Text properties properties.
     *
     * @return the properties
     * @throws IOException the io exception
     */
// FUTURE ENHANCEMENT: Default to english if the system language does not have a property file
    public static Properties textProperties() throws IOException {
        FileInputStream fileInputStream = null;
        Properties prop = null;
        String directory = "src\\main\\java\\com\\miciaha\\rendezvous\\";
        String fileName = directory + "text_" + getLanguage() + ".properties";

        try {
            fileInputStream = new FileInputStream(fileName);
            prop = new Properties();
            prop.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            assert fileInputStream != null;
            fileInputStream.close();
        }
        return prop;
    }

    /**
     * The type Date time helper.
     */
    public static class DateTimeHelper {
        private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        private static final DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM);
        private static final DateTimeFormatter timestampFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        /**
         * Local date to timestamp utc timestamp.
         *
         * @param localDateTime the local date time
         * @return the timestamp
         */
        public static Timestamp localDateToTimestampUTC(LocalDateTime localDateTime) {
            Instant currentInstant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
            ZonedDateTime utcDateTime = currentInstant.atZone(ZoneId.of("UTC"));
            String utcDate = utcDateTime.format(timestampFormat);
            return Timestamp.valueOf(utcDate);
        }

        /**
         * Utc to local date time local date time.
         *
         * @param timestamp the timestamp
         * @return the local date time
         */
        public static LocalDateTime utcToLocalDateTime(Timestamp timestamp) {
            Instant utcInstance = timestamp.toLocalDateTime().atZone(ZoneId.of("UTC")).toInstant();
            ZonedDateTime localDateTime = utcInstance.atZone(ZoneId.systemDefault());
            return localDateTime.toLocalDateTime();
        }

        /**
         * Format time string.
         *
         * @param localDateTime the local date time
         * @return the string
         */
        public static String formatTime(LocalDateTime localDateTime) {
            LocalTime time = localDateTime.toLocalTime();
            return time.format(timeFormat);
        }

        /**
         * Format date string.
         *
         * @param localDateTime the local date time
         * @return the string
         */
        public static String formatDate(LocalDateTime localDateTime) {
            LocalDate date = localDateTime.toLocalDate();
            return date.format(dateFormat);
        }

        /**
         * Format date time string.
         *
         * @param localDateTime the local date time
         * @return the string
         */
        public static String formatDateTime(LocalDateTime localDateTime) {
            return localDateTime.format(dateTimeFormat);
        }

        /**
         * Get week of year string.
         *
         * @param localDateTime the local date time
         * @return the string
         */
        public static String getWeekOfYear(LocalDateTime localDateTime) {
            WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY, 1);
            TemporalField weekOfYear = weekFields.weekOfYear();
            int week = localDateTime.get(weekOfYear);
            return String.valueOf(week);
        }
    }
}
