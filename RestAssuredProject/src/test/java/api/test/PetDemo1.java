package api.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PetDemo1 {
  @Test
  public void ReadInventory() {
	  RestAssured
	  .given()
	  .when().get("https://petstore.swagger.io/v2/store/inventory")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void AddpettoInventory() {
	  RestAssured.given().contentType(ContentType.JSON).body(new File("src\\test\\resources\\testData\\addpet.json"))
	  .when().post("https://petstore.swagger.io/v2/pet")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void UpdatepetfromInventory() {
	  RestAssured.given().contentType(ContentType.JSON).body(new File("src\\test\\resources\\testData\\addpet.json"))
	  .when().put("https://petstore.swagger.io/v2/pet")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void GetpetfromInventory() {
	  RestAssured.given().pathParam("petid", "99999")
	  .when().get("https://petstore.swagger.io/v2/pet/{petid}")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void DeletepetfromInventory() {
	  RestAssured.given().pathParam("petid", "99999")
	  .when().delete("https://petstore.swagger.io/v2/pet/{petid}")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void logdemo4() {
	  RestAssured.given().pathParam("petid", "99999")
	  .when().delete("https://petstore.swagger.io/v2/pet/{petid}")
	  .then().log().ifValidationFails().statusCode(200);
  }
  @Test
  public void logdemo3() {
	  RestAssured.given().pathParam("petid", "99999").log().all()
	  .when().delete("https://petstore.swagger.io/v2/pet/{petid}")
	  .then().log().ifStatusCodeIsEqualTo(404).statusCode(200);
  }
  @Test
  public void GetpetbystatusfromInventory() {
	  RestAssured.given()
	  .when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=pending")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void GetpetbystatusparamfromInventory() {
	  RestAssured.given().queryParam("status", "pending")
	  .when().get("https://petstore.swagger.io/v2/pet/findByStatus")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void logdemo2() {
	  RestAssured.given().queryParam("status", "pending").log().all()
	  .when().get("https://petstore.swagger.io/v2/pet/findByStatus")
	  .then().log().body().statusCode(200);
  }
  @Test
  public void Postorderbypetid() {
	  Map<String, Object> bodyparams = new HashMap<String, Object>();
	  bodyparams.put("id", 9);
	  bodyparams.put("petId", 99999);
	  bodyparams.put("quantity", 1);
	  bodyparams.put("shipDate", "2022-06-03");
	  bodyparams.put("status", "placed");
	  bodyparams.put("complete", "true");
	  
	  String jsonPayload = new Gson().toJson(bodyparams);
	  
	  RestAssured.given().contentType(ContentType.JSON).body(jsonPayload)
	  .when().post("https://petstore.swagger.io/v2/store/order")
	  .then().log().body().statusCode(200);
	  
  }
  @Test

  public void logdemo1() {
	  Map<String, Object> bodyparams = new HashMap<String, Object>();
	  bodyparams.put("id", 9);
	  bodyparams.put("petId", 99999);
	  bodyparams.put("quantity", 1);
	  bodyparams.put("shipDate", "2022-06-03");
	  bodyparams.put("status", "placed");
	  bodyparams.put("complete", "true");
	  
	  String jsonPayload = new Gson().toJson(bodyparams);
	  
	  RestAssured.given().contentType(ContentType.JSON).body(jsonPayload).log().all()
	  .when().post("https://petstore.swagger.io/v2/store/order")
	  .then().log().body().statusCode(200);
	  
  }
 
  @Test(dataProviderClass = Data.dataprovider.class, dataProvider = "dp")
  public void DataDemo(String usn, String pwd, String id) {
	  System.out.println(usn+"\t\t"+pwd+"\t\t"+id);
  }
  
  @Test(dataProviderClass = Data.dataprovider.class, dataProvider = "status")
  public void DataDemo2(String st) {
	  RestAssured.given().queryParam("status", st)
	  .when().get("https://petstore.swagger.io/v2/pet/findByStatus")
	  .then().log().body().statusCode(200);
}
}
