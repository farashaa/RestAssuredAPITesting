package com.demo.tests;

import static com.resources.Payload.studentPayload;
import static com.utils.ResponseToJsonpathConvertor.jsonConvertor;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestLocalAPIStudent extends BaseLocalTest{

	@Test(priority=0)
	public  void testGetStudent() {

		int subjectId=2;

		Response response=given().when().get("/student").then().extract().response();

		JsonPath jsonPath = jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 200);
		System.out.println(jsonPath.getString("id[8]"));
		System.out.println(jsonPath.getString("firstName[8]"));
		System.out.println(jsonPath.getString("lastName[8]"));
		System.out.println(jsonPath.getInt("subjectId[8]"));
		Assert.assertNotNull(jsonPath.getString("id[8]"));
		Assert.assertEquals(jsonPath.getInt("subjectId[8]"), subjectId);

	}
	@Test(priority=1)
	public  void testGetStudentById() {

		int subjectId=1;
		Response response=given().when().get("/student/1").then().extract().response();

		JsonPath jsonPath = jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getString("id"));
		Assert.assertEquals(jsonPath.getInt("subjectId"), subjectId);

	}
	@Test(priority=2)
	public void testPostStudent() {
		String firstName="kevin";
		String lastName="can";
		String subjectId="1";

		Response response=given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(studentPayload(firstName, lastName, subjectId))
				.when()
				.post("/student")
				.then().extract().response();

		JsonPath jsonPath = jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertNotNull(jsonPath.getString("id"));

	}
	@Test(priority=3)
	public void testPutStudent() {

		String firstName="henry";
		String lastName="holmes";
		String subjectId="2";

		Response response=given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(studentPayload(firstName, lastName, subjectId))
				.when()
				.put("/student/10")
				.then()
				.extract().response();

		JsonPath jsonPath = jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getString("id"));
	}
	@Test(priority=4)
	public void testPatchstudent() {

		String firstName="abella";
		String lastName="valdes";
		String subjectId="6";

		Response response=given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(studentPayload(firstName, lastName, subjectId))
				.when()
				.patch("/student/15")
				.then()
				.extract().response();

		JsonPath jsonPath = jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getString("id"));
	}
	@Test(priority=5)
	public void testDeleteStudent() {
		Response response=when().delete("/student/19").then().extract().response();

		Assert.assertEquals(response.statusCode(), 200);
	}
}
