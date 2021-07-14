package test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;



public class QueryParam {
//Set Base URL
	final static String BASE_URI = "https://petstore.swagger.io/v2/pet/findByStatus";

	@Test
	public void petGetRequest() {
	
Response response =
     given().contentType(ContentType.JSON)
     .when().queryParam("status", "pending")
     .get(BASE_URI);

System.out.println(response.getBody().asPrettyString());

response.then().statusCode(200);

	}
}

