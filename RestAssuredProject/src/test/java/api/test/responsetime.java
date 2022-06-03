package api.test;


import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class responsetime {
	Response response;

  @Test
  public void time() {
		RestAssured.baseURI="https://petstore.swagger.io/";
		   response = RestAssured.given().pathParam("petid", 99999)
				  .when().get("v2/pet/{petid}");
		   long time = response.getTimeIn(TimeUnit.SECONDS);
		   assertTrue(time<=3,"Response err");
		   System.out.println(time);
		   long time1 = response.getTime();
		   assertTrue(time1<=4500,"Response err");
		   System.out.println(time1);
  }
}
