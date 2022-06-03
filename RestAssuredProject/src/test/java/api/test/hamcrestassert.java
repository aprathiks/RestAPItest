package api.test;
import org.testng.annotations.BeforeClass;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import io.restassured.RestAssured;

public class hamcrestassert {
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
		  assertThat(statuscode, equalTo(200));

	  }
	  @Test
	  public void Validation2() {
		  
		  String statusbody = response.body().asString();
		  assertThat(statusbody, containsString("available"));

	  }
	  @Test
	  public void Validation3() {
		  //String contenttype1 = response.header("content-type");
		  String contenttype = response.contentType();
		  assertThat(contenttype, equalTo("application/json"));
		  
	  }
  }



