package selmok.datagenerator.dataproviders;

import selmok.datagenerator.customizers.SingletonLocaleContext;
import selmok.datagenerator.services.RandomService;

/**
 * Provides methods for generating email addresses, extending the functionality of the BaseProvider class.
 * This class encapsulates the logic for generating email addresses with various parameters such as length,
 * inclusion of upper case letters, numbers, and special characters in the local part, and custom domain names.
 * It also provides methods for generating email addresses with a person's full name as the local part.
 */
public class EmailProvider extends BaseProvider{

    private NameProvider name;

    /**
     * Using the BaseProvider.class constructor, initializes the instance of EmailProvider
     * with SingletonLocaleContext to define country and language for which data will be generated
     * and RandomService settings to use (e.g. random seed).
     * Apart from that, initializes the NameProvider instance to generate emails with full names as
     * local part.
     */
    public EmailProvider(SingletonLocaleContext locale, RandomService random) {

        super(locale, random);
        this.name = new NameProvider(getLocale(), getRandom());
    }

    /**
     * Generates a random email local part with the specified parameters.
     *
     * @param upperLetters If true, include upper case letters in the generated local part.
     * @param numbers      If true, include numbers in the generated local part.
     * @return The generated email local part.
     * @implNote This method uses a default length of 8 characters for generating the local part.
     */
    public String getEmailLocalPart(boolean upperLetters, boolean numbers){
        return getRandom().randomString(8, upperLetters, numbers, false);
    }

    /**
     * Generates a random email local part with the default length and non-included by default
     * uppercase letters and numbers.
     *
     * @return The generated email local part.
     * @implNote This method uses a default length of 8 characters for generating the local part.
     */
    public String getEmailLocalPart(){
        return getEmailLocalPart(false, false);
    }

    /**
     * Generates a local part for an email address using randomly generated full name
     * with a specified separator for name and surname (e.g. if separtor is set to '.', then
     * email local part will be in format Name.Surname)
     *
     * @param nameSeparator The separator to use between parts of the full name.
     * @return The generated local part based on the full name with the specified separator.
     */
    public String getEmailLocalePartAsFullName(String nameSeparator){
        String fullName = name.fullName();
        return fullName.replace(" ", nameSeparator);
    }

    /**
     * Generates a local part for an email address using the full name with an underscore as separator.
     * (the email locale part will be in format Name_Surname, if you want another separator, please
     * provide it as a parameter, another message, that includes customization, will be used).
     *
     * @return The generated local part based on the full name with an underscore separator.
     */
    public String getEmailLocalePartAsFullName(){
        String fullName = name.fullName();
        return fullName.replace(" ", "_");
    }

    /**
     * Generates a random domain name for an email address. By default does not include
     * the uppercase letters and numbers.
     *
     * @return The generated domain name.
     * This method generates a random domain name with the format "@randomString.com".
     *           The randomString consists of 4 characters and does not include uppercase letters, numbers, or special characters.
     */
    public String getEmailDomainName(){
        return "@" + getRandom()
                .randomString(4, false, false, false) + ".com";
    }

    /**
     * Generates a random email address with the specified parameters for the local part.
     *
     * @param upperLettersInLocalPart If true, include upper case letters in the local part.
     * @param numbersInLocalPart If true, include numbers in the local part.
     * @return The generated email address with a domain name.
     */
    public String getEmail(boolean upperLettersInLocalPart, boolean numbersInLocalPart){
        return getEmailLocalPart(upperLettersInLocalPart, numbersInLocalPart)
                + getEmailDomainName();
    }


    /**
     * Generates a random email address with the specified parameters for the local part and a custom domain.
     *
     * @param upperLettersInLocalPart If true, include upper case letters in the local part.
     * @param numbersInLocalPart If true, include numbers in the local part.
     * @param domain The custom domain name for the email address.
     * @return The generated email address with the specified domain.
     */
    public String getEmail(boolean upperLettersInLocalPart, boolean numbersInLocalPart,
                           String domain){
        return getEmailLocalPart(upperLettersInLocalPart, numbersInLocalPart) + domain;
    }

    /**
     * Generates a random email address with default parameters. Does not include
     * uppercase letters and numbers both in local part and in domain name.
     *
     * @return The generated email address with a domain name.
     */
    public String getEmail(){
        return getEmailLocalPart() + getEmailDomainName();
    }

    /**
     * Generates a random email address with a custom domain. Does not include
     * uppercase letters and numbers in local part.
     *
     * @param domain The custom domain name for the email address.
     * @return The generated email address with the specified domain.
     */
    public String getEmail(String domain){
        return getEmailLocalPart() + domain;
    }

    /**
     * Generates a random email address with a person's full name in the local part and adds the custom domain name.
     *
     * @param domain The custom domain name for the email address.
     * @return The generated email address with a person's full name and the specified domain name.
     */
    public String getEmailWPersonName(String domain){
        return getEmailLocalePartAsFullName() + domain;
    }

    /**
     * Generates a random email address with a person's full name in the local part, using the specified separator,
     * and adds the custom domain name.
     *
     * @param separator The separator to use between parts of the person's full name.
     * @param domain The custom domain name for the email address.
     * @return The generated email address with a person's full name and the specified domain name.
     */
    public String getEmailWPersonName(String separator, String domain){
        return getEmailLocalePartAsFullName(separator) + domain;
    }


    /**
     * Generates a random email address with a person's full name in the local part
     * and a randomly generated domain name.
     *
     * @return The generated email address with a person's full name and a default domain name.
     */
    public String getEmailWPersonName(){
        return getEmailLocalePartAsFullName() + getEmailDomainName();
    }

    /**
     * Generates a random email address with a person's full name in the local part, including the
     * separator for surname and name,
     * and a randomly generated domain name.
     *
     * @return The generated email address with a person's full name and a default domain name.
     */
    public String getEmailWPersonNameAndSeparator(String separator){
        return getEmailLocalePartAsFullName(separator) + getEmailDomainName();
    }


}
