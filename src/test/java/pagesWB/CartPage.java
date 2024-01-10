package pagesWB;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final String itemName = ".good-info__good-name";
    private final String itemPrice = ".list-item__price-new";

    @FindBy(css = ".list-item")
    private List<WebElement> allListItems;
    @FindBy(css = ".b-top__total [data-link]")
    private WebElement totalPrice;

    public List<Product> getAllProductsData() {
        List<WebElement> cards = allListItems;
        List<Product> products = new ArrayList<>();
        for (WebElement card: cards) {
            products.add(new Product(this.getProductName(card), this.getProductPrice(card)));
        }
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    public String getProductName(WebElement card){
        return card.findElement(By.cssSelector(itemName))
                .getText()
                .replaceAll("/"," ");
    }

    public Integer getProductPrice(WebElement card){
        return Integer.parseInt(card.findElement(By.cssSelector(itemPrice)).getText()
                .replaceAll(" ", "")
                .replace("₽", ""));
    }

    public Integer getTotalPrice() {
        return Integer.parseInt(totalPrice.getText()
                .replaceAll(" ", "")
                .replace("₽", ""));
    }
}
