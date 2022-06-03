package api.test;

import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class jsonassert {
		Response response;
		@Test
		public void json() throws JSONException {
			RestAssured.baseURI="https://petstore.swagger.io/";
			   response = RestAssured.given().pathParam("petid", 99999)
					  .when().get("v2/pet/{petid}");
			   String body = ("{\n"
			   		+ "  \"id\": 99999,\n"
			   		+ "  \"category\": {\n"
			   		+ "    \"id\": 99,\n"
			   		+ "    \"name\": \"Labrador\"\n"
			   		+ "  },\n"
			   		+ "  \"name\": \"Tommy\",\n"
			   		+ "  \"photoUrls\": [\n"
			   		+ "    \"string\"\n"
			   		+ "  ],\n"
			   		+ "  \"tags\": [\n"
			   		+ "    {\n"
			   		+ "      \"id\": 9,\n"
			   		+ "      \"name\": \"RIP\"\n"
			   		+ "    }\n"
			   		+ "  ],\n"
			   		+ "  \"status\": \"available\"\n"
			   		+ "}");
			   JSONAssert.assertEquals(body,response.asString(),true);
		}
  }

