package br.com.dvlima.ocpjp8.apress.topic12._localization;

/**
 * Localization
 * <p>
 * - Read and set the locale by using the Locale object
 * - Create and read a Properties file
 * - Build a resource bundle for each locale and load a resource bundle in an application
 */
public class Localization {

/*
Read and set the locale by using the Locale object

 A locale represents a language, culture, or country; the Locale class in Java provides an abstraction for this concept.

 Each locale can have three entries: the language, country, and variant. You can use standard codes available for
 language and country to form locale tags. There are no standard tags for variants;
 you can provide variant strings based on your need.

 The getter methods in the Locale class—such as getLanguage(), getCountry(), and getVariant()—return codes;
 whereas the similar methods of getDisplayCountry(), getDisplayLanguage(), and getDisplayVariant() return names.

 The getDefault() method in Locale returns the default locale set in the JVM. You can change this default
 locale to another locale by using the setDefault() method.

 There are many ways to create or get a Locale object corresponding to a locale:

 Use the constructor of the Locale class.

 Use the forLanguageTag(String languageTag) method in the Locale class.

 Build a Locale object by instantiating Locale.Builder and then call setLanguageTag() from that object.

 Use the predefined static final constants for locales in the Locale class.
*/

/*
Create and read a Properties file

 A resource bundle is a set of classes or property files that help define a set of keys and map those keys to
 locale-specific values.

 The class ResourceBundle has two derived classes: PropertyResourceBundle and ListResourceBundle. You can use
 ResourceBundle.getBundle() to get the bundle for a given locale.

 The PropertyResourceBundle class provides support for multiple locales in the form of property files. For each locale,
  you specify the keys and values in a property file for that locale. You can use only Strings as keys and values.

 To add support for a new locale, you can extend the ListResourceBundle class. In this derived class, you have to
 override the Object [][] getContents() method. The returned array must have the list of keys and values.
 The keys must be Strings, and values can be any objects.

 When passing the key string to the getObject() method to fetch the matching value in the resource bundle, make sure
 that the passed keys and the key in the resource bundle exactly match (the keyname is case sensitive).
 If they don’t match, you'll get a MissingResourceException.

 The naming convention for a fully qualified resource bundle name is
 packagequalifier.bundlename + "_" + language + "_" + country + "_" + (variant + "_#" | "#") + script + "-" + extensions.
 */

/*
Build a resource bundle for each locale and load a resource bundle in an application

 The process of finding a matching resource bundle is same for classes extended from ListResourceBundles as for property
  files defined for PropertyResourceBundles.

 Here is the search sequence to look for a matching resource bundle. Search starts from Step 1. If at any step the search
 finds a match, the resource bundle is loaded. Otherwise, the search proceeds to the next step.

 Step 1: The search starts by looking for an exact match for the resource bundle with the full name.

 Step 2: The last component (the part separated by _) is dropped and the search is repeated with the resulting shorter
 name. This process is repeated till the last locale modifier is left.

 Step 3: The search is continued using the full name of the bundle for the default locale.

 Step 4: Search for the resource bundle with just the name of the bundle.

 Step 5: The search fails, throwing a MissingBundleException.

 The getBundle() method takes a ResourceBundle.Control object as an additional parameter. By extending this
 ResourceBundle.Control class and passing that object, you can control or customize the resource
 bundle searching and loading process.
 */
}
