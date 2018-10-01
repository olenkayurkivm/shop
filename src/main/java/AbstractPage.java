import com.codeborne.selenide.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.log4j.Logger;
import static com.codeborne.selenide.Selenide.*;



/**
 * Created by OYurkiv on 10/1/2018.
 */
public class AbstractPage {

    final static public String WEB_URL = "https://finance.google.com/finance";
    final static Logger LOG = Logger.getLogger(AbstractPage.class);
    //private WebDriver driver = DriverFactory.getInstance();
    //private final WebDriverWait webDriverWait = new WebDriverWait(driver, 5);



    public AbstractPage()  {
        //PageFactory.initElements((new DefaultElementLocatorFactory(driver)), this);
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriverv2.41.exe");
        Configuration.browser = "chrome";
        open(WEB_URL);
    }

    public void goToFinance(){
        //driver.get("https://finance.google.com/finance");
        open(WEB_URL);
    }
}
