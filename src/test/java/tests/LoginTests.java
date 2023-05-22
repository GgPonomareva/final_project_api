package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.UserGenerator;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static specs.Specs.request;
import static specs.Specs.response200;

@Epic("API Reqres")
@Feature("User authorization")

public class LoginTests {
    @Test
    void successLoginTest() {

        UserGenerator user = UserGenerator.builder()
                .email("eve.holt@reqres.in")
                .password("cityslicka")
                .build();

        given()
                .spec(request)
                .body(user)
                .when()
                .post("/login")
                .then()
                .log().all()
                .spec(response200)
                .body("token", is(notNullValue()));
    }
}
