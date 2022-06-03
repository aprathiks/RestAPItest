package api.test;

import java.util.List;
import java.util.Map;

import org.testng.annotations.Test;
import io.restassured.response.Response;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Headers;

public class headerres {
  @Test
  public void Response() {
	  Response res = RestAssured
			  .given().pathParam("petid", 99999)
			  .when().get("https://petstore.swagger.io/v2/pet/{petid}");
	  res.prettyPrint();
	  
	  Headers allHeaders = res.getHeaders();
	  System.out.println(allHeaders);
	  
	  String contenttype = res.header("content-type");
	  System.out.println(contenttype);
  }
  
  @Test
  public void Responsecookies() {
	  Response res = RestAssured
			  .given()
			  .when().get("https://www.google.com/");
	 Map<String, String> allCookies = res.getCookies();
	 System.out.println(allCookies);
	  
	 String singlecookie = res.getCookie("1P_JAR");
	 System.out.println(singlecookie);
	 
	 Cookie Allinfosinglecookie = res.getDetailedCookie("1P_JAR");
	 System.out.println(Allinfosinglecookie);	  
  }
  
  @Test
  public void xmlpathdemo() {
	  Response response = RestAssured
			  .given().queryParam("page", 2)
			  .when().get("https://reqres.in/api/users?page=2");
	  String seconduseremailid = response.jsonPath().get("data[1].email");
	  System.out.println(seconduseremailid);
	  
	  List<String> allEmailIds = response.jsonPath().get("data.findAll{it.id<11}.email");
	  System.out.println(allEmailIds);
			  
  }
  String payload = ("{\n"
  		+ "  \"id\": 7,\n"
  		+ "  \"petId\": 99,\n"
  		+ "  \"quantity\": 1,\n"
  		+ "  \"shipDate\": \"2022-06-03T14:13:27.452Z\",\n"
  		+ "  \"status\": \"placed\",\n"
  		+ "  \"complete\": true\n"
  		+ "}");
  @Test
  public void Corelation() {
	  Response response = RestAssured
			  .given().contentType(ContentType.JSON).body(payload)
			  .when().post("https://petstore.swagger.io/v2/store/order");
	  response.prettyPrint();
	  int id = response.jsonPath().get("id");
	  System.out.println(id);
		
	  RestAssured
	  .given().pathParam("id", id)
	  .when().get("https://petstore.swagger.io/v2/store/order/{id}")
	  .then().log().body().statusCode(200);
	  
  }
  
}
