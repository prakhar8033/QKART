package QKART_SANITY_LOGIN.Module1;

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
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Click on the "Add new address" button, enter the addressString in the address
             * text box and click on the "ADD" button to save the address
             */
            Thread.sleep(500);
            if(addresString.length()<20) {
                System.out.println("Address should be greater than 20 characters");
                return false;
            }
            driver.findElement(By.xpath("//*[@id='add-new-btn']")).click();
            WebElement box = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div[1]/div/textarea[1]"));
            box.sendKeys(addresString);
            WebElement addBtn = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[2]/div[2]/button[1]"));
            if(addBtn.isEnabled()) {
                addBtn.click();
                Thread.sleep(500);
                return true;
            }
            return false;
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
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through all the address boxes to find the address box with matching
             * text, addressToSelect and click on it
             */
            // "//*[@id="root"]/div/div[2]/div[1]/div/div[1]/div"
            // ".//div[1]/p"
            // ".//div[1]/span/input"

            List<WebElement> addresses = driver.findElements(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/div[1]/div"));
            for(WebElement i:addresses) {
                if(i.findElement(By.xpath(".//div[1]/p")).getText().equals(addressToSelect)) {
                    i.findElement(By.xpath(".//div[1]/span/input")).click();
                    Thread.sleep(300);
                    return true;
                }
            }
            System.out.println("Unable to find the given address");
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
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find the "PLACE ORDER" button and click on it
            WebElement orderBtn = driver.findElement(By.xpath("//*[@id='root']/div/div[2]/div[1]/div/button[2]"));
            if(orderBtn.isEnabled()) {
                Thread.sleep(1000);
                orderBtn.click();
                return true;
            }
            return false;

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
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 08: MILESTONE 7
            Thread.sleep(300);
            WebElement temp = driver.findElement(By.xpath("//html/body/div/div[2]/div/div/div/div/div/div"));
            if(temp.isDisplayed() && temp.getText().equals("You do not have enough balance in your wallet for this purchase")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            System.out.println("Exception while verifying insufficient balance message: " + e.getMessage());
            return false;
        }
    }
}
