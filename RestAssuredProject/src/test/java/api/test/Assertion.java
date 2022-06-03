package api.test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;

public class Assertion {
	Response response;
	@BeforeClass
	public void Before() {
		RestAssured.baseURI="https://petstore.swagger.io/";
		   response = RestAssured.given().pathParam("petid", 99999)
				  .when().get("v2/pet/{petid}");
	}
  @Test
  public void Validation1() {
	  
	  int statuscode = response.getStatusCode();
	  assertEquals(statuscode,200);

  }
  @Test
  public void Validation2() {
	  
	  String statusbody = response.body().asString();
	  assertTrue(statusbody.contains("available"));

  }
  @Test
  public void Validation3() {
	  //String contenttype1 = response.header("content-type");
	  String contenttype = response.contentType();
	  assertEquals(contenttype,"application/json");
  }

}
