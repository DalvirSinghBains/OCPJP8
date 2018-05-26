package br.com.dvlima.ocpjp8.apress.topic07._use_java_se_date_time_api._01_localdate_localtime_localdatetime_iInstant_period_duration;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class JavaTime {

    /*
    The Java 8 date and time API uses ISO 8601 as the default calendar format. In this internationally
    accepted format, the date and time values are sorted from the largest to the smallest unit of time: year,
    month/week, day, hour, minute, second, and millisecond/nanosecond.

    ISO-8601 calendar system: YYYY-MM-DD HH:MM:SS.nanosecond
     */
    public static void main(String[] args) {
        // usingLocalDateLocalTimeLocalDateTime();
        // importantMethodsInTheLocalDateClass();
        // usingTheLocalTimeClass();
        // usingTheLocalDateTimeClass();
        // usingTheInstantClass();
        // usingThePeriodClass();
        // usingTheDurationClass();
        usingTheTemporalUnitInterface();
    }

    private static void usingTheTemporalUnitInterface() {
        System.out.println("ChronoUnit DateBased TimeBased Duration");
        System.out.println("---------------------------------------");

        for (ChronoUnit unit : ChronoUnit.values()) {
            System.out.printf("%10s \t %b \t\t %b \t\t %s %n", unit, unit.isDateBased(), unit.isTimeBased(), unit.getDuration());
        }

        /*
        ChronoUnit  DateBased   TimeBased   Duration
        -----------------------------------------------------------
        Nanos       false       true        PT0.000000001S
        Micros      false       true        PT0.000001S
        Millis      false       true        PT0.001S
        Seconds     false       true        PT1S
        Minutes     false       true        PT1M
        Hours       false       true        PT1H
        HalfDays    false       true        PT12H
        Days        true        false       PT24H
        Weeks       true        false       PT168H
        Months      true        false       PT730H29M6S
        Years       true        false       PT8765H49M12S
        Decades     true        false       PT87658H12M
        Centuries   true        false       PT876582H
        Millennia   true        false       PT8765820H
        Eras        true        false       PT8765820000000H
        Forever     false       false       PT2562047788015215H30M7.999999999S
         */

        System.out.println(Duration.of(1, ChronoUnit.MINUTES).getSeconds());
        // prints: 60
        System.out.println(Duration.of(1, ChronoUnit.HOURS).getSeconds());
        // prints: 3600
        System.out.println(Duration.of(1, ChronoUnit.DAYS).getSeconds());
        // prints: 86400
    }

    private static void usingTheDurationClass() {
        //The Duration class represents time in terms of hours, minutes, seconds, and so on.
        LocalDateTime exam = LocalDateTime.of(2018, 06, 06, 8, 00);
        LocalDateTime comingMidnight = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.MIDNIGHT);
        LocalDateTime now = LocalDateTime.now();
        Duration between = Duration.between(now, exam);
        System.out.println(between);
        //PT526H11M8.221S

        Duration.of(3600, ChronoUnit.MINUTES);
        // returns "PT60H"

        Duration.ofDays(4);
        // returns "PT96H"

        Duration.ofHours(2);
        // returns "PT2H"

        Duration.ofMinutes(15);
        // returns "PT15M"

        Duration.ofSeconds(30);
        //returns "PT30S"

        Duration.ofMillis(120);
        // returns "PT0.12S"

        Duration.ofNanos(120);
        // returns "PT0.00000012S"

        Duration.parse("P2DT10H30M");
        // returns a Duration object
        // with value PT58H30M
    }

    private static void usingThePeriodClass() {
        //it represents time in terms of years, months, and days
        /*The java.time.Period class is used to measure an amount of time in terms of years, months, and days.
        Assume that you have bought some expensive medicine and want to use it before it expires*/

        LocalDate manufacturingDate = LocalDate.of(2016, Month.JANUARY, 1);
        LocalDate expiryDate = LocalDate.of(2018, Month.JULY, 18);

        Period expiry = Period.between(manufacturingDate, expiryDate);
        System.out.printf("Medicine will expire in: %d years, %d months, and %d days (%s)\n", expiry.getYears(), expiry.getMonths(), expiry.getDays(), expiry);
        // Medicine will expire in: 2 years, 6 months, and 17 days (P2Y6M17D)

        LocalDate christmas = LocalDate.of(2018, 12, 25);
        System.out.println(Period.between(LocalDate.now(), christmas));
        //P7M10D

        Period.ofWeeks(2);
        // returns P14D
        Period.ofDays(15);
        // returns P15D
        Period.ofMonths(6);
        // returns P6M
        Period.ofYears(4);
        // returns P4Y
        Period.parse("P4Y6M15D");
        // returns P4Y6M15D
    }

    private static void usingTheInstantClass() {
        // prints the current timestamp with UTC as time zone
        Instant currTimeStamp = Instant.now();
        System.out.println("Instant timestamp is: " + currTimeStamp);
        // Instant timestamp is: 2018-05-15T12:20:34.216Z

        // prints the number of seconds as Unix timestamp from epoch time
        System.out.println("Number of seconds elapsed: " + currTimeStamp.getEpochSecond());
        // Number of seconds elapsed: 1526386834

        // prints the Unix timestamp in milliseconds
        System.out.println("Number of milliseconds elapsed: " + currTimeStamp.toEpochMilli());
        // Number of milliseconds elapsed: 1526386834216


        //What is the difference between LocalDateTime and Instant?
        LocalDateTime localDateTime = LocalDateTime.now();
        Instant instant = Instant.now();
        System.out.println("LocalDateTime is: " + localDateTime + " \nInstant is: " + instant);
        // LocalDateTime is:    2018-05-15T09:22:45.041
        // Instant is:          2018-05-15T12:22:45.041Z
        /*
        As you can see, the time value printed by LocalDateTime is different from the result of Instant. Why?
        Because we live in the Asia/Kolkata time zone, which is +05:30 hours from Greenwich time. LocalDateTime
        uses the default time zone, but Instant doesn’t.
         */
    }

    private static void usingTheLocalDateTimeClass() {
        LocalDateTime currDateTime = LocalDateTime.now();
        System.out.println("Today's date and current time is: " + currDateTime);
        //Today's date and current time is: 2018-05-15T09:13:13.962

        LocalDateTime christmas = LocalDateTime.of(2015, 12, 25, 0, 0);
        LocalDateTime newYear = LocalDateTime.of(2016, 1, 1, 0, 0);
        System.out.println("New Year 2016 comes after Christmas 2015? " + newYear.isAfter(christmas));
        // New Year 2016 comes after Christmas 2015? true

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Today's date and current time: " + dateTime);
        System.out.println("The date component is: " + dateTime.toLocalDate());
        System.out.println("The time component is: " + dateTime.toLocalTime());
        // Today's date and current time: 2018-05-15T09:15:18.556
        // The date component is: 2018-05-15
        // The time component is: 09:15:18.556
    }

    private static void usingTheLocalTimeClass() {
        LocalTime currTime = LocalTime.now();
        System.out.println("Current time is: " + currTime);
        //Current time is: 09:04:41.493

        System.out.println(LocalTime.of(18, 30));
        // prints: 18:30

        long hours = 6;
        long minutes = 30;
        LocalTime curreTime = LocalTime.now();
        System.out.println("Current time is: " + curreTime);
        // Current time is: 09:06:37.357
        System.out.println("My meeting is at: " + curreTime.plusHours(hours).plusMinutes(minutes));
        // My meeting is at: 15:36:37.357

        System.out.println(LocalTime.now(Clock.systemDefaultZone()));
        //09:08:14.536

        System.out.println(LocalTime.now(ZoneId.of("Asia/Tokyo")));
        //21:09:32.484

        System.out.println(LocalTime.ofSecondOfDay(66620));
        // returns 18:30:20 because
        // 66620 seconds have elapsed

        System.out.println(LocalTime.parse("18:30:05"));
        // 18:30:05
    }

    private static void importantMethodsInTheLocalDateClass() {
        final LocalDate now = LocalDate.now(Clock.systemDefaultZone());
        System.out.println(now);
        // returns current date as 2018-05-15

        System.out.println(LocalDate.now(ZoneId.of("Asia/Kolkata")));
        // returns current date as 2018-05-15

        System.out.println(LocalDate.now(ZoneId.of("Asia/Tokyo")));
        // returns current date as 2018-05-16

        System.out.println(LocalDate.ofYearDay(2018, 61));
        // returns date as 2018-03-02

        System.out.println(LocalDate.parse("2018-06-06"));

        System.out.println(LocalDate.ofEpochDay(1511));
        // returns 1974-02-20;
    }

    private static void usingLocalDateLocalTimeLocalDateTime() {
        LocalDate localDate = LocalDate.now();
        System.out.println("localDate: " + localDate);
        //localDate: 2018-05-11

        LocalTime localTime = LocalTime.now();
        System.out.println("localTime: " + localTime);
        //localTime: 10:28:51.556

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println("localDateTime: " + localDateTime);
        // localDateTime: 2018-05-11T10:28:51.556


        LocalDate localDate2 = LocalDate.of(2016, 12, 01);
        System.out.println("localDate: " + localDate2);
        //localDate: 2016-12-01

        LocalTime localTime55 = LocalTime.of(23, 12, 56, 234);
        System.out.println("localTime: " + localTime55);
        //localTime: 23:12:56.000000234

        LocalDateTime localDateTime1 = LocalDateTime.of(2016, 12, 01, 23, 12, 56, 234);
        System.out.println("localDateTime: " + localDateTime1);
        //localDateTime: 2016-12-01T23:12:56.000000234

        LocalDate localDateNov = LocalDate.of(2016, Month.NOVEMBER, 01);
        System.out.println("localDateNov: " + localDateNov);
        //localDateNov: 2016-11-01


        System.out.println("Today: " + LocalDate.now());
        //Today: 2018-05-11
        LocalDate todayPlus10Days = LocalDate.now().plusDays(10);
        System.out.println("localDatePlus10: " + todayPlus10Days);
        //localDatePlus10: 2018-05-21

        System.out.println("Time Now: " + LocalTime.now());
        //Time Now: 10:28:51.557
        LocalTime nowMinus20Minutes = LocalTime.now().minusMinutes(20);
        System.out.println("nowMinus20Minutes: " + nowMinus20Minutes);
        //nowMinus20Minutes: 10:08:51.557

        System.out.println("Date-Time Now: " + LocalDateTime.now());
        //Date-Time Now: 2018-05-11T10:28:51.557
        LocalDateTime nowPlus2Years = LocalDateTime.now().plusYears(2);
        System.out.println("todayPlus2Years: " + nowPlus2Years);
        //todayPlus2Years: 2020-05-11T10:28:51.557


        System.out.println("Today: " + LocalDate.now());
        //Today: 2018-05-11
        LocalDate dayOfMonth20 = LocalDate.now().withDayOfMonth(20);
        System.out.println("Day of month set as 20: " + dayOfMonth20);
        //Day of month set as 20: 2018-05-20

        System.out.println("Time Now: " + LocalTime.now());
        //Time Now: 10:28:51.557
        LocalTime minute0 = LocalTime.now().withMinute(0);
        System.out.println("Minutes set to 0: " + minute0);
        //Minutes set to 0: 10:00:51.557

        System.out.println("Date-Time Now: " + LocalDateTime.now());
        //Date-Time Now: 2018-05-11T10:28:51.557
        LocalDateTime month10 = LocalDateTime.now().withMonth(10);
        System.out.println("Month set to 10: " + month10);
        //Month set to 10: 2018-10-11T10:28:51.557


        System.out.println("Today: " + LocalDate.now());
        //Today: 2018-05-11
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        System.out.println("Day of month is: " + dayOfMonth);
        //Day of month is: 11

        System.out.println("Time Now: " + LocalTime.now());
        //Time Now: 10:28:51.557
        int minute = LocalTime.now().getMinute();
        System.out.println("Minutes value is: " + minute);
        //Minutes value is: 28

        System.out.println("Date-Time Now: " + LocalDateTime.now());
        //Date-Time Now: 2018-05-11T10:28:51.557
        Month month = LocalDateTime.now().getMonth();
        System.out.println("Month value is: " + month.getValue());
        //Month value is: 5

        long visaValidityDays = 180L;
        LocalDate currDate = LocalDate.now();
        System.out.println("My Visa expires on: " + currDate.plusDays(visaValidityDays));
        //My Visa expires on: 2016-04-23
    }

}
