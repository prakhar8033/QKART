package QKART_SANITY_LOGIN.Module4;

import java.util.ArrayList;
import java.util.List;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("css-1urhf6j"), "Logout"));

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
            WebElement searchBox = driver.findElement(By.xpath("//input[@name='search'][1]"));
            searchBox.clear();
            searchBox.sendKeys(product);

            Thread.sleep(3000);

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.textToBePresentInElement(
                            driver.findElement(By.xpath("//p[contains(@class,'css-yg30e6')]")), product),
                    ExpectedConditions
                            .visibilityOfElementLocated(By.xpath("//h4[normalize-space()='No products found']"))));

            // wait.until(ExpectedConditions.or(
            // ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(String.format(
            // "//div[@class='MuiCardContent-root
            // css-1qw96cp'][1]/p[contains(normalize-space(),'%s')]",
            // product))),
            // ExpectedConditions
            // .visibilityOfElementLocated(By.xpath("//h4[normalize-space()='No products
            // found']"))));
            return true;
        } catch (Exception e) {
            System.out.println("Error while searching for a product: " + e.getMessage());
            return false;
        }
    }

    /*
     * Returns Array of Web Elements that are search results and return the same
     */
    public List<WebElement> getSearchResults() {
        List<WebElement> searchResults = new ArrayList<WebElement>() {
        };
        try {
            searchResults = driver.findElements(By.className("css-1qw96cp"));
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
            status = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[3]/div[1]/div[2]/div/h4"))
                    .isDisplayed();
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
            /*
             * Iterate through each product on the page to find the WebElement corresponding
             * to the matching productName
             * 
             * Click on the "ADD TO CART" button for that element
             * 
             * Return true if these operations succeeds
             */
            List<WebElement> gridContent = driver.findElements(By.className("css-sycj1h"));
            for (WebElement cell : gridContent) {
                if (productName.contains(cell.findElement(By.className("css-yg30e6")).getText())) {
                    cell.findElement(By.tagName("button")).click();

                    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
                            String.format("//*[@class='MuiBox-root css-1gjj37g']/div[1][contains(text(),\"%s\")]",
                                    productName))));
                    return true;
                }
            }
            System.out.println("Unable to find the given product: " + productName);
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
            WebElement checkoutBtn = driver.findElement(By.className("checkout-btn"));
            checkoutBtn.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.urlToBe("https://crio-qkart-frontend-qa.vercel.app/checkout"));
            status = true;
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

            WebElement ele = driver.findElement(By.xpath(String.format(
                    "//div[contains(@class,'css-1gjj37g') and contains(normalize-space(),'%s')]",
                    productName)));

            WebElement minusBtn = ele.findElement(By.xpath("./div[2]/div[1]/button[1]"));
            WebElement qty = ele.findElement(By.xpath("./div[2]/div[1]/div"));
            int currQty = Integer.parseInt(qty.getText());
            WebElement addBtn = ele.findElement(By.xpath("./div[2]/div[1]/button[2]"));

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            if (currQty < quantity) {
                while (currQty != quantity) {
                    addBtn.click();
                    wait.until(ExpectedConditions.textToBePresentInElement(qty, String.valueOf(currQty + 1)));
                    currQty++;
                }
            } else if (currQty > quantity) {
                while (currQty != quantity) {
                    minusBtn.click();
                    wait.until(ExpectedConditions.textToBePresentInElement(qty, String.valueOf(currQty - 1)));
                    currQty--;
                }
            }

            return true;

            // WebElement cartParent = driver.findElement(By.className("cart"));
            // List<WebElement> cartContents =
            // cartParent.findElements(By.className("css-zgtx0t"));

            // int currentQty;
            // for (WebElement item : cartContents) {
            // if (productName.contains(
            // item.findElement(By.xpath("//*[@class='MuiBox-root
            // css-1gjj37g']/div[1]")).getText())) {
            // currentQty =
            // Integer.valueOf(item.findElement(By.className("css-olyig7")).getText());

            // while (currentQty != quantity) {
            // if (currentQty < quantity) {
            // item.findElements(By.tagName("button")).get(1).click();

            // } else {
            // item.findElements(By.tagName("button")).get(0).click();
            // }

            // synchronized (driver) {
            // driver.wait(2000);
            // }

            // currentQty = Integer
            // .valueOf(item.findElement(By.xpath("//div[@data-testid=\"item-qty\"]")).getText());
            // }

            // return true;
            // }
            // }

            // return false;
        } catch (Exception e) {
            if (quantity == 0)
                return true;
            System.out.println(("exception occurred when updating cart"));
            e.printStackTrace();
            return false;
        }
    }

    /*
     * Return Boolean denoting if the cart contains items as expected
     */
    public Boolean verifyCartContents(List<String> expectedCartContents) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[contains(@class,'css-1q5pok1')]/div/div")));

            Thread.sleep(3000);
            List<WebElement> cartContents = driver.findElements(By.xpath("//div[contains(@class,'css-1gjj37g')]"));
            System.out.println("size of cartContents = " + cartContents.size());
            List<String> actualCartContents = new ArrayList<>();

            for(WebElement cartContent : cartContents) {
                String prodName = cartContent.findElement(By.xpath("./div[1]")).getText();
                actualCartContents.add(prodName);
            }

            for(String actualCartContent : actualCartContents) {
                if(!expectedCartContents.contains(actualCartContent)){
                    return false;
                }
            }

            // ArrayList<String> actualCartContents = new ArrayList<> ();
            // for (WebElement cartItem : cartContents) {
            //     actualCartContents.add(cartItem.findElement(By.className("css-1gjj37g")).getText().split("\n")[0]);
            // }

            // for (String expected : expectedCartContents) {
            //     if (!actualCartContents.contains(expected.trim())) {
            //         return false;
            //     }
            // }

            return true;

        } catch (Exception e) {
            System.out.println("Exception while verifying cart contents: " + e.getMessage());
            return false;
        }
    }
}
