package com.demo.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.demo.report.ExtentManager;

public class Listener implements ITestListener,ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		ExtentManager.setupReport();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentManager.flushReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		ExtentManager.createTest(result.getMethod().getDescription(), "regression", "chrome 96");
		ExtentManager.test.pass(result.getMethod().getDescription() + " is started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		ExtentManager.test.pass(result.getMethod().getDescription() + " is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		ExtentManager.test.fail(result.getMethod().getDescription()+ " is failed");
		ExtentManager.test.fail(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		ExtentManager.test.skip(result.getMethod().getDescription() + " is skipped");
	}

}
