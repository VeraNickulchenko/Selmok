package selmok.datagenerator.customizers;

import selmok.datagenerator.enums.Countries;
import selmok.datagenerator.enums.Languages;
import selmok.datagenerator.utils.message_customizers.ExceptionsHandlerUtils;

import java.util.Objects;

/**
 * This class represents the LocaleContext object, that
 * contains information about the country and the language,
 * based on which the fake data will be generated.
 * This means, that, based on this object,
 * names, streets, cities, etc. specific for the provided country
 * will be returned as result, translated into corresponding language
 *
 */
public class LocaleContext {
    private Countries country;
    private Languages language;

    private static final Countries  DEFAULT_COUNTRY = Countries.USA;

    private static final Languages DEFAULT_LANGUAGE = Languages.ENGLISH;

    /**
     * Constructor,
     * that initializes LocaleContext object with default language and country (english and US)
     */
    public LocaleContext() {
        this.country = DEFAULT_COUNTRY;
        this.language = DEFAULT_LANGUAGE;
    }

    /** Constructor, that initializes LocaleContext object with custom country (Countries.class instance)
     * and custom language (Languages.class instance)
     */
    public LocaleContext(Countries country, Languages language){
        ExceptionsHandlerUtils.checkObjectsForNullAndThrowException(country, language);
        this.country = country;
        this.language = language;
    }

    /**
     * Constructor, that initializes LocaleContext object with custom country (Countries.class instance)
     * and matches corresponding native language (Languages.class instance) for this country.
     * For example: if country is France, then language will be french
     */
    public LocaleContext(Countries country) {
        ExceptionsHandlerUtils.checkObjectsForNullAndThrowException(country);
        this.country = country;
        this.language = CountryLangMatcher.matchLang(country);
    }

    /**
     * Constructor, that initializes LocaleContext object with custom language (Languages.class instance)
     * and matches corresponding native country (Countries.class instance) for this language.
     * For example, if language is german, then country is Germany.
     */
    public LocaleContext(Languages language) {
        ExceptionsHandlerUtils.checkObjectsForNullAndThrowException(language);
        this.language = language;
        this.country = CountryLangMatcher.matchCountry(language);
    }

    /**
     * Returns String representation of
     * country currently set
     *
     * @return String
     */
    public String getCountryCode() {return country.getCountryCode();}

    /**
     * Returns String representation of
     * the language currently set
     *
     * @return String
     */
    public String getLanguageCode() {
        return language.getLanguageCode();
    }

    public Countries getCountry() {
        return country;
    }

    /**
     * Returns String representation of the object,
     * that holds its content, specifically
     * the country and language set
     *
     * @return String
     */
    @Override
    public String toString() {
        return getCountryCode().toLowerCase() + "_" + getLanguageCode().toLowerCase();
    }

    /**
     * Overrides standard method of the Object.class
     * to compare an objects.
     *
     * It compares two LocaleContext by its content,
     * so that if the country and the name of the first object
     * equals to the country and name of the second object,
     * objects considered as equal and true will be returned.
     *
     * If object provided is not the LocaleContext object or is null,
     * then false is returned.
     *
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocaleContext that = (LocaleContext) o;
        return getCountryCode() == that.getCountryCode() && getLanguageCode() == that.getLanguageCode();
    }

    /**
     * Overrides standard Object.class method.
     * Uses the object content (String representation of both country and language)
     * to generate the hashcode
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(getCountryCode(), getLanguageCode());
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public void setLanguage(Languages language) {
        this.language = language;
    }

    /**
     * This is the nested class, which main responsibility is to match
     * the country provided to its native language and vise versa.
     * It is required to handle the constructors with only Language
     * or only Country provided, to match and initilize the second attribute correctly,
     * based on it.
     */
    static class CountryLangMatcher{

        /**
         * Matches a given country to its corresponding language.
         *
         * @param country The country for which the language is to be determined.
         * @return The language associated with the provided country.
         * @throws IllegalArgumentException If the country does not have support for its native language added yet.
         */
        public static Languages matchLang(Countries country){

            switch (country){
                case UKRAINE:
                    return Languages.UKRAINIAN;


                case USA:
                    return Languages.ENGLISH;


            }

            throw new IllegalArgumentException("Country: " + country.getCountryCode() + " has " +
                    "no support for its native language added yet");

        }

        /**
         * Matches a given language to its corresponding country.
         *
         * @param lang The language for which the country is to be determined.
         * @return The country associated with the provided language.
         * @throws IllegalArgumentException If the language does not have support for its native country added yet.
         */
        public static Countries matchCountry(Languages lang){

            switch (lang){
                case UKRAINIAN:
                    return Countries.UKRAINE;


                case ENGLISH:
                    return Countries.USA;

            }

            throw new IllegalArgumentException("Language: " + lang.getLanguageCode() + " has " +
                    "no support for its native country added yet");
        }
    }



}
