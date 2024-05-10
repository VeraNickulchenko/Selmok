package selmok.datagenerator.enums;

/**
 * Enum of countries, supported by the faker.
 * Any of the country, defined in the enum, can be
 * used to initialize the LocaleContext and tell the faker
 * data specific to which country you need it to generate.
 *
 * @author Vira
 */
public enum Countries {
    USA("us"), UKRAINE("ua");

    /**
     * Provides the String representation of country, which is defined as
     * country code according to ISO 3166
     *
     */
    private String countryCode;

    /**
     * Initializes each Countries enum entry with its corresponding
     * country code, defined according to ISO 3166
     */
    Countries(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * Returns the country code of specific country (Countries enum entry).
     * The country codes are defined according to ISO 3166.
     */
    public String getCountryCode(){
        return this.countryCode;
    }
}
