package br.com.dvlima.ocpjp8.topic12._localization._02_create_read_properties_file;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class UsingPropertyResourceBundle {
    public static void main(String args[]) {
//        usingResourcesBundle();

    }

    public void printMovieDetails(ResourceBundle resBundle) {
        String movieName = resBundle.getString("MovieName");
        Long revenue = (Long)(resBundle.getObject("GrossRevenue"));
        Integer year = (Integer) resBundle.getObject("Year");
        System.out.println("Movie " + movieName + "(" + year + ")" + " grossed " + revenue );
    }

    private static void usingListResourceBundle() {
        //class ResBundle

        UsingPropertyResourceBundle localizedHits = new UsingPropertyResourceBundle();
        // print the largest box-office hit movie for default (US) locale
        Locale locale = Locale.getDefault();
        localizedHits.printMovieDetails(ResourceBundle.getBundle("ResBundle", locale));
        // print the largest box-office hit movie for Italian locale
        locale = new Locale("it", "IT", "");
        localizedHits.printMovieDetails(ResourceBundle.getBundle("ResBundle", locale));

//        It prints the following:
//        Movie Avatar (2009) grossed 2782275172
//        Movie Che Bella Giornata (2011) grossed 43000000
    }

    private static void usingResourcesBundle() {
        Locale currentLocale = Locale.getDefault();
        ResourceBundle resBundle = ResourceBundle.getBundle("ResourceBundle", currentLocale);

        System.out.printf(resBundle.getString("Greeting"));
        //java UsingPropertyResourceBundle
        //Ola

        //java -Duser.language=it -Duser.region=IT UsingPropertyResourceBundle
        //Ciao

        //if you forget to create property files or they are not in the path, you will get a MissingResourceException.
    }
}

// default US English version
class ResBundle extends ListResourceBundle {
    static final Object[][] contents = {
            {"MovieName", "Avatar"},
            {"GrossRevenue", (Long) 2782275172L}, // in US dollars
            {"Year", (Integer) 2009}
    };

    public Object[][] getContents() {
        return contents;
    }
}

// Italian version
class ResBundle_it_IT extends ListResourceBundle {
    public Object[][] getContents() {
        return contents;
    }
    static final Object[][] contents = {
            { "MovieName", "Che Bella Giornata" },
            { "GrossRevenue", (Long) 43000000L }, // in euros
            { "Year", (Integer)2011 }
    }; }
