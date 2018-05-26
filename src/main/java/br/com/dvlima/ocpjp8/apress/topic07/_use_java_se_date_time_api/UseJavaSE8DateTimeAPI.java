package br.com.dvlima.ocpjp8.apress.topic07._use_java_se_date_time_api;

/**
 * Use Java SE 8 Date/Time API
 * <p>
 * - Create and manage date-based and time-based events including a combination of
 * date and time into a single object using
 * LocalDate, LocalTime, LocalDateTime, Instant, Period, and Duration
 * - Work with dates and times across timezones and manage changes resulting from
 * daylight savings including Format date and times values
 * - Define and create and manage date-based and time-based events using Instant,
 * Period, Duration, and TemporalUnit
 */
public class UseJavaSE8DateTimeAPI {

    /** Create and manage date-based and time-based events including a combination of date and time into a single object
     * using LocalDate, LocalTime, LocalDateTime, Instant, Period, and Duration
     */

// The Java 8 date and time API uses ISO 8601 as the default calendar format.

// The java.time.LocalDate class represents a date without time or time zones; the java.time.LocalTime class represents
// time without dates and time zones; the java.time.LocalDateTime class represents both date and time without time zones.

// The java.time.Instant class represents a Unix timestamp.

// The java.time.Period is used to measure the amount of time in terms of years, months, and days.

// The java.time.Duration class represents time in terms of hours, minutes, seconds, and fraction of seconds.


    /** Work with dates and times across timezones and manage changes resulting from daylight savings including Format
     * date and times values
    */

// ZoneId identifies a time zone; ZoneOffset represents time zone offset from UTC/Greenwich.

// ZonedDateTime provides support for all three aspects: date, time, and time zone.

// You have to account for daylight savings time (DST) when working with different time zones.

// The java.time.format.DateTimeFormatter class provides support for reading or printing date and time values in
// different formats.

// The DateTimeFormatter class provides predefined constants (such as ISO_DATE and ISO_TIME) for formatting date and
// time values.

// You encode the format of the date or time using case-sensitive letters to form a date or time pattern string with
// the DateTimeFormatter class.

    /**
     * Define and create and manage date-based and time-based events using Instant, Period, Duration, and TemporalUnit
     * */
// The enumeration java.time.temporal.ChronoUnit implements the java.time.temporal.TemporalUnit interface.

// Both TemporalUnit and ChronoUnit deal with time unit values such as seconds, minutes, and hours and date values such
// as days, months, and years.
}
