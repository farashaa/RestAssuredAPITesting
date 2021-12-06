



import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class FirstDemo {
	@BeforeTest
	public void setUp() {
		baseURI = "https://reqres.in/api";
	}
	@Test
	public static void testUsersById() {
		Response response=get("https://reqres.in/api/users/2");
		System.out.println(response.asPrettyString());
	}

	@Test(priority=0)
	public void testCreateUser() {
		Map<String, Object> map= new HashMap<>();
		map.put("name","morpheus");
		map.put("job", "leader");
		System.out.println(map.toString());
		//		JSONObject req=new JSONObject(map);
		//		req.put("name","morpheus");
		//		req.put("job","leader");
		//		System.out.println(req.toJSONString());
		given().
		body(map.toString()).
		when().
		post("/users").
		then().
		statusCode(201).log().all();
	}
	//@Test
	public static void testPut() {
		Map<String, Object> map= new HashMap<>();
		map.put("name","morpheus");
		map.put("job", "leader");
		System.out.println(map.toString());
		given().
		body(map.toString()).
		when().
		put("/users/2").
		then().
		statusCode(200).log().all();
	}
	//@Test
	public static void testPatch() {
		Map<String, Object> map= new HashMap<>();
		map.put("name","morpheus");
		map.put("job", "leader");
		System.out.println(map.toString());
		given().
		body(map.toString()).
		when().
		patch("/users/2").
		then().
		statusCode(200).log().all();
	}
	//@Test
	public static void testDelete() {
		baseURI = "https://reqres.in/api";
		when().
		delete("/users/2").
		then().
		statusCode(204).log().all();
	}
}
