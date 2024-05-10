package selmok.datagenerator;

import selmok.datagenerator.customizers.LocaleContext;
import selmok.datagenerator.customizers.SingletonLocaleContext;
import selmok.datagenerator.dataproviders.CredentialsProvider;
import selmok.datagenerator.dataproviders.EmailProvider;
import selmok.datagenerator.dataproviders.NameProvider;
import selmok.datagenerator.services.RandomService;

import java.util.Random;

/**
 * Root class, which is responsible for access to every fake data providers (e.g. NameProvider,
 * EmailProvider, CredentialsProvider, etc) and their initialization. It creates objects of the provider
 * classes and returns reference to them to access their methods easily, with no need to initialize
 * each and every provider separately in code, but at the same time does not limit to do it.
 */
public class DataGenerator {
    private SingletonLocaleContext locale;
    private RandomService randomService;

    /**
     * Creates the DataGenerator object, initialized with LocaleContext object to provide
     * country and language context for the fake data.
     * This means that data generated will be specific to provided country (e.g. for USA as country
     * only american names and addresses will be generated) and translated into specific language
     * (even if USA is defined as country, if language is ukrainian, american names and addresses will
     * be translated).
     * Apart from that, it initializes the RandomService with specific Random object, used to control
     * the data generation (provide random seed, etc).
     */
    public DataGenerator(LocaleContext localeContext, Random random) {
        this.locale = SingletonLocaleContext.get(localeContext);
        this.randomService = new RandomService(random);

    }

    /**
     * Creates the DataGenerator object, initialized with LocaleContext object to provide
     * country and language context for the fake data.
     * This means that data generated will be specific to provided country (e.g. for USA as country
     * only american names and addresses will be generated) and translated into specific language
     * (even if USA is defined as country, if language is ukrainian, american names and addresses will
     * be translated).
     * The instance of Random.class, used to control and generate the data, is the default one
     * (means that default constructor of Random class is used for initialization).
     */
    public DataGenerator(LocaleContext locale){
        this.locale = SingletonLocaleContext.get(locale);
        this.randomService = new RandomService();
    }

    /**
     * Creates the DataGenerator object, initialized with default LocaleContext object
     * (where country defined is USA and language defined is english)
     * to provide country and language context for the fake data.
     * This means that data generated will be specific to provided country (e.g. for USA as country
     * only american names and addresses will be generated) and translated into specific language
     * (even if USA is defined as country, if language is ukrainian, american names and addresses will
     * be translated).
     * Apart from that, it initializes the RandomService with specific Random object, used to control
     * the data generation (provide random seed, etc).
     */
    public DataGenerator(Random random){
        this.locale = SingletonLocaleContext.get(new LocaleContext());
        this.randomService = new RandomService(random);
    }

    public DataGenerator(){
        this.locale = SingletonLocaleContext.get(new LocaleContext());
        this.randomService = new RandomService();
    }

    /**
     * Returns the instance of NameProvider to enable names generation.
     *  Refer to NameProvider class for more information about possible methods.
     */
    public NameProvider name(){
        return new NameProvider(locale, randomService);
    }

    /**
     * Returns the instance of CredentialsProvider to enable credentials generation (emails, passwords, etc).
     *  Refer to CredentialsProvider class for more information about possible methods.
     */
    public CredentialsProvider credentials(){
        return new CredentialsProvider(locale, randomService);
    }

    /**
     * Returns the instance of EmailProvider to enable email generation (local parts, domains,
     * full emails). Refer to EmailProvider class for more information about possible methods.
     */
    public EmailProvider email(){
        return new EmailProvider(locale, randomService);
    }

    /**
     * Returns instance of RandomService, initialized with specific Random object,
     * provided to the constructor of this DataGenerator instance.
     * Enables to generate text and numbers (in range, less then, etc.)
     * Refer to RandomService class for information on methods available.
     */
    public RandomService random(){
        return randomService;
    }


}
