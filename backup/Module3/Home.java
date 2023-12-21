package QKART_SANITY_LOGIN.Module1;

import java.util.ArrayList;
import java.util.List;
import java.time.Duration;
import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.FluentWait;

public class Home {
    RemoteWebDriver driver;
    String url = "https://crio-qkart-frontend-qa.vercel.app";

    public Home(RemoteWebDriver driver) {
        this.driver = driver;
    }

    public void navigateToHome() {
        if (!this.driver.getCurrentUrl().equals(this.url)) {
            this.driver.get(this.url);
        }
    }

    public Boolean PerformLogout() throws InterruptedException {
        try {
            // Find and click on the Logout Button
            WebElement logout_button = driver.findElement(By.className("MuiButton-text"));
            logout_button.click();

            // SLEEP_STMT_10: Wait for Logout to complete
            // Wait for Logout to Complete
            Thread.sleep(3000);

            return true;
        } catch (Exception e) {
            // Error while logout
            return false;
        }
    }

    /*
     * Returns Boolean if searching for the given product name occurs without any
     * errors
     */
    public Boolean searchForProduct(String product) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Clear the contents of the search box and Enter the product name in the search
            // box
            WebElement search_box = driver.findElement(By.xpath("//input[@name='search'][1]"));
            search_box.clear();
            search_box.sendKeys(product);
            // System.out.println(search_box.getText());
            // System.out.println("Length is:"+search_box.getText().length());

            // driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[1]")).click();
            
            // WebDriverWait wait = new WebDriverWait(driver,30);
            // wait.until(ExpectedConditions.(By.xpath("//*[@id='root']/div[1]/div/div[1]/div[2]/div/input"),product));

            // WebDriverWait wait = new WebDriverWait(driver, 10);
            // product = product.toUpperCase();
            // wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("css-yg30e6"), product));
            // System.out.println("=============>"+driver.findElement(By.className("css-yg30e6")).getText());





            // wait.until(ExpectedConditions.textToBePresentInElement(element, "text"));

            // WebElement temp = (new WebDriverWait(driver, 10)).until(new ExpectedCondition <Boolean> () {
            //     public Boolean apply(RemoteWebDriver drive) {
            //         return drive.findElement(By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div/input")).getText().equals(product);
            //     }
            // });

            // Boolean res = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            //     @Override
            //     public boolean apply(RemoteWebDriver drive) {
            //         return drive.findElement(By.xpath("//*[@id='root']/div/div/div[1]/div[2]/div/input")).getText().equals(product);
            //     }
            // });
            // // search_box.click();
            // try {
            //     Thread.sleep(2000);
            // } catch (InterruptedException e) {
            //     System.out.println(e);
            // }

            try {
                Wait<RemoteWebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(Exception.class);
            wait.until((Function<WebDriver, Boolean>) RemoteWebDriver -> {
                WebElement element = driver.findElement(By.xpath("//html/body/div/div/div/div[3]/div[1]/div[2]/div/div/div[1]/p[1]"));
                String elementText = element.getText().toLowerCase();
                return elementText.contains(product.toLowerCase());

            }); 
        } catch (Exception e) {
                return isNoResultFound();
            }

            return true;
        }   catch (Exception e) {
            
            e.printStackTrace();
        }
        return false;
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Find all webelements corresponding to the card content section of each of
            // search results
            
            searchResults = driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div"));
            // Thread.sleep(5000);
            return searchResults;
        } catch (Exception e) {
            System.out.println("There were no search results: " + e.getMessage());
            return searchResults;

        }
    }

    /*
     * Returns Boolean based on if the "No products found" text is displayed
     */
    public Boolean isNoResultFound() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 03: MILESTONE 1
            // Check the presence of "No products found" text in the web page. Assign status
            // = true if the element is *displayed* else set status = false
            WebElement temp = driver.findElement(By.xpath("//*[@id='root']/div/div/div[3]/div[1]/div[2]/div/h4"));
            if(temp.getText().equals("No products found")) {
                status=true;
            }
            return status;
        } catch (Exception e) {
            return status;
        }
    }

    /*
     * Return Boolean if add product to cart is successful
     */
    public Boolean addProductToCart(String productName) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */

            List<WebElement> searchResults = this.getSearchResults();
            for(WebElement i:searchResults) {
                if(i.findElement(By.xpath(".//div/div[1]/p[1]")).getText().equals(productName)) {
                    WebElement cartBtn = i.findElement(By.xpath(".//div/div[2]/button"));
                    cartBtn.click();
                    // Thread.sleep(500);
                    return true;
                }
            }
            System.out.println("Unable to find the given product");
            return false;
        } catch (Exception e) {
            System.out.println("Exception while performing add to cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting the status of clicking on the checkout button
     */
    public Boolean clickCheckout() {
        Boolean status = false;
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 05: MILESTONE 4
            // Find and click on the the Checkout button
            List<WebElement> checkout = driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div"));
                                                                        // *[@id='root']/div/div/div[3]/div[2]/div/div[1]
            int size = checkout.size();
            checkout.get(size-1).findElement(By.xpath(".//button")).click();
            status=true;
            return status;
        } catch (Exception e) {
            System.out.println("Exception while clicking on Checkout: " + e.getMessage());
            return status;
        }
    }

    /*
     * Return Boolean denoting the status of change quantity of product in cart
     * operation
     */
    public Boolean changeProductQuantityinCart(String productName, int quantity) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 06: MILESTONE 5

            // Find the item on the cart with the matching productName
            List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div"));
            if(cartProducts.size()<=2) {
                return false;
            }
            // Increment or decrement the quantity of the matching product until the current
            // quantity is reached (Note: Keep a look out when then input quantity is 0,
            // here we need to remove the item completely from the cart)
            for(int i=0;i<cartProducts.size()-2;i++) {
                if(cartProducts.get(i).findElement(By.xpath(".//div/div[2]/div[1]")).getText().equals(productName)) {
                    WebElement currQuantityElement = driver.findElement(By.xpath(".//div/div[2]/div[2]/div[1]/div"));
                    int currQuantity = Integer.parseInt(currQuantityElement.getText());                    
                    if(quantity>currQuantity) {
                        WebElement add = cartProducts.get(i).findElement(By.xpath(".//div/div[2]/div[2]/div[1]/button[2]"));
                        for(int j=currQuantity;j<quantity;j++) {
                            add.click();
                            Thread.sleep(2000);
                        }
                        // return true;
                    } else {
                        WebElement reduce = cartProducts.get(i).findElement(By.xpath(".//div/div[2]/div[2]/div[1]/button[1]"));
                        for(int j=currQuantity;j>quantity;j--) {
                            reduce.click();
                            Thread.sleep(2000);
                        }
                        // return true;
                    }
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println("exception occurred when updating cart: " + e.getMessage());
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            // TODO: CRIO_TASK_MODULE_TEST_AUTOMATION - TEST CASE 07: MILESTONE 6

            // Get all the cart items as an array of webelements
            List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@id='root']/div/div/div[3]/div[2]/div/div"));
            if(cartProducts.size()<=2) {
                return false;
            }
            List<String> products = new ArrayList<>();
            
            // Iterate through expectedCartContents and check if item with matching product
            // name is present in the cart
            for(int i=0;i<cartProducts.size()-2;i++) {
                products.add(cartProducts.get(i).findElement(By.xpath(".//div/div[2]/div")).getText());
            }
            if(products.equals(expectedCartContents)) {
                return true;
            }
            return false;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
