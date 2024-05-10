package selmok.datagenerator.enums;

/**
 * Enum of languages, supported by the faker.
 * Any of the language, defined in the enum, can be
 * used to initialize the LocaleContext and tell the faker
 * data translated into which language you need it to generate.
 *
 * @author Vira
 */
public enum Languages {
    ENGLISH("en"), UKRAINIAN("ukr");

    /**
     * Provides a string representation of specific language in format
     * of language code, defined according to ISO 639.
     */
    private String languageCode;

    /**
     * Initializes each Languages enum entry with its corresponding
     * language code, defined according to ISO 639
     */
    Languages(String languageCode) {
        this.languageCode = languageCode;
    }

    /**
     * Returns the language code of specific language (Languages enum entry).
     * The country codes are defined according to ISO 639.
     */
    public String getLanguageCode() {
        return languageCode;
    }
}
