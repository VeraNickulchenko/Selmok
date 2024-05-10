package selmok.datagenerator.utils.dateparser.jsonparser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The utility class, used to read values from json and interact with them
 * by deserializing into objects, searching for them by jsonpath, etc.
 *
 * @author Cas
 */
public class JSONParser {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * static code block, used to configure the ObjectMapper object.
     * The ObjectMapper object confiuration are mostly strict and can be changed, but for
     * test data json parsing it is better to create a separate class.
     */
   static {
       objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
       objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, false);
       objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
       objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
       objectMapper.configure(DeserializationFeature.WRAP_EXCEPTIONS, true);
       objectMapper.configure(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY, true);
   }

    /**
     * Reads the contents of a JSON file into a JsonNode object.
     *
     * @param file the JSON file to read
     * @return a JsonNode containing the contents of the file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public static JsonNode readFileToJsonNode(File file) throws IOException {
            return objectMapper.readTree(file);

    }

    /**
     * Finds a JSON node in the given JsonNode object using the specified JSON Pointer path.
     *
     * @param node         the JsonNode object to search
     * @param pointerPath the JSON Pointer path to search for
     * @return the JSON node found at the specified path, or a missing node if not found
     * @see <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a>
     */
    public static JsonNode findNodeByJsonPointer(JsonNode node, String pointerPath){
       return node.at(pointerPath);
    }

    /**
     * Retrieves a JSON string representation of the JSON node found at the specified JSON Pointer path.
     *
     * @param node the JsonNode object to search
     * @param path the JSON Pointer path to search for
     * @return a JSON string representation of the JSON node found at the specified path
     * @throws JsonProcessingException if an error occurs during JSON processing
     * @see <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a>
     */
    public static String getJsonStringByPath(JsonNode node, String path) throws JsonProcessingException {
       return objectMapper.writerWithDefaultPrettyPrinter()
               .writeValueAsString(findNodeByJsonPointer(node, path));
    }

    /**
     * Deserializes the JSON string into an object of the specified type using Jackson's TypeReference.
     *
     * @param json the JSON string to deserialize
     * @param obj  the TypeReference representing the type of object to deserialize into
     * @return an object of the specified type deserialized from the JSON string
     * @throws JsonProcessingException if an error occurs during JSON processing
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T getObjectFromString(String json, TypeReference<T> obj) throws JsonProcessingException {
       return objectMapper.readValue(json, obj);
    }

    /**
     * Retrieves a specific object from a JSON node at the specified JSON Pointer path and deserializes it into
     * an object of the specified type using Jackson's TypeReference.
     *
     * @param node     the JsonNode object to search
     * @param jsonPath the JSON Pointer path to search for
     * @param obj      the TypeReference representing the type of object to deserialize into
     * @return an object of the specified type deserialized from the JSON node found at the specified path
     * @throws JsonProcessingException if an error occurs during JSON processing
     * @see <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a>
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> T getSpecificObjFromJsonNodeByPath(JsonNode node, String jsonPath,
                                                         TypeReference<T> obj) throws JsonProcessingException {
       return getObjectFromString(getJsonStringByPath(node, jsonPath), obj);
    }

    /**
     * Reads objects from the JSON file at multiple JSON Pointer paths and deserializes them into a list of objects
     * of the specified type using Jackson's TypeReference.
     *
     * @param file          the JSON file to read
     * @param jsonPathes    a list of JSON Pointer paths to search for
     * @param typeReference the TypeReference representing the type of objects to deserialize into
     * @return a list of objects deserialized from the JSON file at the specified paths
     * @throws IOException if an I/O error occurs while reading the file
     * @throws JsonProcessingException if an error occurs during JSON processing
     * @see <a href="https://tools.ietf.org/html/rfc6901">JSON Pointer</a>
     * @see com.fasterxml.jackson.core.type.TypeReference
     */
    public static <T> List<T> readObjectListsByFewJsonPaths(File file, List<String> jsonPathes,
                                                            TypeReference<T> typeReference)
            throws IOException {
        List<T> requestedObjects = new ArrayList<>();
        JsonNode fileContent = readFileToJsonNode(file);

        for (String jsonPath:jsonPathes) {

            T content = getSpecificObjFromJsonNodeByPath(fileContent, jsonPath, typeReference);
            requestedObjects.add(content);

        }

        return requestedObjects;
    }









}
