package test;
import org.hamcrest.Matcher;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.net.MalformedURLException;
import java.net.URL;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SchemaValidation {

	String ROOT_URI = "https://petstore.swagger.io/v2/pet";
	
	 
	
	@Test
		
	public void getPetInfo() throws MalformedURLException {
		
	    // Generate response
		
	    Response response = 
		
	        given().contentType(ContentType.JSON) // Set headers
		
	        .when().get(ROOT_URI + "/77232"); // Send GET request
		
	 
		
	    // Print response
		
	    System.out.println(response.asPrettyString());
		
	 
		
	    // Validate JSON Schema from URL
		
	    URL swaggerSchema = new URL("https://petstore.swagger.io/v2/swagger.json");
		
	    response.then().body(matchesJsonSchema(swaggerSchema));
		
	}



	private Matcher<?> matchesJsonSchema(URL swaggerSchema) {
		// TODO Auto-generated method stub
		return null;
	}
}
