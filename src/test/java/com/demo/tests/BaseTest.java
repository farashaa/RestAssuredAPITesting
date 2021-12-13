package com.demo.tests;

import static io.restassured.RestAssured.baseURI;

import org.testng.annotations.BeforeTest;

import com.demo.report.ExtentManager;

public class BaseTest {

	@BeforeTest
	public void setUp() {
		baseURI = "https://reqres.in/api";
	}
	public static void addResponseToReport(String response) {
		ExtentManager.logResponse(response);
	}

}
