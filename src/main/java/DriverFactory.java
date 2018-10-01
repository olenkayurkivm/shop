import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by OYurkiv on 10/1/2018.
 */
public class DriverFactory {

    public static WebDriver driver;
    public static WebDriver getInstance() {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/chromedriverv2.41.exe");
        if (driver == null) {
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        return driver;
    }

    public static void closeDriver(){
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

}
