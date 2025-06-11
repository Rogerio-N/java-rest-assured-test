package config;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import utils.PropertiesUtils;

public class RestAssuredBase {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = PropertiesUtils.getProperty("baseURI");
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
