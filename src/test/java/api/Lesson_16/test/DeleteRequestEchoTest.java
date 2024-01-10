package api.Lesson_16.test;

import api.Lesson_16.Specification;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequestEchoTest {
    private final static String URL = "https://postman-echo.com";
    @Test
    public void deleteRequestTest() {
        Specification.installSpecification(Specification.requestSpec(URL));
        given()
                .basePath("/delete")
                .contentType(ContentType.TEXT)
                .body("This is expected to be sent back as part of response body.")
                .when()
                .delete()
                .then().log().all()
                .statusCode(200)
                .body("data", equalTo("This is expected to be sent back as part of response body."));
    }
}