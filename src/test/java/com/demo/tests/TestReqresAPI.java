package com.demo.tests;
import static com.resources.Payload.UserPayload;
import static com.utils.ResponseToJsonpathConvertor.jsonConvertor;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TestReqresAPI extends BaseTest{


	@Test(priority=6,description="Test get user by id")
	public static void testUsersById()  {
		String first_name="Janet";
		String last_name="Weaver";
		String avatar="https://reqres.in/img/faces/2-image.jpg";

		Response response=given().when().get("/users/2").then().extract().response();

		addResponseToReport(response.asPrettyString());

		JsonPath jsonPath =jsonConvertor(response);
		Assert.assertEquals(jsonPath.getInt("data.id"), 2);
		Assert.assertEquals(jsonPath.getString("data.email"),"janet.weaver@reqres.in");
		Assert.assertEquals(jsonPath.getString("data.first_name"),first_name );
		Assert.assertEquals(jsonPath.getString("data.last_name"), last_name);
		Assert.assertEquals(jsonPath.getString("data.avatar"), avatar);
		Assert.assertTrue(jsonPath.getString("support.url").contains("#support-heading"));
		Assert.assertTrue(true,jsonPath.getString("support.text"));
	}
	@Test(priority=7, description="Test get list of users in a apge")
	public void testListUsers() {
		int page=2;
		int per_page=6;
		int total=12;
		int total_pages=2;
		String email= "michael.lawson@reqres.in";

		Response response=given().queryParam("page",2).when().get("/users").then().extract().response();

		addResponseToReport(response.asPrettyString());

		JsonPath jsonPath = jsonConvertor(response);
		ArrayList<Integer> list = new ArrayList<>(Arrays.asList(7, 8, 9, 10, 11, 12));
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
	@Test(priority=8 , description="Test create a user using post method")
	public void testCreateUser()  {

		String name="ainam";
		String job="developer";

		Response response=given().body(UserPayload(name, job).toString()).when().post("/users").then().extract().response();

		addResponseToReport(response.asPrettyString());

		JsonPath jsonPath = jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 201);
		Assert.assertNotNull(jsonPath.getString("id"));
		Assert.assertNotNull(jsonPath.getString("createdAt"));
	}
	@Test(priority=9, description= "Test update a user using put method")
	public static void testPutUser()  {
		String name="ruth";
		String job="leader";

		Response response=given().body(UserPayload(name, job).toString()).when().put("/users/2").then().extract().response();

		addResponseToReport(response.asPrettyString());

		JsonPath jsonPath =jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getString("updatedAt"));
	}
	@Test(priority=10,  description= "Test update a user using patch method")
	public static void testPatchUser() {
		String name="alisha";
		String job="tester";

		Response response=given().body(UserPayload(name, job).toString()).when().patch("/users/2").then().extract().response();

		addResponseToReport(response.asPrettyString());

		JsonPath jsonPath = jsonConvertor(response);
		Assert.assertEquals(response.statusCode(), 200);
		Assert.assertNotNull(jsonPath.getString("updatedAt"));

	}
	@Test(priority=11, description = "Test delete user")
	public static void testDeleteUser() {

		Response response= given().when().delete("/users/2").then().extract().response();

		Assert.assertEquals(response.statusCode(), 204);
	}


}
