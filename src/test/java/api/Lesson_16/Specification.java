package api.Lesson_16;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.RestAssured.*;
import   io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;


public class Specification {

    public static  RequestSpecification requestSpec(String url) {
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();
    }

    public static void installSpecification(RequestSpecification request) {
        RestAssured.requestSpecification = request;
    }

}