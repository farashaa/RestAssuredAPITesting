package com.demo.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class JsonSchemaValidator extends BaseTest {
	//@Test
	public static void testListUsers() {
		Response response = (Response) given()
		        .when().contentType(ContentType.JSON).get("/users?page=2")
		        .then().assertThat().body(matchesJsonSchemaInClasspath("Schema.json"));
		Assert.assertEquals(response.statusCode(), 200);
		
	//Response response=(Response)given().when().get("/users?page=2").then().assertThat().body(matchesJsonSchemaInClasspath("schema.json")).extract().response();
	
	//Assert.assertEquals(response.statusCode(), 200);
	}
	@Test
	public static void testStudent() {
		given().when().get("/student").then().assertThat().body(matchesJsonSchemaInClasspath("Schema.json")).statusCode(200).log().all();
	}
}
