package br.com.dvlima.ocpjp8.topic07._use_java_se_date_time_api._02_timezones_format;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class TimeZonesAndDaylightSavings {

    public static void main(String[] args) {
        //usingTheZoneIdClass();
        //usingTheZoneOffsetClass();
        //usingTheZonedDateTimeClass();
        //dealingWithDaylightSavings();
        formattingDatesAndTimes();
    }

    private static void formattingDatesAndTimes() {
//        ISO_DATE (2015-11-05)
//        ISO_TIME (11:25:47.624)
//        RFC_1123_DATE_TIME (Thu, 5 Nov 2015 11:27:22 +0530)
//        ISO_ZONED_DATE_TIME (2015-11-05T11:30:33.49+05:30[Asia/Kolkata])

        LocalTime wakeupTime = LocalTime.of(07, 20, 00);
        System.out.println("Wake up time: " + DateTimeFormatter.ISO_TIME.format(wakeupTime));
        //Wake up time: 07:20:00

        DateTimeFormatter customFormat = DateTimeFormatter.ofPattern("dd MMM yyyy");
        System.out.println(customFormat.format(LocalDate.of(2016, Month.JANUARY, 01)));
        //01 jan 2016
        /*
            Uppercase and lowercase letters can have similar or different meanings when used in format strings for dates
            and times. read the Javadoc for these patterns carefully before trying to use these letters. For example,
            in dd-mm-yy, MM refers to month; however, in dd-mm-yy, mm refers to minutes !

            G (era: BC, AD)
            y (year of era: 2015, 15)
            Y (week-based year: 2015, 15)
            M (month: 11, Nov, November)
            w (week in year: 13)
            W (week in month: 2)
            E (day name in week: Sun, Sunday)
            D (day of year: 256)
            d (day of month: 13)
        */

        // patterns from simple to complex ones
        String[] dateTimeFormats = {
                "dd-MM-yyyy", /* d is day (in month), M is month, y is year */
                "d '('E')' MMM, YYYY", /*E is name of the day (in week), Y is year*/
                "w'th week of' YYYY", /* w is the week of the year */
                "EEEE, dd'th' MMMM, YYYY" /*E is day name in the week */
        };
        LocalDateTime now = LocalDateTime.now();
        for (String dateTimeFormat : dateTimeFormats) {
            System.out.printf("Pattern \"%s\" is %s %n", dateTimeFormat,
                    DateTimeFormatter.ofPattern(dateTimeFormat).format(now));
        }

//        Pattern "dd-MM-yyyy" is 16-05-2018
//        Pattern "d '('E')' MMM, YYYY" is 16 (Qua) mai, 2018
//        Pattern "w'th week of' YYYY" is 20th week of 2018
//        Pattern "EEEE, dd'th' MMMM, YYYY" is Quarta-feira, 16th Maio, 2018

        /*  Time Patterns

            a (marker for the text a.m./p.m. marker) H (hour: value range 0–23)
            k (hour: value range 1–24)
            K (hour in a.m./p.m.: value range 0–11) h (hour in a.m./p.m.: value range 1–12) m (minute
            s (second)
            S (fraction of a second)
            z (time zone: general time-zone format)
         */

        String[] timeFormats = {
                "h:mm",             /* h is hour in am/pm (1-12), m is minute */
                "hh 'o''clock'",    /* '' is the escape sequence to print a single quote */
                "H:mm a",           /* H is hour in day (0-23), a is am/pm*/
                "hh:mm:ss:SS",      /* s is seconds, S is milliseconds */
                "K:mm:ss a"         /* K is hour in am/pm(0-11) */
        };

        for (String timeFormat : timeFormats) {
            System.out.printf("Time in pattern \"%s\" is %s %n", timeFormat,
                    DateTimeFormatter.ofPattern(timeFormat).format(now));
        }
//        Time in pattern "h:mm" is 3:02
//        Time in pattern "hh 'o''clock'" is 03 o'clock
//        Time in pattern "H:mm a" is 3:02 AM
//        Time in pattern "hh:mm:ss:SS" is 03:02:39:77
//        Time in pattern "K:mm:ss a" is 3:02:39 AM

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy hh.mm a");
        // Leaving on 1st Jan 2016, 6:00am from "Singapore"

        ZonedDateTime departure = ZonedDateTime.of(
                LocalDateTime.of(2016, Month.JANUARY, 1, 6, 0), ZoneId.of("Asia/Singapore"));
        System.out.println("Departure: " + dateTimeFormatter.format(departure));
        // Arrival on the same day in 10 hours in "Auckland"

        ZonedDateTime arrival = departure.withZoneSameInstant(ZoneId.of("Pacific/Auckland")).plusHours(10);

        System.out.println("Arrival: " + dateTimeFormatter.format(arrival));
//        Departure: 01 jan 2016 06.00 AM
//        Arrival: 01 jan 2016 09.00 PM
    }

    private static void dealingWithDaylightSavings() {
    /*
        The amount of daylight does not remain the same throughout the year, because the seasons change. There is more
        daylight in summer, for example. With daylight savings time (DST), the clock is set one hour earlier or later
        to make the best use of the daylight. As the saying goes, “Spring forward, fall back”—the clock is typically set
        one hour earlier when Spring begins and one hour later at the start of Fall:
     */

        ZoneId saoPauloZone = ZoneId.of("America/Sao_Paulo");
        Duration saoPauloDST = saoPauloZone.getRules().getDaylightSavings(Instant.now());
        System.out.printf("%s zone DST is: %d hours %n", saoPauloZone, saoPauloDST.toHours());
        //America/Sao_Paulo zone DST is: 0 hours

        ZoneId buenosAiresZone = ZoneId.of("America/Argentina/Buenos_Aires");
        Duration buenosAiresDST = buenosAiresZone.getRules().getDaylightSavings(Instant.now());
        System.out.printf("%s zone DST is: %d hours", buenosAiresZone, buenosAiresDST.toHours());
        //America/Argentina/Buenos_Aires zone DST is: 0 hours
    }

    private static void usingTheZonedDateTimeClass() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        ZoneId myZone = ZoneId.systemDefault();
        ZonedDateTime zonedDateTime = ZonedDateTime.of(currentDate, currentTime, myZone);
        System.out.println(zonedDateTime);
        //2018-05-16T02:33:00.010-03:00[America/Sao_Paulo]

        LocalDateTime dateTime = LocalDateTime.now();
        ZoneId zone = ZoneId.systemDefault();
        ZonedDateTime zoneDateTime = dateTime.atZone(zone);
        System.out.println(zoneDateTime);
        //2018-05-16T02:35:11.579-03:00[America/Sao_Paulo]

        ZoneId myZone2 = ZoneId.of("America/Sao_Paulo");
        LocalDateTime dateTime2 = LocalDateTime.now();
        ZonedDateTime zonedDateTime2 = dateTime2.atZone(myZone2);
        ZoneOffset zoneOffset = zonedDateTime2.getOffset();
        System.out.println(zoneOffset);
        //-03:00

        //Time Difference

        ZoneId singaporeZone = ZoneId.of("Asia/Singapore");
        ZonedDateTime dateTimeInSingapore = ZonedDateTime.of(
                LocalDateTime.of(2016, Month.JANUARY, 1, 6, 0), singaporeZone);

        ZoneId aucklandZone = ZoneId.of("Pacific/Auckland");
        ZonedDateTime sameDateTimeInAuckland =
                dateTimeInSingapore.withZoneSameInstant(aucklandZone);

        Duration timeDifference = Duration.between(
                dateTimeInSingapore.toLocalTime(),
                sameDateTimeInAuckland.toLocalTime());

        System.out.printf("Time difference between %s and %s zones is %d hours",
                singaporeZone, aucklandZone, timeDifference.toHours());
        //Time difference between Asia/Singapore and Pacific/Auckland zones is 5 hours
    }

    private static void usingTheZoneOffsetClass() {
    /*
        ZoneId identifies a time zone, such as Asia/Kolkata. Another class, ZoneOffset, represents the time-zone offset
        from UTC/Greenwich. For example, zone ID “Asia/Kolkata” has a zone offset of +05:30 (plus 5 hours and 30 minutes)
        from UTC/Greenwich. The ZoneOffset class extends the ZoneId class. We discuss an example that uses ZoneOffset
        in the next section.
     */
    }

    private static void usingTheZoneIdClass() {
        System.out.println("My zone id is: " + ZoneId.systemDefault());
        //My zone id is: America/Sao_Paulo

        Set<String> zones = ZoneId.getAvailableZoneIds();
        System.out.println("Number of available time zones is: " + zones.size());
        zones.forEach(System.out::println);
        // Number of available time zones is: 589
        // Asia/Aden
        // America/Cuiaba
        // rest of the output elided...

        ZoneId zoneAsiaKolkataZoneId = ZoneId.of("Asia/Kolkata");

    }

}
