package br.com.dvlima.ocpjp8.topic12._localization._01_read_and_set_locale;

import java.util.Arrays;
import java.util.Locale;

public class TheLocaleClass {

    public static void main(String[] args) {
//        availableLocales();
//        availableLocalesEnglish();
//        localeDetails();
        createLocaleObject();
    }

    private static void createLocaleObject(){
        //Use the constructor of the Locale Class
        Locale locale1 = new Locale("it","","");

        //Use the forLanguageTag
        Locale locale2 = Locale.forLanguageTag("lpt");
        System.out.println("locale2 = " + locale2.getCountry());

        //Option 3: Build a Locale object by instantiating Locale.Builder and then call setLanguageTag()
        Locale locale3 = new Locale.Builder().setLanguageTag("it").build();

        //Option 4: Use the predefined static final constants for locales in the Locale class:
        Locale locale4 = Locale.ITALIAN;
    }

    private static void localeDetails() {
        Locale.setDefault(Locale.ITALY);

        Locale.setDefault(new Locale("fr", "CA", ""));

        Locale defaultLocale = Locale.getDefault();
        System.out.printf("The default locale is %s %n", defaultLocale);
        System.out.printf("The default language code is %s and the name is %s %n",
                defaultLocale.getLanguage(), defaultLocale.getDisplayLanguage());
        System.out.printf("The default country code is %s and the name is %s %n",
                defaultLocale.getCountry(), defaultLocale.getDisplayCountry());
        System.out.printf("The default variant code is %s and the name is %s %n",
                defaultLocale.getVariant(), defaultLocale.getDisplayVariant());
/*
The default locale is fr_CA
The default language code is fr and the name is français
The default country code is CA and the name is Canada
The default variant code is  and the name is
 */
    }

    private static void availableLocalesEnglish() {
        Arrays.stream(Locale.getAvailableLocales())
                .filter(locale -> locale.getLanguage().equals("en"))
                .forEach(locale ->
                        System.out.printf("Locale code: %s and it stands for %s %n",
                                locale, locale.getDisplayName()));
    }

    private static void availableLocales() {
        System.out.println("The default locale is: " + Locale.getDefault());
        //The default locale is: pt_BR

        Locale[] locales = Locale.getAvailableLocales();
        System.out.printf("No. of other available locales is: %d, and they are: %n", locales.length);
        Arrays.stream(locales)
                .forEach(locale -> System.out.printf("Locale code: %s and it stands for %s %n", locale, locale.getDisplayName()));

        //For the locale code of “th_TH_TH_#u-nu-thai”
//      The language code is "th" (Thai) and it is always written in lowercase
//      The country code is "TH" (Thailand) and it is always written in uppercase
//      The variant name is "TH"; here it repeats the country code, but it could be any string

/*
To give another example, consider the locale code "sr_RS_#Latn",
The language code is "sr" (Serbian)
The country code is "RS" (Serbia)
The variant name is empty here
The script name is "Latn" (Latin) which is a four-letter string with the first letter in uppercase and the rest in lowercase
The extension is empty here
*/
    }

}
