package selmok.datagenerator.dataproviders;

import selmok.datagenerator.customizers.SingletonLocaleContext;
import selmok.datagenerator.services.RandomService;
import selmok.datagenerator.utils.error_message_adjustments.ExceptionsHandlerUtils;
import selmok.datagenerator.utils.requests_resolvers.RequestExecutor;
import org.apache.logging.log4j.Level;

import java.io.IOException;
import java.util.HashMap;

/**
 * Provides methods for generating names, extending the functionality of the BaseProvider class.
 * This class encapsulates the logic for generating first names and last names using predefined values,
 * randomly selected from the source json file.
 * Instances of this class can be used to generate random first names and last names based on the provided locale.
 */
public class NameProvider extends BaseProvider {

    /**
     * Initializes the map of requests executed to get corresponding fake date (first name, last name,
     * full name). The requests are jsonpath to json files, that holds the information to generate from.
     */
    private static final HashMap<String, String> requests = new HashMap<>();

    static {
        requests.put("first_name", "name(/generator/first_name)");
        requests.put("last_name", "name(/generator/last_name)");
        requests.put("full_name", "name(/generator/first_name|/generator/last_name)");
    }

    /**
     * Using the BaseProvider.class constructor, initializes the instance of NameProvider
     * with SingletonLocaleContext to define country and language for which data will be generated
     * and RandomService settings to use (e.g. random seed).
     */
    public NameProvider(SingletonLocaleContext locale, RandomService random) {
        super(locale, random);

    }

    /**
     * Generates a random first name.
     * This method executes a request to obtain a fake value for the first name using the 'first_name' pattern
     * defined in the 'requests' HashMap. It utilizes the provided locale and random service.
     *
     * <p>If the request fails due to an IOException, a FATAL level log message is generated using the logger
     * from the base class, indicating the failure to retrieve the fake value for the first name.</p>
     *
     * @return A random first name generated, or null if the request fails.
     */
    public String firstName(){
        try {
            return RequestExecutor.getFakeValueByRequest(getLocale(), requests.get("first_name"),
                    getRandom());
        } catch (IOException e) {
            getISSUE_LOG().log(Level.FATAL, ExceptionsHandlerUtils
                    .constructErrorMessageForFailedRequests(e, requests.get("first_name")));
        }

        return null;
    }

    /**
     * Generates a random last name.
     * This method executes a request to obtain a fake value for the last name using the 'last_name' pattern
     * defined in the 'requests' HashMap. It utilizes the provided locale and random service.
     *
     * <p>If the request fails due to an IOException, a FATAL level log message is generated using the logger
     * from the base class, indicating the failure to retrieve the fake value for the last name.</p>
     *
     * @return A random last name generated based on the predefined pattern, or null if the request fails.
     */
    public String lastName(){
        try {
            return RequestExecutor.getFakeValueByRequest(getLocale(), requests.get("last_name"),
                    getRandom());
        } catch (IOException e) {
            getISSUE_LOG().log(Level.FATAL, ExceptionsHandlerUtils
                    .constructErrorMessageForFailedRequests(e, requests.get("last_name")));
        }

        return null;
    }

    /**
     * Generates a random full name.
     * This method executes a request to obtain a fake value for the full name using the pattern specified
     * in the 'requests' HashMap with the key 'full_name'.
     * It utilizes the provided locale and random service.
     *
     * <p>If the request fails due to an IOException, a FATAL level log message is generated using the logger
     * from the base class, indicating the failure to retrieve the fake value for the full name.</p>
     *
     * @return A random full name generated based on the pattern specified in the 'requests' HashMap,
     * or null if the request fails.
     */
    public String fullName(){
        try {
            return RequestExecutor.getFakeValueByRequest(getLocale(),
                    requests.get("full_name"), getRandom(), true);
        } catch (IOException e) {
            getISSUE_LOG().log(Level.FATAL, ExceptionsHandlerUtils
                    .constructErrorMessageForFailedRequests(e,requests.get("full_name")));
        }

        return null;
    }





}
