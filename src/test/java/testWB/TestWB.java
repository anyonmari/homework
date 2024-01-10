package testWB;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pagesWB.CartPage;
import pagesWB.MainPage;
import pagesWB.Product;
import java.util.List;

public class TestWB {
    private WebDriver driver;
    private MainPage mainPage;
    private CartPage cartPage;

    @BeforeEach
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        cartPage = new CartPage(driver);
        driver.get("https://www.wildberries.ru/");
        mainPage.waitForLoaded();
    }

    @AfterEach
    public void browserClose() {
        driver.quit();
    }

    @Test
    public void testWildberries() {
        mainPage.searchForProduct("Зонт");
        List<Product> products = mainPage.addProductsToCartByIndex(new int [] {1, 2});
        mainPage.goToCart();
        List<Product> cartProducts = cartPage.getAllProductsData();

        Assertions.assertEquals(products.size(),cartProducts.size(),"Quantity of selected products is not equal to the quantity of products in the basket");
        Assertions.assertEquals(cartPage.getTotalPrice(), Product.getTotalPrice(products),"Total price of products is not equal");

        for (int i = 0; i < cartProducts.size(); i++) {
            Product product = products.get(i);
            Product cartProduct = cartProducts.get(i);
            Assertions.assertEquals(product.getName(),cartProduct.getName(),"Different item names");
            Assertions.assertEquals(product.getPrice(),cartProduct.getPrice(), "Price of products is not equal");
        }
    }
}
