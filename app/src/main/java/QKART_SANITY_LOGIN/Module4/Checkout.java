package QKART_SANITY_LOGIN.Module4;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Checkout {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app/checkout";

    public Checkout(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToCheckout() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    /*
     * Return Boolean denoting the status of adding a new address
     */
    public Boolean addNewAddress(String addresString) {
        try {
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */
            WebElement addNewAddressButton = driver.findElement(By.id("add-new-btn"));
            addNewAddressButton.click();

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea[@aria-invalid=\"false\"]")));

            WebElement AddressBox = driver.findElement(By.xpath("//textarea[@aria-invalid=\"false\"]"));
            AddressBox.clear();
            AddressBox.sendKeys(addresString);

            WebElement addBtn = driver.findElement(By.xpath("//div[contains(@class,'css-19a1170')]//button[1]"));
            addBtn.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(
                    "//span[contains(@class,\"css-z2fzuu\")]//following-sibling::p[contains(normalize-space(),\"%s\")]",
                    addresString))));

            // wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(String.format(
            // "//*[@class='MuiTypography-root MuiTypography-body1 css-yg30e6' and
            // text()='%s']", addresString))));
            return true;
        } catch (Exception e) {
            System.out.println("Exception occurred while entering address: " + e.getMessage());
            return false;

        }
    }

    /*
     * Return Boolean denoting the status of selecting an available address
     */
    public Boolean selectAddress(String addressToSelect) {
        try {
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            WebElement addr = driver.findElement(By.xpath(String.format(
                    "//span[contains(@class,'css-z2fzuu')]//following-sibling::p[contains(normalize-space(),'%s')]",
                    addressToSelect)));
            addr.click();
            if (addr.findElement(By.xpath("./../span/input")).isEnabled()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception Occurred while selecting the given address: " + e.getMessage());
            return false;
        }

    }

    /*
     * Return Boolean denoting the status of place order action
     */
    public Boolean placeOrder() {
        try {
            WebElement orderBtn = driver.findElement(By.xpath("//button[contains(@class,'css-177pwqq')]"));
            orderBtn.click();

            try {
                
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        } catch (Exception e) {
            System.out.println("Exception while clicking on PLACE ORDER: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the insufficient balance message is displayed
     */
    public Boolean verifyInsufficientBalanceMessage() {
        try {
            WebElement alertMessage = driver.findElement(By.id("notistack-snackbar"));
            if (alertMessage.isDisplayed()) {
                if (alertMessage.getText().equals("You do not have enough balance in your wallet for this purchase")) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
