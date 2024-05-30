package selmok.datagenerator.dataproviders;

import selmok.datagenerator.customizers.SingletonLocaleContext;
import selmok.datagenerator.services.RandomService;

/**
 * Provides methods for generating credentials (e.g. emails, passwords, etc),
 * extending the functionality of the BaseProvider class.
 */
public class CredentialsProvider extends BaseProvider{

    public CredentialsProvider(SingletonLocaleContext locale, RandomService random) {
        super(locale, random);
    }

    /**
     * Creates a new instance of EmailProvider with the current locale and random service.
     * The instance can be further used to generate the email address with different level
     * of customization.
     *
     * @return A new EmailProvider instance.
     */
    public EmailProvider email(){
        return new EmailProvider(getLocale().getLocale().getCountry(), getRandom());
    }

    /**
     * Generates a random password with the specified parameters.
     *
     * @param length The length of the password.
     * @param upperLetters If true, include upper case letters.
     * @param numbers If true, include numbers.
     * @param specialChars If true, include special characters.
     * @return The generated password.
     */
    public String password(int length, boolean upperLetters, boolean numbers, boolean specialChars){
        return getRandom().randomString(length, upperLetters, numbers, specialChars);
    }








}
