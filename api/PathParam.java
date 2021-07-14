package test;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PathParam {
	//Set Base URL
		final static String BASE_URI = "https://petstore.swagger.io/v2/pet";

		@Test
		public void petGetRequestWithPathParam() {
		
	Response response =
	     given().contentType(ContentType.JSON)
	     .when().pathParam("petID", "17232")
	     .get(BASE_URI + "/{petID}");

	System.out.println(response.getBody().asPrettyString());

	response.then().statusCode(200);
		}
}
