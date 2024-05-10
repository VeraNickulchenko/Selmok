package selmok.datagenerator.services;

import java.util.Random;

/**
 * RandomService class provides functionality for generating random strings and numbers.
 * It utilizes the java.util.Random class for generating random numbers.
 */
public class RandomService {
    private Random rand;
    private static final String CHAR_LOWER_SRC_STRING = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER_SRC_STRING = CHAR_LOWER_SRC_STRING.toUpperCase();
    private static final String SPECIAL_CHARS_SRC_STRING = "$#&*{}[],=-().+;/!%^?><:~`|";
    private static final String NUMBER_SRC_STRING = "0123456789";

    /**
     * Creates RandomService instance with default Random object.
     * (uses default Random constructor with no parameters)
     */
    public RandomService() {
        this.rand = new Random();
    }

    /**
     * Creates RandomService instance with specifically configured Random object.
     */
    public RandomService(Random rand) {
        this.rand = rand;
    }

    /**
     * Retrieves the underlying Random object used for random numbers/text generation.
     *
     * @return The Random object used for random number generation.
     */
    public Random getRand() {
        return rand;
    }

    /**
     * Generates a random integer.
     *
     * @return The generated random integer.
     */
    public int nextInt(){
        return rand.nextInt();
    }

    /**
     * Generates a random integer less than the specified bound.
     *
     * @param bound The upper bound (exclusive) of the generated random integer.
     * @return The generated random integer.
     */
    public int nextInt(int bound){
        return rand.nextInt(bound);
    }

    /**
     * Generates a random integer within the specified range.
     *
     * @param min The inclusive lower bound of the range.
     * @param max The exclusive upper bound of the range.
     * @return The generated random integer.
     */
    public int nextInt(int min, int max){
        return rand.nextInt(min, max);
    }

    /**
     * Generates a random long integer.
     *
     * @return The generated random long integer.
     */
    public long nextLong(){
        return rand.nextLong();
    }

    /**
     * Generates a random long less than the specified bound.
     *
     * @return The generated random float.
     */
    public long nextLong(long bound){
        return rand.nextLong(bound);
    }

    /**
     * Generates a random long within the specified range.
     *
     * @return The generated random double.
     */
    public long nextLong(long min, long max){
        return rand.nextLong(min, max);
    }

    /**
     * Generates a random floating-point value between 0.0 (inclusive) and 1.0 (exclusive).
     *
     * @return The generated random float.
     */
    public float nextFloat(){
        return rand.nextFloat();
    }

    /**
     * Generates a random floating-point value less than the specified bound.
     *
     * @return The generated random float.
     */
    public float nextFloat(float bound){
        return rand.nextFloat(bound);
    }

    /**
     * Generates a random floating-point value within the specified range.
     *
     * @param min The inclusive lower bound of the range.
     * @param max The exclusive upper bound of the range.
     * @return The generated random float.
     */
    public float nextFloat(float min, float max){
        return rand.nextFloat(min, max);
    }

    /**
     * Generates a random double value between 0.0 (inclusive) and 1.0 (exclusive).
     *
     * @return The generated random double.
     */
    public double nextDouble(){
        return rand.nextDouble();
    }

    /**
     * Generates a random double value less than the specified bound.
     *
     * @param bound The upper bound (exclusive) of the generated random double.
     * @return The generated random double.
     */
    public double nextDouble(double bound){
        return rand.nextDouble(bound);
    }

    /**
     * Generates a random double value within the specified range.
     *
     * @param min The inclusive lower bound of the range.
     * @param max The exclusive upper bound of the range.
     * @return The generated random double.
     */
    public double nextDouble(double min, double max){
        return rand.nextDouble(min, max);
    }

    /**
     * Generates a string representation of the data source based on the specified parameters.
     * The data source is used during random String generation to select characters from
     * and create a random value by adding them until defined length is reached.
     *
     * @param upperLetters  If true, includes uppercase letters in the data source string.
     * @param numbers       If true, includes numbers in the data source string.
     * @param specialChars  If true, includes special characters in the data source string.
     * @return The generated string representation of the data source.
     */
    private String getDataSourceStringByParams(boolean upperLetters, boolean numbers,
                                              boolean specialChars){

        StringBuilder builder = new StringBuilder(CHAR_LOWER_SRC_STRING);

        if(upperLetters){
            builder.append(CHAR_UPPER_SRC_STRING);
        }

        if(numbers){
            builder.append(NUMBER_SRC_STRING);
        }

        if(specialChars){
            builder.append(SPECIAL_CHARS_SRC_STRING);
        }

        return builder.toString();
    }

    /**
     * Generates a random string of the specified length using the given parameters.
     *
     * @param length        The length of the random string to generate.
     * @param upperLetters If true, includes uppercase letters in the generated string.
     * @param numbers      If true, includes numbers in the generated string.
     * @param specialChars If true, includes special characters in the generated string.
     * @return The generated random string.
     */
    public String randomString(int length, boolean upperLetters, boolean numbers, boolean specialChars){
        String srcData = getDataSourceStringByParams(upperLetters, numbers, specialChars);

        StringBuilder resData = new StringBuilder(length);

        for (int i =0; i < length; i++){

            if(i == length-3 && specialChars){
                resData.insert(0, SPECIAL_CHARS_SRC_STRING.charAt(rand.nextInt(SPECIAL_CHARS_SRC_STRING.length())));
            }

            if(i==length/2 && upperLetters){
                resData.insert(length/2, CHAR_UPPER_SRC_STRING.charAt(rand.nextInt(CHAR_UPPER_SRC_STRING.length())));
            }

            if(i==length-1 && numbers){
                resData.insert(length-1, NUMBER_SRC_STRING.charAt(rand.nextInt(NUMBER_SRC_STRING.length())));
            }

            else{
                int charIndex = rand.nextInt(srcData.length());
                resData.append(srcData.charAt(charIndex));
            }

        }


        return resData.toString();
    }



}
