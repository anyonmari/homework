package api.Lesson_16.test;

import api.Lesson_16.Specification;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequestEchoTest {
    private final static String URL = "https://postman-echo.com";
    @Test
    public void getRequestTest() {
        Specification.installSpecification(Specification.requestSpec(URL));
        given()
                .basePath("/get")
                .queryParam("foo1", "bar1")
                .queryParam("foo2", "bar2")
                .when()
                .get()
                .then().log().all()
                .statusCode(200)
                .body("headers", not(emptyArray()))
                .body("args.foo1", equalTo("bar1"))
                .body("args.foo2", equalTo("bar2"))
                .body("url", equalTo("https://postman-echo.com/get?foo1=bar1&foo2=bar2"));
    }
}