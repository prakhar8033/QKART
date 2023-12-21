package QKART_SANITY_LOGIN.Module4;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/login";

    public Login(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToLoginPage() {
        try {
            if (!this.driver.getCurrentUrl().equals(this.url)) {
                this.driver.get(this.url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean PerformLogin(String Username, String Password) throws InterruptedException {
        try {
            WebElement username_txt_box = this.driver.findElement(By.id("username"));

            // Enter the username
            username_txt_box.sendKeys(Username);

            // Find the password Text Box
            WebElement password_txt_box = this.driver.findElement(By.id("password"));

            // Enter the password
            password_txt_box.sendKeys(Password);

            // Find the Login Button
            WebElement login_button = driver.findElement(By.className("button"));

            // Click the login Button
            login_button.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions
                    .or(ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/")));

            return this.VerifyUserLoggedIn(Username);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public Boolean VerifyUserLoggedIn(String Username) {
        try {
            // Find the username label (present on the top right of the page)
            WebElement username_label = this.driver.findElement(By.className("username-text"));
            return username_label.getText().equals(Username);
        } catch (Exception e) {
            return false;
        }

    }

}
