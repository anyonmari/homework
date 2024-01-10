package pagesWB;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    private Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
        driver.manage().window().maximize();
    }

    public Actions getActions(){
        if (actions==null) {
            actions = new Actions(driver);
        }
        return actions;
    }

    @FindBy(css = "#app > .general-preloader")
    private WebElement preloader;

    public void waitForLoaded() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOf(preloader));
        } catch (Exception ignored) {}
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOf(preloader));
    }
}
