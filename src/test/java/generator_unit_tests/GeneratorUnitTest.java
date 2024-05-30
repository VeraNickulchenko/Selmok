package generator_unit_tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import selmok.datagenerator.Generator;
import selmok.datagenerator.customizers.LocaleContext;
import selmok.datagenerator.enums.Countries;
import selmok.datagenerator.enums.Languages;
import selmok.datagenerator.utils.dateparser.jsonparser.JSONParser;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneratorUnitTest {
    private static final Generator GENERATOR =
            new Generator(new LocaleContext(Countries.USA, Languages.UKRAINIAN));
    private static final String JSON_DATA_FILE = "src/main/resources/faker_date/ukr/name/us_name.json";
    private static final String EMAIL_JSON_DATA_FILE = "src/main/resources/faker_date/en/name/us_name.json";

    public String getExpectedDataJSONString(String fileName) throws IOException {
        return JSONParser.readFileToJsonNode(
                new File(fileName)).toPrettyString();

    }

    public boolean verifyStringMatchesRegex(String regex, String str){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();

    }

    @Test
    public void validateStringGenerate_AllParameters(){
        String resultString = GENERATOR.random()
                .randomString(3, true, true, true);

        Assert.assertEquals(resultString.length(), 3);
        Assert.assertTrue(verifyStringMatchesRegex(
                "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[$#&*{}\\[\\],=\\-().+;/!%^?><:~`|]).+$", resultString
        ));
    }

    @Test
    public void validateStringGenerate_NoParameters(){

        String resultString = GENERATOR.random()
                .randomString(9, false, false, false);

        Assert.assertEquals(resultString.length(), 9);
        Assert.assertTrue(verifyStringMatchesRegex("^[a-z]+$", resultString));

    }

    @Test
    public void validateStringGenerate_OneParameter(){

        String resultString = GENERATOR.random().randomString(4, false, true, false);

        Assert.assertEquals(resultString.length(), 4);
        Assert.assertTrue(verifyStringMatchesRegex("^[a-z0-9]+$", resultString));
    }

    @Test
    public void validateFullNameGenerate() throws IOException {
        String jsonContent = getExpectedDataJSONString(JSON_DATA_FILE);
        String resultName = GENERATOR.name().fullName();
        String[] nameParts = resultName.split(" ");

        Assert.assertEquals(nameParts.length, 2);
        Assert.assertTrue(jsonContent.contains(nameParts[0]));
        Assert.assertTrue(jsonContent.contains(nameParts[1]));

    }

    @Test
    public void validateFirstNameGenerate() throws IOException {
        String jsonContent = getExpectedDataJSONString(JSON_DATA_FILE);
        String resultName = GENERATOR.name().firstName();

        Assert.assertTrue(jsonContent.contains(resultName));
    }

    @Test
    public void validateFakeDomainGenerate(){
        Assert.assertTrue(verifyStringMatchesRegex("^@[a-z]+\\.[a-zA-Z]{2,}$",
                GENERATOR.email().getEmailDomainName()));
    }

    @Test
    public void validateFullNameEmailGenerate_wSeparator() throws IOException {

        String separator = "/";
        String jsonContent = getExpectedDataJSONString(EMAIL_JSON_DATA_FILE);
        String resultStr = GENERATOR.email().getEmailLocalePartAsFullName(separator);

        Assert.assertTrue(resultStr.contains(separator));

        String[] dividedBySeparator = resultStr.split(separator);

        Assert.assertEquals(dividedBySeparator.length, 2);
        Assert.assertTrue(jsonContent.contains(dividedBySeparator[0]));
        Assert.assertTrue(jsonContent.contains(dividedBySeparator[1]));

    }

    @Test
    public void validateFullNameEmailGenerate_woSeparator() throws IOException {
        String jsonContent = getExpectedDataJSONString(EMAIL_JSON_DATA_FILE);
        String resultStr = GENERATOR.email().getEmailLocalePartAsFullName();

        Assert.assertTrue(resultStr.contains("_"));

        String[] dividedBySeparator = resultStr.split("_");

        Assert.assertEquals(dividedBySeparator.length, 2);
        Assert.assertTrue(jsonContent.contains(dividedBySeparator[0]));
        Assert.assertTrue(jsonContent.contains(dividedBySeparator[1]));

    }

    @Test
    public void validateFullEmailGenerate_FakeDomain(){
        Assert.assertTrue(verifyStringMatchesRegex("^[a-z]+@[a-z]+\\.[a-z]{2,}$",
                GENERATOR.email().getEmail()));
    }

    @Test
    public void validateFullEmail_CustomDomain(){
        Assert.assertTrue(verifyStringMatchesRegex("^[a-z]+@fakeDomain\\.com$",
                GENERATOR.email().getEmail("@fakeDomain.com")));
    }




}