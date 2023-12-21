package QKART_TESTNG;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class ListenerClass implements ITestListener {

    QKART_Tests obj = new QKART_Tests();

    public void onTestStart(ITestResult result) {
        obj.takeScreenshot(obj.driver,  result.getName() + " start","screenshot");
        System.out.println("Test Started : " + result.getName() + " Taking Screenshot !");
    }

    public void onTestFinish(ITestResult result) {
        obj.takeScreenshot(obj.driver, result.getName() + " start","screenshot");
        System.out.println("Test Finished : " + result.getName() + " Taking Screenshot !");
    }

    public void onTestFailure(ITestResult result) {
        obj.takeScreenshot(obj.driver, result.getName() + " start","screenshot");
        System.out.println("Test Failed : " + result.getName() + " Taking Screenshot !");
    }
}
