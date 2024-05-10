package selmok.datagenerator.utils.requests_resolvers;

import com.fasterxml.jackson.core.type.TypeReference;
import selmok.datagenerator.customizers.SingletonLocaleContext;
import selmok.datagenerator.services.RandomService;
import selmok.datagenerator.utils.dateparser.jsonparser.JSONParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is the utility class, used to execute the requests for the specific fake data
 * and return the request result, represented as String. The result is further returned
 * by the fake data provider.
 *
 * @author  Cas
 */
public class RequestExecutor {

    /**
     * Executes the request and returns the list of objects, returned as response.
     * The objects returned are represented as HashMap&lt;String, List&lt;String&gt;&gt;, where
     * String is the key, that represent attribute requested and the List&lt;String&gt; is the
     * value, that represents value of the attribute.
     * The request is resolved with help of RequestResolver utility.
     */
    public static List<HashMap<String, List<String>>> getListOfValuesByRequest(SingletonLocaleContext locale,
                                                                               String request) throws IOException {
        return JSONParser.readObjectListsByFewJsonPaths(RequestResolver
                        .getFileFromRequest(locale, request),
                RequestResolver.getAllJsonPathsFromRequest(request),
                new TypeReference<HashMap<String, List<String>>>() {});
    }

    /**
     * Returns fake String, generated as the result of request.
     * The String is formed from the values, randomly selected for each object from the
     * list of result objects, returned as the response to request.
     * It iterates through list and for each HashMap selects random key, invokes
     * the list, stored under this key and then selects the random value from this list.
     * All those values are stored in StringBuilder, separated by " ". This allows
     * to format full addresses (e.g. Poland, 31-436 Krakow, ul.Pradnica 10), full names, etc.
     * If there only one value requested, the separator is not used. The string contains only the
     * value requested and no additional spaces.
     *
     * @return String
     */
    public static String getFakeValueByRequest(SingletonLocaleContext localeContext, String request,
                                               RandomService rand)
            throws IOException {
        List<HashMap<String, List<String>>> res =  getListOfValuesByRequest(localeContext, request);
        StringBuilder resultValue = new StringBuilder();
        String valsSeparator = res.size() > 0 ? " " : "";

        for (HashMap<String, List<String>> fakeValsMap : res) {
            resultValue.append(getFakeValueFromHashMap(fakeValsMap, rand) + valsSeparator);
        }

        return resultValue.toString();

    }

    /**
     * Retrieves a fake value based on the provided request using the specified locale context and random service.
     * Apart from that, it uses the getKeysOnSameIndexOrLast to control from which index the keys
     * are selected for each HashMap. If set index is bigger then the length of key set for any
     * of the HashMap, its last key is selected.
     * This is the special approach and should be used carefully only in specific cases (e.g. generating
     * full name, considering last and first name would match by gender)
     *
     * @param localeContext        The locale context to use for the request.
     * @param request              The request string specifying the type of fake value to retrieve.
     * @param rand                 The random service to use for generating random values.
     * @param getKeysOnSameIndexOrLast If true, gets keys on the same index; otherwise, gets the last index.
     * @return The generated fake value based on the request.
     * @throws IOException If an I/O error occurs while processing the request.
     */
    public static String getFakeValueByRequest(SingletonLocaleContext localeContext, String request,
                                               RandomService rand, boolean getKeysOnSameIndexOrLast)
            throws IOException {
        List<HashMap<String, List<String>>> res =  getListOfValuesByRequest(localeContext, request);
        StringBuilder resultValue = new StringBuilder();
        String valsSeparator = res.size() > 1 ? " " : "";
        int randomIndex = getKeysOnSameIndexOrLast ? rand.nextInt(res.get(0)
                .keySet().size()) : -1;

        for (HashMap<String, List<String>> fakeValsMap : res) {

            if(randomIndex >= 0){
                resultValue.append(getFakeValueFromHashMap(fakeValsMap, rand, randomIndex) + valsSeparator);
            }

            else{
                resultValue.append(getFakeValueFromHashMap(fakeValsMap, rand) + valsSeparator);
            }
        }

        return resultValue.toString();

    }

    /**
     * Retrieves a fake value from the specified HashMap using the given random service and special index or the last index
     * to retrieve the key from the keys set and further work and select from corr. values, stored
     * under this key.
     * Should be used in specific cases (e.g. full name generation to get first and last name, that
     * would match each other by gender)
     *
     * @param initHashMap       The HashMap containing the fake values.
     * @param rand              The random service to use for generating random values.
     * @param specialIndexOrLast The index to retrieve the fake value from, or if negative, retrieves from the last index.
     * @return The generated fake value from the HashMap.
     */
    public static String getFakeValueFromHashMap(HashMap<String, List<String>> initHashMap,
                                                 RandomService rand, int specialIndexOrLast){

        List<String> arrayOfMapKeys = new ArrayList<>(initHashMap.keySet());

        if(arrayOfMapKeys.size() <= specialIndexOrLast){
            specialIndexOrLast = arrayOfMapKeys.size() - 1;
        }

        List<String> fakeValuesStream = initHashMap
                .get(arrayOfMapKeys.get(specialIndexOrLast));

        return fakeValuesStream.get(rand.nextInt(fakeValuesStream.size()));


    }

    /**
     * Selects random key from provided HashMap, then invokes the list,
     * stored under this key and eventually selects the random value from this list.
     *
     * @return String
     */
    public static String getFakeValueFromHashMap(HashMap<String, List<String>> initHashMap,
                                                 RandomService rand){

        List<String> arrayOfMapKeys = new ArrayList<>(initHashMap.keySet());

        List<String> fakeValuesStream = initHashMap
                .get(arrayOfMapKeys.get(rand.nextInt(arrayOfMapKeys.size())));

        return fakeValuesStream.get(rand.nextInt(fakeValuesStream.size()));


    }

}
