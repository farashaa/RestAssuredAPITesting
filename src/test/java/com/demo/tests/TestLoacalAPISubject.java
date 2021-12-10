package com.demo.tests;

import static com.resources.Payload.subjectPayload;
import static com.utils.ResponseToJsonpathConvertor.jsonConvertor;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestLoacalAPISubject extends BaseLocalTest {
	@Test(priority=12)
	public  void testGetSubject() {

		int id= 3;
		String name="Networks";

		Response response=given().when().get("/subject").then().extract().response();

		JsonPath jsonPath = jsonConvertor(response);

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getInt("id[2]"), id);
		Assert.assertEquals(jsonPath.getString("name[2]"), name);
	}
	@Test(priority=13)
	public  void testGetSubjectById() {
		int id= 1;
		String name= "Automation";
		Response response=given().when().get("/subject/1").then().extract().response();

		JsonPath jsonPath = jsonConvertor(response);

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getInt("id"), id);
		Assert.assertEquals(jsonPath.getString("name"), name);
	}
	@Test(priority=14)
	public void testPostSubject() {

		String name = "Operations";
		

		Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).body(subjectPayload(name))
				.when()
				.post("/subject")
				.then()
				.extract().response();

		JsonPath jsonPath = jsonConvertor(response);

		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertNotNull(jsonPath.getInt("id"));
		Assert.assertEquals(jsonPath.getString("name"), name);

	}
	@Test(priority=15)
	public void testPutSubject() {

		String name = "Networks";
		
		Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).body(subjectPayload(name))
				.when()
				.put("/subject/3")
				.then()
				.extract().response();

		JsonPath jsonPath = jsonConvertor(response);

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getInt("id"));
		Assert.assertEquals(jsonPath.getString("name"), name);
	}
	@Test(priority=16)
	public void testPatchSubject() {

		String name = "SAP";
	

		Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).body(subjectPayload(name))
				.when()
				.patch("/subject/4")
				.then()
				.extract().response();

		JsonPath jsonPath = jsonConvertor(response);

		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getInt("id"));
		Assert.assertEquals(jsonPath.getString("name"), name);
	}
	@Test(priority=17)
	public void testDeleteSubject() {
		Response response=when().delete("/subject/9").then().extract().response();

		Assert.assertEquals(response.statusCode(), 200);
	}
}
