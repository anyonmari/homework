package pagesWB;

import org.openqa.selenium.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.time.Duration;

public class MainPage extends BasePage {

    public MainPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(id = "applySearchBtn")
    private WebElement buttonSearch;

    @FindBy(xpath = "//a[@data-wba-header-name='Cart']/span")
    private WebElement buttonGoToBasket;

    @FindBy(css = ".product-card")
    private List<WebElement> allProductCard;

    public void searchForProduct(String productName) {
        searchInput.sendKeys(productName);
        buttonSearch.click();
        this.waitForLoaded();
    }

    public List<Product> addProductsToCartByIndex(int[] indexes) {
        List<Product> products = new ArrayList<>();
        for (int index : indexes) {
            WebElement card = this.getCardByIndex(index);
            products.add(new Product(this.getProductName(card), this.getProductPrice(card)));
            this.addToCart(card);
        }
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    public WebElement getCardByIndex(int index) {
        return allProductCard.get(index);
    }

    public String getProductName(WebElement card) {
        String productBrand = ".product-card__brand";
        String productName = ".product-card__name";
        String brand = card.findElement(By.cssSelector(productBrand)).getText();
        String name = card.findElement(By.cssSelector(productName))
                .getText()
                .replace("/ ", "");
        if (!Objects.equals(brand, "")) {
            name = name.replace("Зонт", "Зонт " + brand);
        }
        return name;
    }

    public int getProductPrice(WebElement card) {
        String productPrice = ".price__lower-price";
        return Integer.parseInt(card.findElement(By.cssSelector(productPrice)).getText()
                .replaceAll(" ", "")
                .replace("₽", ""));
    }

    public void addToCart(WebElement card) {
        WebElement itemButton = card.findElement(By.xpath(".//a[contains(@class, 'product-card__add-basket')]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", itemButton);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(itemButton)).click();
    }


    public CartPage goToCart() {
        buttonGoToBasket.click();
        this.waitForLoaded();
        return new CartPage(driver);
    }
}