package tests.auth;

import config.RestAssuredBase;
import org.json.JSONObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.PropertiesUtils;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class LoginUserTests extends RestAssuredBase {

    private final String defaultPassword = PropertiesUtils.getProperty("defaultPassword");
    private final String defaultUsername = "Keegan35";

    @Test
    @DisplayName("Should login with success when provided with a valid username and password")
    void validLogin() {

        JSONObject request = new JSONObject();
        request.put("type", "LOGIN");
        request.put("password", defaultPassword);
        request.put("username", defaultUsername);

        given()
            .request()
                .body(request.toString())
        .when()
            .post("/login")
        .then()
                .statusCode(200)
                .body("user.uuid", notNullValue());
    }

    @Test
    @DisplayName("Should have an Unauthorized error when provided with a invalid username and password combination")
    void wrongParams() {
        JSONObject request = new JSONObject();
        request.put("type", "LOGIN");
        request.put("password", defaultPassword+"wrongValue");
        request.put("username", defaultUsername);

        given()
            .request()
                .body(request.toString())
        .when()
            .post("/login")
        .then()
            .statusCode(401);
    }

    @Test
    @DisplayName("Should have a BadRequest error when provided with a empty param to login")
    void emptyParam() {
        JSONObject request = new JSONObject();
        request.put("type", "LOGIN");
        request.put("password", defaultPassword);
        request.put("username", "");

        given()
            .request()
                .body(request.toString())
        .when()
            .post("/login")
        .then()
            .statusCode(400);
    }

}
