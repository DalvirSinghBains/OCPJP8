package br.com.dvlima.ocpjp8.topic12._localization._03_build_resource_bundle;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class LoadingAResourceBundle {
    /*
        on the oCpJp 8 exam, you’re not expected to memorize language codes or country codes that are used for naming
        resource bundles. however, you are expected to remember the naming convention and recognize the constituents of
        a fully qualified resource bundle name.
     */

    //packagequalifier.bundlename + "_" + language + "_" + country + "_" + (variant + "_#" | "#") + script + "-" + extensions

/*
 packagequalifier: The name of the package (or the subpackages) in which the resource bundle is provided.

 bundlename: The name of the resource bundle that you’ll use in the program to refer and load it.

 language: A two-letter abbreviation typically given in lowercase for the locale’s language (in rare cases, it could be three letters as well).

 country: A two-letter abbreviation typically given in uppercase for the locale’s country (in rare cases, it could be three letters as well).

 variant: An arbitrary list of variants (in lowercase or uppercase) to differentiate locales when you need more
 than one locale for a language and country combination.
*/

/*
ResourceBundle.properties               //-- Global bundle
ResourceBundle_ar.properties            //-- Arabic language bundle
ResourceBundle_en.properties            //-- English bundle (assuming en_US is the default locale)
ResourceBundle_it.properties            //-- Italian language bundle
ResourceBundle_it_IT_Rome.properties    //-- Italian (Italy, Rome) bundle
*/

}

// Extend ResourceBundle.Control and override getCandidateLocales method
// to get the list of candidate locales that Java searches for
class TalkativeResourceBundleControl extends ResourceBundle.Control {
    // override the default getCandidateLocales method to print
    // the candidate locales first
    public List<Locale> getCandidateLocales(String baseName, Locale locale) {
        List<Locale> candidateLocales = super.getCandidateLocales(baseName, locale);
        System.out.printf("Candidate locales for base bundle name %s and locale %s %n",
                baseName, locale.getDisplayName());
        candidateLocales.forEach(System.out::println);
        return candidateLocales;
    }
}

class CandidateLocales {
    public static void loadResourceBundle(String resourceBundleName, Locale locale) {
        // Pass an instance of TalkativeResourceBundleControl
        // to print candidate locales
        ResourceBundle resourceBundle = ResourceBundle.getBundle(resourceBundleName,
                locale, new TalkativeResourceBundleControl());
        String rbLocaleName = resourceBundle.getLocale().toString();
        // if the resource bundle locale name is empty,
        // it means default property file
        if (rbLocaleName.equals("")) {
            System.out.println("Loaded the default property file with name: "
                    + resourceBundleName);
        } else {
            System.out.println("Loaded the resource bundle for the locale: "
                    + resourceBundleName + "." + rbLocaleName);
        }
    }

    public static void main(String[] args) {
        // trace how ResourceBundle_it_IT_Rome.properties is resolved
        loadResourceBundle("ResourceBundle", new Locale("it", "IT", "Rome"));
/*
Candidate locales for base bundle name ResourceBundle and locale Italian (Italy, Rome)
it_IT_Rome
it_IT
it
Loaded the resource bundle for the locale: ResourceBundle.it_IT_Rome
*/
    }
}