package com.demo.tests;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;

public class BaseLocalTest {
	@BeforeTest
	public  void setUp() {
		baseURI="http://localhost:3000";
	}
}
