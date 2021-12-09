package com.demo.tests;

import static io.restassured.RestAssured.baseURI;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseTest {
	static ExtentReports extent;
	static ExtentTest test;
	@BeforeTest
	public void setUp() {
		baseURI = "https://reqres.in/api";
	}
	@BeforeSuite
	public static void setupReport() {
	  extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("report/index.html");
        spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Automation test report");
		spark.config().setReportName("Extent Reports");
		extent.attachReporter(spark);
	}
	@AfterSuite
	public static void flushReport() {
		extent.flush();
	}
	public static void createTest(String testName, String testType, String testDevice) {
	    test= extent.createTest(testName).assignCategory(testType).assignDevice(testDevice);
	}
	
}
