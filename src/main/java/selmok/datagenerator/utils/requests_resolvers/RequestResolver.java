package selmok.datagenerator.utils.requests_resolvers;

import selmok.datagenerator.customizers.SingletonLocaleContext;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * This is the class, that used to resolve requests for fake data and then use them to
 * access this data and correspondingly return it.
 * The requests are created by the fake data providers and therefore only them are responsible for the
 * correct request format.
 *
 * @author Cas
 */
public class RequestResolver {

    private static final String REQUEST_SEPARATOR_PATTERN = "[()]";
    private static final String ROOT_DIR = "src//main//resources//";

    /**
     * Uses specifically defined separators to get the part of file name
     * and root directory from the request as well as the json path to
     * the attribute required in json file with fake data. Stores both
     * values in String array and returns it.
     *
     * @return String[]
     */
    public static String[] resolveRequest(String request){
        return request.split(REQUEST_SEPARATOR_PATTERN);
    }

    /**
     * Gets from the request the jsonpath to attribute requested in source json file
     * with specific fake values and returns it
     *
     * @return String
     */
    public static String getJsonPathFromRequest(String request){
       return resolveRequest(request)[1];

    }

    /**
     * If there are multiple json paths in the request (e.g. string with multiple
     * fake values is requested like the full name, where first and last name must be generated)
     * it gets all of them from the request string and returns as list of strings.
     *
     * @return List of String
     */
    public static List<String> getAllJsonPathsFromRequest(String request){
        return Arrays.asList(getJsonPathFromRequest(request).split("\\|"));
    }

    /**
     * Gets from the request the directory where source json file with
     * requested fake values is located. Returns it as String.
     *
     * @return String
     */
    public static String getFileContextFromRequest(String request){
        return resolveRequest(request)[0];
    }

    /**
     * Uses SingletonLocaleContext to get information about country and language for
     * which fake data must be generated to get the path to the source json file.
     * Additionally, gets from the request the directory where source json file with
     * requested fake values is located. Combines all those values in the full filepath
     * and returns it.
     *
     * @return String
     */
    public static String getFilePathFromRequest(SingletonLocaleContext localeContext, String request){
        String fileContext = getFileContextFromRequest(request);

        return ROOT_DIR + localeContext.getLocale().getLanguage() + "//"
                + fileContext + "//"
                + localeContext.getLocale().getCountry() + "_"
                + fileContext+ ".json";

    }

    /**
     * Uses SingletonLocaleContext to get information about country and language for
     * which fake data must be generated to get the path to the source json file.
     * Additionally, gets from the request the directory where source json file with
     * requested fake values is located. Combines all those values in the full filepath
     * and creates File object from it.
     *
     * @return File
     */
    public static File getFileFromRequest(SingletonLocaleContext localeContext, String request){
        return new File(getFilePathFromRequest(localeContext, request));
    }
}
