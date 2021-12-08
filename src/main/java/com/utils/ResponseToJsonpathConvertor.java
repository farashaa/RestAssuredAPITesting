package com.utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class ResponseToJsonpathConvertor {
	public static JsonPath jsonConvertor(Response response) {
		return new JsonPath(response.asString());
	}
}
