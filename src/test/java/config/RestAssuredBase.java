package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.jupiter.api.BeforeAll;
import utils.PropertiesUtils;

public class RestAssuredBase {

    @BeforeAll
    public static void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(PropertiesUtils.getProperty("baseURI"))
                .addHeader("Content-Type", "application/json")
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

}
