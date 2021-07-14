package GitHub_Rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class GitHubRestAssured {
	
	RequestSpecification requestSpec;
	
	String sshkey;
	int sshkeyid;
	
	@BeforeClass
	public void setUp() {
	
	requestSpec = new RequestSpecBuilder()
			.setContentType(ContentType.JSON)
			.addHeader("Authorization", "token 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123")
	        .setBaseUri("https://api.github.com")
	        .build();
	
	sshkey = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCFUOc4UGL05Lg9RWSqJ40glnTo+ZqYWVi2cXzdjUSYujtxu+qe2mTF6RdUYzTmA7JImHAEO4jRDb/eGN7sDO3HW4zFCAI/oKD5GQ0qtNolMcWxdH2lHm2ArJ2Uaid8lN6/JVvc+N+LDgiWgqwCEUlzFoHvXHBKmkms/oWoA0g7Ei1jo+c/XP4RrWk18dG8wWbjLz2nFnbwOtjKSnemUywsXBKUkYKK1mW3r2HxdPPimnX06DdtLVzHlM66QjH4mXVvU55V5Xnz7Fs4m9RcLyyZu2T3c57YRVVmU9Scuz7oPwxlkm5KoLImBgnKvwo0Xd+jKH69GdSrhAgSSbRCXGEz456 "; 
}
	
	@Test(priority = 1)
	public void addKeys() {
		String reqBody = "{\"title\": \"TestKey\", \"key\": \"" + sshkey + "\"}";
		Response response = given().spec(requestSpec)
				.body(reqBody)
				.when().post("/user/keys");
		
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		
		sshkeyid = response.then().extract().path("id");
		
		response.then().statusCode(201);
		
		
}
	@Test (priority = 2)
	public void getKeys() {
		String reqBody = "{\"title\": \"TestKey\", \"key\": \"" + sshkey + "\"}";
		Response response = given().spec(requestSpec)
				.body(reqBody)
				.when().get("/user/keys");
		
		String resBody = response.getBody().asPrettyString();
		System.out.println(resBody);
		
		response.then().statusCode(200);
	}

		@Test (priority = 3)
		public void deleteKeys() {
			Response response = given().spec(requestSpec)
					.pathParam("keyId", sshkeyid).when().delete("/user/keys/{keyId}");
			
			String resBody = response.getBody().asPrettyString();
			System.out.println(resBody);
			
			response.then().statusCode(204);
			
		}
 }


