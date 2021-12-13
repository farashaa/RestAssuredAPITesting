package com.demo.tests;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;

import com.demo.report.ExtentManager;

public class BaseLocalTest {
	@BeforeTest
	public  void setUp() {
		baseURI="http://localhost:3000";
	}
	public static void addResponseToReport(String response) {
		ExtentManager.logResponse(response);
	}
}
