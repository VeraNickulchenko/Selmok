package selmok.datagenerator.utils.error_message_adjustments;

import java.util.List;
import java.util.stream.Stream;

/**
 * Utility class for handling exceptions and constructing error messages.
 *
 * @author Cas
 */
public class ExceptionsHandlerUtils {

    /**
     * Constructs an error message for null objects.
     *
     * @param obj the objects to check for null
     * @param <T> the type of objects to check
     * @return an error message indicating which objects are null
     */
    public static final <T extends Object> String constructErrorMessageForNullObjcts(T... obj){
        String commonPart="The following objects equal to null and therefore cannot be processed" +
                " correctly: ";

        StringBuilder customPart = new StringBuilder();

        List<T> nullObjects = Stream.of(obj).toList();
        for(int i = 0; i < nullObjects.size(); i++){
            if(nullObjects.get(i) == null){
                customPart.append(nullObjects.get(i).getClass() + ": null;\n");
            }
        }

        return commonPart + customPart.toString();

    }

    /**
     * Checks if any of the objects are null and throws an IllegalArgumentException if any are found.
     *
     * @param objects the objects to check for null
     * @param <T>     the type of objects to check
     * @throws IllegalArgumentException if any of the objects are null
     */
    public static final <T extends Object> void checkObjectsForNullAndThrowException(T... objects){
        if(Stream.of(objects).toList().contains(null)){
            throw new IllegalArgumentException(constructErrorMessageForNullObjcts(objects));
        }
    }

    /**
     * Constructs an error message for failed requests, including the exception message and the requests themselves.
     *
     * @param e       the exception that occurred
     * @param requests the requests that failed
     * @return an error message indicating the exception and the failed requests
     */
    public static final String constructErrorMessageForFailedRequests(Exception e, String...requests){

        StringBuilder message = new StringBuilder("Exception " + e.getClass()
                + " has occurred when processing the following requests: ");

        for (String request: requests) {
            message.append(request + "; ");
        }

        message.append("\nPlease see the exception message: " + e.getLocalizedMessage());


        return message.toString();
    }

}
