package api.Lesson_16.test;

import api.Lesson_16.Specification;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestEchoTest {
    private final static String URL = "https://postman-echo.com";
    @Test
    public void postFormDataRequestTest() {
        Specification.installSpecification(Specification.requestSpec(URL));
        given()
                .basePath("/post")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .formParam("foo1", "bar1")
                .formParam("foo2", "bar2")
                .when()
                .post()
                .then().log().body()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body("form.foo1", equalTo("bar1"))
                .body("form.foo2", equalTo("bar2"));
    }

    @Test
    public void postRawTextRequestTest() {
        Specification.installSpecification(Specification.requestSpec(URL));
        Map<String,Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("text", "value");

        given()
                .basePath("/post")
                .contentType(ContentType.JSON)
                .body(jsonAsMap)
                .when()
                .post()
                .then().log().all()
                .statusCode(200)
                .body("data.text", equalTo("value"));
    }
}