package com.demo.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static com.resources.Payload.subjectPayload;
import org.testng.Assert;
import org.testng.annotations.Test;
import static com.resources.Payload.studentPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestLocalAPI extends BaseLocalTest{

	@Test(priority=0)
	public  void getStudent() {
		Response response=given().when().get("/student").then().extract().response();

		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test(priority=1)
	public void postStudent() {
		String firstName="kevin";
		String lastName="can";

		Response response=given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(studentPayload(firstName, lastName))
				.when()
				.post("/student")
				.then().extract().response();

		Assert.assertEquals(response.statusCode(), 201);

	}
	@Test(priority=2)
	public void putStudent() {

		String firstName="henry";
		String lastName="holmes";

		Response response=given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(studentPayload(firstName, lastName))
				.when()
				.put("/student/14")
				.then()
				.extract().response();

		Assert.assertEquals(response.statusCode(), 200);

	}
	@Test(priority=3)
	public void patchstudent() {

		String firstName="abella";
		String lastName="valdes";


		Response response=given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(studentPayload(firstName, lastName))
				.when()
				.patch("/student/12")
				.then()
				.extract().response();

		Assert.assertEquals(response.statusCode(), 200);

	}
	@Test(priority=4)
	public void deleteStudent() {
		Response response=when().delete("/student/2").then().extract().response();

		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test(priority=5)
	public void postSubject() {

		String subjectName = "Operations";

		Response response =given().contentType(ContentType.JSON).accept(ContentType.JSON).body(subjectPayload(subjectName))
				.when()
				.post("/subject")
				.then()
				.extract().response();

		Assert.assertEquals(response.statusCode(), 201);
	}

}
