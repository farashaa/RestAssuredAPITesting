package com.demo.tests;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestReqresAPI {
	@BeforeTest
	public void setUp() {
		baseURI = "https://reqres.in/api";
	}
	//@Test
	public static void testUsersById() {
		String first_name="Janet";
		String last_name="Weaver";
		String avatar="https://reqres.in/img/faces/2-image.jpg";

		Response response=given().when().get("/users/2").then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		Assert.assertEquals(jsonPath.getInt("data.id"), 2);
		Assert.assertEquals(jsonPath.getString("data.email"),"janet.weaver@reqres.in");
		Assert.assertEquals(jsonPath.getString("data.first_name"),first_name );
		Assert.assertEquals(jsonPath.getString("data.last_name"), last_name);
		Assert.assertEquals(jsonPath.getString("data.avatar"), avatar);
		Assert.assertTrue(jsonPath.getString("support.url").contains("#support-heading"));
		Assert.assertTrue(true,jsonPath.getString("support.text"));
	}
	@Test
	public void testListUsers() {
		int page=2;
		int per_page=6;
		int total=12;
		int total_pages=2;
		String email= "michael.lawson@reqres.in";


		Response response=given().queryParam("page",2).when().get("/users").then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12));
		
		
		for(Object id:list) {
			System.out.println(id);
		}
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertEquals(jsonPath.getInt("page"), page);
		Assert.assertEquals(jsonPath.getInt("per_page"), per_page);
		Assert.assertEquals(jsonPath.getInt("total"), total);
		Assert.assertEquals(jsonPath.getInt("total_pages"), total_pages);
		Assert.assertNotNull(jsonPath.getList("data").size());
		Assert.assertEquals(jsonPath.getString("data[0].email"),email );
		Assert.assertNotNull(jsonPath.getList("data.id"));
		Assert.assertEquals(jsonPath.getList("data.id"), list);

	}
	//@Test
	public void testCreateUser() {
		Map<String, Object> map= new HashMap<>();
		map.put("name","ainam");
		map.put("job", "developer");
		String name="ainam";
		String job="developer";

		Response response=given().body(map.toString()).when().post("/users").then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertNotNull(jsonPath.getString("id"));
		Assert.assertNotNull(jsonPath.getString("createdAt"));
	}
	//@Test
	public static void testPutUser() {
		Map<String, Object> map= new HashMap<>();
		map.put("name","ruth");
		map.put("job", "leader");
		System.out.println(map.toString());

		Response response=given().body(map.toString()).when().put("/users/2").then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getString("updatedAt"));
	}
	//@Test
	public static void testPatchUser() {
		Map<String, Object> map= new HashMap<>();
		map.put("name","alisha");
		map.put("job", "tester");

		Response response=given().body(map.toString()).when().patch("/users/2").then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getString("updatedAt"));

	}
	//@Test
	public static void testDelete() {
		Response response= given().when().delete("/users/2").then().extract().response();

		JsonPath jsonPath = new JsonPath(response.asString());
		Assert.assertEquals(response.statusCode(), 204);
	}
}
