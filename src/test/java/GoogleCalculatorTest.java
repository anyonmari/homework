import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class GoogleCalculatorTest {
    static AndroidDriver driver;
    @BeforeAll
    static void initDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "android Emulator device");
        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        } catch (MalformedURLException e) {
            throw new RuntimeException (e);
        }
    }
    public static final String result = "com.google.android.calculator:id/eq";
    public static final String resultFinal = "com.google.android.calculator:id/result_final";
    public static final String add = "com.google.android.calculator:id/op_add";
    public static final String subtract = "com.google.android.calculator:id/op_sub";
    public static final String multiply = "com.google.android.calculator:id/op_mul";
    public static final String divide = "com.google.android.calculator:id/op_div";

    @Test
    public void testCalculatorAdd() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(By.id(add)).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_5")).click();
        driver.findElement(By.id(result)).click();
        Assertions.assertEquals("8",driver.findElement(By.id(resultFinal)).getText());
    }

    @Test
    public void testCalculatorSubtract() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
        driver.findElement(By.id(subtract)).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(By.id(result)).click();
        Assertions.assertEquals("5",driver.findElement(By.id(resultFinal)).getText());
    }

    @Test
    public void testCalculatorMultiply() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(By.id(multiply)).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(By.id(result)).click();
        Assertions.assertEquals("9",driver.findElement(By.id(resultFinal)).getText());
    }

    @Test
    public void testCalculatorDivide() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(By.id(divide)).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(By.id(result)).click();
        Assertions.assertEquals("1",driver.findElement(By.id(resultFinal)).getText());
    }
}