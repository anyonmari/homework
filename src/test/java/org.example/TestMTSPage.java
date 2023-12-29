package org.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class TestMTSPage {

    private WebDriver driver;
    private WebDriverWait wait;


    @BeforeEach
    public void setUp() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        driver.get("https://www.mts.by/");
    }

    @AfterEach
    public void tearDown() {
            Driver.quitDriver();
    }

    @Test
    public void testCheckBlockTitle() {
        WebElement blockTitle = driver.findElement(By.xpath("//h2[contains(.,'Онлайн пополнение без комиссии')]"));
        Assertions.assertEquals("Онлайн пополнение\nбез комиссии", blockTitle.getText());
    }

    @Test
    public void testCheckPaymentSystemLogos() {
        List <WebElement> logos = driver.findElements(By.cssSelector(".pay__partners ul"));
        Assertions.assertTrue(logos.size() > 0, "Элементы не найдены на странице");
    }

    @Test
    public void testCheckServiceDetailsLink() {
        driver.findElement(By.xpath("//a[contains(.,'Подробнее о сервисе')]")).click();
        WebElement detailsLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@itemprop='name'][contains(.,'Порядок оплаты')]")));
        Assertions.assertNotNull(detailsLink);
    }

    @Test
    public void testFillFormAndContinue() {
        WebElement phone = driver.findElement(By.id("connection-phone"));
        phone.clear();
        phone.sendKeys("297777777");
        WebElement sumInput = driver.findElement(By.id("connection-sum"));
        sumInput.sendKeys("10");
        WebElement email = driver.findElement(By.id("connection-email"));
        email.sendKeys("anyonmari@gmail.com");

        WebElement cookieClose = driver.findElement(By.xpath("//button[@class='cookie__close']"));
        if (cookieClose.isDisplayed()) {
            cookieClose.click();
        }

        driver.findElement(By.xpath("//form[@class='pay-form opened']/button[contains(.,'Продолжить')]")).click();

        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div/iframe")));
        Assertions.assertTrue(driver.findElement(By.xpath("//div/iframe")).isDisplayed());
    }
}