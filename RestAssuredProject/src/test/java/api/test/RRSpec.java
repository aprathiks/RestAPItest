package api.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RRSpec {
	Response response;
	static RequestSpecBuilder requestBuilder;
	static RequestSpecification requestSpec;
	static ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;
	
	
	@BeforeClass
	public static void setUp() {
		//Request
		requestBuilder = new RequestSpecBuilder();
		requestBuilder.addHeader("accept", "application/json");
		requestSpec=requestBuilder.build();
		
		//Response
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectHeader("content-type", "application/json");
		responseBuilder.expectHeader("server", "Jetty(9.2.9.v20150224)");
		responseSpec=responseBuilder.build();
	}
  @Test
  public void Spec1() {
		 RestAssured.given().spec(requestSpec)
				  .when().get("https://petstore.swagger.io/v2/pet/99999")
				  .then()
				  .spec(responseSpec);
  }
  @Test
  public void Spec2() {
		RestAssured.given().spec(requestSpec)
				  .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=available")
				  .then()
				  .spec(responseSpec);
  }
}
