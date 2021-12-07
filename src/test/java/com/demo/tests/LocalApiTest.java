package com.demo.tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class LocalApiTest {
	@BeforeTest
	public  void setUp() {
		baseURI="http://localhost:3000";
	}
	@Test(priority=0)
	public  void getStudent() {
		given().when().get("/student").then().statusCode(200).log().all();
		}
	@Test(priority=1)
	public void postStudent() {

		JSONObject req=new JSONObject();
		req.put("firstName","henry");
		req.put("lastName","baskerville");
		req.put("subjectId",2);
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		post("/student").
		then().
		statusCode(201).log().all();
	}
	@Test(priority=2)
	public void putStudent() {

		JSONObject req=new JSONObject();
		req.put("firstName","henry");
		req.put("lastName","holmes");
		req.put("subjectId",2);
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		put("/student/5").
		then().
		statusCode(200).log().all();

	}
	@Test(priority=3)
	public void patchstudent() {
		JSONObject req=new JSONObject();
		req.put("firstName","abella");
		req.put("lastName","valdes");
		req.put("subjectId",2);
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		patch("/student/3").
		then().
		statusCode(200).log().all();
	}
	@Test(priority=4)
	public void deleteStudent() {
		when().
		delete("/student/3").
		then().
		statusCode(200).log().all();
	}
	@Test(priority=5)
	public void postSubject() {
		JSONObject req=new JSONObject();
		req.put("name","operations");
		System.out.println(req.toJSONString());
		given().contentType(ContentType.JSON).accept(ContentType.JSON).
		body(req.toJSONString()).
		when().
		post("/subject").
		then().
		statusCode(201).log().all();
	}

}
