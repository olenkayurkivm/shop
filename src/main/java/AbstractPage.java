import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.log4j.Logger;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;


/**
 * Created by OYurkiv on 10/1/2018.
 */
public class AbstractPage {

    final static Logger LOG = Logger.getLogger(AbstractPage.class);

    final static public String WEB_URL = "https://finance.google.com/finance";
    final static public String ESA_URL = "https://getesa.com/reviews/add/fd876f8d76f8d6f8d68fd67";
    final static public String FIND_IP_URL = "https://ru.infobyip.com";
    final static public String MINUTE_MAIL_URL = "https://10minutemail.com";
    final static public String MINUTE_MAIL_URL1 = "https://10minutemail.net";

    final static public String HIDEME_HTTP_URL = "https://hidemyna.me/en/proxy-list/?country=US&type=hs";
    final static public String HIDEME_SOCKS5_URL = "https://hidemyna.me/en/proxy-list/?country=US&type=5";
    final static public String HIDEME_SOCKS4_URL = "https://hidemyna.me/en/proxy-list/?country=US&type=4";
    final static public String SPYSONE_URL = "http://spys.one/free-proxy-list/US/";
    static WebDriver driver;
    //private final WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

    Map<String, String> stateMap = new HashMap<String, String>() {{
        put("AL", "Alabama");
        put("AK", "Alaska");
        put("AZ", "Arizona");
        put("AR", "Arkansas");
        put("CA", "California");
        put("CO", "Colorado");
        put("CT", "Connecticut");
        put("DE", "Delaware");
        put("DC", "District of Columbia");
        put("FL", "Florida");
        put("GA", "Georgia");
        put("HI", "Hawaii");
        put("ID", "Idaho");
        put("IL", "Illinois");
        put("IN", "Indiana");
        put("IA", "Iowa");
        put("KS", "Kansas");
        put("KY", "Kentucky");
        put("LA", "Louisiana");
        put("ME", "Maine");
        put("MD", "Maryland");
        put("MA", "Massachusetts");
        put("MI", "Michigan");
        put("MN", "Minnesota");
        put("MS", "Mississippi");
        put("MO", "Missouri");
        put("MT", "Montana");
        put("NE", "Nebraska");
        put("NV", "Nevada");
        put("NH", "New Hampshire");
        put("NJ", "New Jersey");
        put("NM", "New Mexico");
        put("NY", "New York");
        put("NC", "North Carolina");
        put("ND", "North Dakota");
        put("OH", "Ohio");
        put("OK", "Oklahoma");
        put("OR", "Oregon");
        put("PA", "Pennsylvania");
        put("RI", "Rhode Island");
        put("SC", "South Carolina");
        put("SD", "South Dakota");
        put("TN", "Tennessee");
        put("TX", "Texas");
        put("UT", "Utah");
        put("VT", "Vermont");
        put("VA", "Virginia");
        put("WA", "Washington State");
        put("WV", "West Virginia");
        put("WI", "Wisconsin");
        put("WY", "Wyoming");
        put("PR", "Puerto Rico");
    }};

    public String getStateName(String stateCode) {
        return stateMap.get(stateCode);
    }

    @FindBy(css = "#sa_rounded")
    private WebElement ratePopUp;

    @FindBy(css = "#Overall5")
    private WebElement overall5;

    public AbstractPage() {
        System.out.println("Abstr without");
    }


    public AbstractPage(ProxyObject ip, String browser) throws Exception {
        //PageFactory.initElements((new DefaultElementLocatorFactory(driver)), this);
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriverv2.41.exe");
        //Configuration.browser = "chrome";
        //open(WEB_URL);


        driver = DriverFactory.getInstance(ip, browser);
        //WebDriver driver = new ChromeDriver();
        //driver.get(WEB_URL);
        //driver.get(ESA_URL);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public void parseHideMeAll() throws Exception {
        List<ProxyObject> proxiesToRecord = new ArrayList<>();
        proxiesToRecord.addAll(parseHideMe(HIDEME_SOCKS4_URL));
        proxiesToRecord.addAll(parseHideMe(HIDEME_SOCKS5_URL));
        proxiesToRecord.addAll(parseHideMe(HIDEME_HTTP_URL));
        MyCSVParser.writeProxy(proxiesToRecord);
    }

    public List<ProxyObject> parseHideMe(String typeToParse) throws Exception {
        LOG.info(String.format("Going to : %s", typeToParse));
        driver.get(typeToParse);
        Boolean arrowRightIsPresent = false;
        List<ProxyObject> parsedProxies = new ArrayList<>();
        do {
            Thread.sleep(5000);
            waitUntilVisibilityOfAllElements(15, driver.findElements(By.xpath("//table[@class='proxy__t']//tr/td[@class='tdl']")));
            //List<WebElement> hideIpsWe = driver.findElements(By.xpath("//table[@class='proxy__t']//tr/td[@class='tdl']"));
            //List<WebElement> hidePortsWe = driver.findElements(By.xpath("//table[@class='proxy__t']/tbody/tr/td[2]"));
            List<WebElement> hideIpsWe = driver.findElements(By.xpath("//table[@class='proxy__t']/tbody/tr"));
            for (WebElement hideIpWe : hideIpsWe) {
                ProxyObject proxyObject = new ProxyObject();
                proxyObject.setProxyIp(StringUtils.substringBefore(hideIpWe.getText(), " "));
                proxyObject.setProxyPort(StringUtils.substringAfterLast(StringUtils.substringBefore(hideIpWe.getText(), "United").trim(), " "));
                switch (typeToParse){
                    case HIDEME_HTTP_URL: proxyObject.setProxyType("0"); break;
                    case HIDEME_SOCKS5_URL: proxyObject.setProxyType("5"); break;
                    case HIDEME_SOCKS4_URL: proxyObject.setProxyType("4"); break;
                }
                if(!proxyObject.getProxyIp().startsWith("199")) {
                    parsedProxies.add(proxyObject);
                    System.out.println(proxyObject.getProxyIp() + ',' + proxyObject.getProxyPort() + ',' + proxyObject.getProxyType());
                }
            }
            arrowRightIsPresent = driver.findElements(By.cssSelector(".arrow__right>a")).size() > 0;
            if(arrowRightIsPresent) {
                driver.findElement(By.cssSelector(".arrow__right>a")).click();
            }
        } while (arrowRightIsPresent);

        return parsedProxies;
    }

    public void parseSpysOne() throws Exception {
        LOG.info(String.format("Going to : %s", SPYSONE_URL));
        driver.get(SPYSONE_URL);
        Boolean arrowRightIsPresent = false;
        List<ProxyObject> parsedProxies = new ArrayList<>();
        Thread.sleep(5000);
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#xf5>option[value='1']")));
        driver.findElement(By.cssSelector("#xf5>option[value='1']")).click();
        Thread.sleep(3000);
        waitUntilVisibilityOfAllElements(15, driver.findElements(By.xpath("//table[2]//tr[@class='spy1x' or @class='spy1xx'][position()>1]/td[1]//font[@class='spy14']")));
        List<WebElement> hideIpsWe = driver.findElements(By.xpath("//table[2]//tr[@class='spy1x' or @class='spy1xx'][position()>1]/td[1]//font[@class='spy14']"));
            for (WebElement hideIpWe : hideIpsWe) {
                ProxyObject proxyObject = new ProxyObject();
                proxyObject.setProxyIp(hideIpWe.getText());
//                proxyObject.setProxyPort(StringUtils.substringAfterLast(StringUtils.substringBefore(hidePortWe.getText(), "United").trim(), " "));
//                switch (typeToParse){
//                    case HIDEME_HTTP_URL: proxyObject.setProxyType("0"); break;
//                    case HIDEME_SOCKS5_URL: proxyObject.setProxyType("5"); break;
//                    case HIDEME_SOCKS4_URL: proxyObject.setProxyType("4"); break;
//                }
                    parsedProxies.add(proxyObject);
                    //System.out.println(proxyObject.getProxyIp() + ',' + proxyObject.getProxyPort() + ',' + proxyObject.getProxyType());
                    System.out.println(proxyObject.getProxyIp());
            }


        //return parsedProxies;
    }


    public void rate1() throws Exception {
        LOG.info(String.format("Going to : %s", ESA_URL));
        driver.get(ESA_URL);
        Thread.sleep(7000);
        waitUntilVisibilityOf(15, driver.findElement(By.cssSelector("#sa_rounded")));
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#Overall5")));
        LOG.info("Clicking to make 1-st rate");
        driver.findElement(By.cssSelector("#Overall5")).click();
        //overall5.click();
    }

    public void rate1simple() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#Overall5")));
        driver.findElement(By.cssSelector("#Overall5")).click();
    }

    public void rate2() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#Recommend5")));
        LOG.info("Clicking to make 2-nd rate");
        driver.findElement(By.cssSelector("#Recommend5")).click();
    }

    public void rate3() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#Rebuy5")));
        LOG.info("Clicking to make 3-rd rate");
        driver.findElement(By.cssSelector("#Rebuy5")).click();
    }

    public void rate4() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#PriceSatisfaction5")));
        LOG.info("Clicking to make 4-th rate");
        driver.findElement(By.cssSelector("#PriceSatisfaction5")).click();
    }

    public void fillRatingForm(String email) throws Exception {
        rate1();
        rate2();
        rate3();
        rate4();
        leaveComment(MyCSVParser.parseCSVToComments());
        chooseNo();
        User targetUser = MyCSVParser.parseCSVToUsers();
        enterFullName(String.format("%s %s", targetUser.getFirstName(), targetUser.getLastName()));
        enterEmail(email);
        submitForm();
    }

    public void leaveComment(String comment) throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#comments")));
        LOG.info(String.format("Entering comment : %s", comment));
        driver.findElement(By.cssSelector("#comments")).sendKeys(comment);
    }

    public void chooseNo() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector(".sa_followup[value='no']")));
        LOG.info("Choosing [NO]");
        driver.findElement(By.cssSelector(".sa_followup[value='no']")).click();
    }

    public void enterFullName(String fullName) throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#sa_name")));
        LOG.warn(String.format("Entering full name : %s", fullName));
        driver.findElement(By.cssSelector("#sa_name")).sendKeys(fullName);
    }

    public void enterEmail(String email) throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#sa_email")));
        LOG.info(String.format("Entering email : %s", email));
        driver.findElement(By.cssSelector("#sa_email")).sendKeys(email);
    }

    public void submitForm() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#shopper_submit")));
        LOG.warn("Submitting rating form");
        driver.findElement(By.cssSelector("#shopper_submit")).click();
        Thread.sleep(2000);
    }

    //--------------Customer Satisfaction Survey---------------------------------
    public void rateS1() throws Exception {
        waitUntilElementToBeClickableFluent(20, driver.findElement(By.cssSelector("#Overall_5")));
        LOG.info("Clicking to confirm 1-st rate");
        driver.findElement(By.cssSelector("#Overall_5")).click();
        Thread.sleep(1000);
    }

    public void rateS2() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#Recommend_5")));
        LOG.info("Clicking to confirm 2-nd rate");
        //driver.findElement(By.cssSelector("#Recommend_5")).click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#Recommend_5")));
        Thread.sleep(1000);
    }

    public void rateS3() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#Rebuy_5")));
        LOG.info("Clicking to confirm 3-rd rate");
        //driver.findElement(By.cssSelector("#Rebuy_5")).click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#Rebuy_5")));
        Thread.sleep(1000);
    }

    public void rateS4() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#PriceSatisfaction_5")));
        LOG.info("Clicking to confirm 4-th rate");
        //driver.findElement(By.cssSelector("#PriceSatisfaction_5")).click();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#PriceSatisfaction_5")));
        Thread.sleep(1000);
    }

    public void confirmRatings() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#btn_confirm")));
        LOG.warn("Clicking on [confirm ratings] button");
        driver.findElement(By.cssSelector("#btn_confirm")).click();
        Thread.sleep(3000);
    }

    public void rateS5() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#Product_5")));
        LOG.info("Clicking make 5-th rate");
        driver.findElement(By.cssSelector("#Product_5")).click();
        Thread.sleep(1000);
    }

    public void writeState(String state) throws Exception {
        //String stateSelector = String.format("#state>option[value='%s']", state );
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#state")));
        LOG.info(String.format("Selecting state : %s (%s)", stateMap.get(state), state));
        Select states = new Select(driver.findElement(By.cssSelector("#state")));
        //states.selectByValue(state);
        try {
            states.selectByValue(state);
        } catch (NoSuchElementException ex) {
            //states.selectByIndex(5);
            states.selectByIndex((int) (Math.random() * 49 + 1));
        }
    }

    public void confirmFinal() throws Exception {
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#submitSurvey")));
        LOG.info("Clicking on final [confirm] button");
        driver.findElement(By.cssSelector("#submitSurvey")).click();
        Thread.sleep(10000);
    }

    public void fillSatisfactionSurvey(String state) throws Exception {
        rateS1();
        rateS2();
        rateS3();
        rateS4();
        confirmRatings();
        rateS5();
        writeState(state);
        confirmFinal();
        Thread.sleep(5000);
    }

    //---------------------Find Sate for Proxy-----------------------------------------------------------------

    public String findStateForProxy(String ip) throws Exception {
        LOG.info(String.format("Going to : %s", FIND_IP_URL));
        driver.get(FIND_IP_URL);
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("input[name='ip']")));
        LOG.info(String.format("Entering IP : %s", ip));
        driver.findElement(By.cssSelector("input[name='ip']")).sendKeys(ip);
        LOG.info("Clicking on [OK] button");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(5000);
        LOG.info("Getting US state for Entered IP");
        if (!driver.findElement(By.xpath("//table[@class='results wide home']//tr[2]/td[2]")).getText().contains("US")) {
            LOG.warn("IP Country is not USA!");
            throw new Exception("We are not posting reviews for Non-US IP's!!!");
        }
        return driver.findElement(By.xpath("//table[@class='results wide home']//tr[3]/td[2]")).getText();
    }

    //--------------10 Minute email------------------------------------------------------------------

    public String registerMinuteEmail() throws Exception {
//        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
//        driver.get(MINUTE_MAIL_URL);
//        waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#mailAddress")));
//        LOG.info("Getting registered 10 minute email");
//        return driver.findElement(By.cssSelector("#mailAddress")).getAttribute("value");

        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL1));
        driver.get(MINUTE_MAIL_URL1);
        waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#fe_text")));
        LOG.info("Getting registered 10 minute email");
        return driver.findElement(By.cssSelector("#fe_text")).getAttribute("value");
    }

    public void confirmRateUsingMinuteEmail() throws Exception {
//        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
//        driver.get(MINUTE_MAIL_URL);
//        Thread.sleep(6*60*1000);
//        //driver.navigate().refresh();
//        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#messagesList >h3")));
//        LOG.info("Clicking to expand received email");
//        driver.findElement(By.cssSelector("#messagesList >h3")).click();
//        Thread.sleep(3000);
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        //LOG.info("Clicking on confirmation link in received email");
//        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        //waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        LOG.info("Clicking on confirmation link in received email");
//        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")).click();
//        Thread.sleep(10000);

        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL1));
        driver.get(MINUTE_MAIL_URL1);
        Thread.sleep(6 * 60 * 1000);
        //driver.findElement(By.xpath("//a[@href='more.html']")).click(); //Give me 10 more minutes
        //driver.navigate().refresh();
        Boolean destructedIsPresent = driver.findElements(By.xpath("//p/strong[contains(text(), 'self-destructed')]")).size() > 0;
        if (destructedIsPresent) {
            waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[@href='recover.html']")));
            LOG.info("Recovering destructed email");
            driver.findElement(By.xpath("//a[@href='recover.html']")).click();
        }
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//a[contains(text(),'getesa.com')]")));
        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[contains(text(),'getesa.com')]")));
        LOG.info("Clicking to expand received email");
        //driver.findElement(By.xpath("//a[contains(text(),'getesa.com')]")).click();
        new WebDriverWait(driver, 5)
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    d.findElement(By.xpath("//a[contains(text(),'getesa.com')]")).click();
                    return true;
                });
        Thread.sleep(4000);
        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")));
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")));
        //LOG.info("Clicking on confirmation link in received email");
        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        //waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        LOG.info("Clicking on confirmation link in received email");
        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        LOG.warn(driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")).getText());
        driver.get(driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")).getAttribute("href"));
        //driver.findElement(By.xpath("//a[contains(text(),'Activate')]")).click();
        Thread.sleep(7000);
    }



    public void testMinuteEmail() throws Exception {
//        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
//        driver.get(MINUTE_MAIL_URL);
//        Thread.sleep(60*1000);
//        //driver.navigate().refresh();
//        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#messagesList >h3")));
//        LOG.info("Clicking to expand received email");
//        driver.findElement(By.cssSelector("#messagesList >h3")).click();
//        Thread.sleep(3000);
//        JavascriptExecutor jse = (JavascriptExecutor)driver;
//        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector(".message a:not([href='#'])")));
//        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        //waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        LOG.info("Clicking on confirmation link in received email");
//        jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".message a:not([href='#'])")));
////        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector(".message a:not([href='#'])")));
////        driver.findElement(By.cssSelector(".message a:not([href='#'])")).click();
//        Thread.sleep(3000);
//        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("img[alt='Logo']")));
//        driver.findElement(By.cssSelector("img[alt='Logo']")).click();


        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL1));
        driver.get(MINUTE_MAIL_URL1);
        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[@href='more.html']")));
        driver.findElement(By.xpath("//a[@href='more.html']")).click();
        Thread.sleep(2 * 60 * 1000);
        //driver.navigate().refresh();
        Boolean destructedIsPresent = driver.findElements(By.xpath("//p/strong[contains(text(), 'self-destructed')]")).size() > 0;
        if (destructedIsPresent) {
            waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[@href='recover.html']")));
            driver.findElement(By.xpath("//a[@href='recover.html']")).click();
            Thread.sleep(3000);
        }
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//a[contains(text(),'Welcome')]")));
        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[contains(text(),'Welcome')]")));
        LOG.info("Clicking to expand received email");
        //driver.findElement(By.xpath("//a[contains(text(),'No Subject')]")).click();
        new WebDriverWait(driver, 5)
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    d.findElement(By.xpath("//a[contains(text(),'Welcome')]")).click();
                    return true;
                });
        Thread.sleep(7000);
//        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")));
//        //LOG.info("Clicking on confirmation link in received email");
//        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        //waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        LOG.info("Clicking on confirmation link in received email :");
//        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
//        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")));
//        LOG.info(driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")).getAttribute("href"));
//        driver.get(driver.findElement(By.xpath("//a[contains(text(),'shopperapproved')]")).getAttribute("href"));
//        //driver.findElement(By.xpath("//a[contains(text(),'Activate')]")).click();
//        Thread.sleep(7000);
    }


    protected void waitUntilAttributeContains(int timeOutInSeconds, WebElement element, String attribute, String value) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.attributeContains(element, attribute, value));
    }

    protected void waitUntilAttributeToBe(int timeOutInSeconds, WebElement element, String attribute, String value) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.attributeToBe(element, attribute, value));
    }

    protected WebElement waitUntilElementToBeClickable(int timeOutInSeconds, WebElement element) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    protected WebElement waitUntilElementToBeClickableFluent(int timeOutInSeconds, WebElement element) {
        (new WebDriverWait(driver, timeOutInSeconds)).ignoring(NoSuchElementException.class).until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }

    protected WebElement waitUntilElementToBeSelected(int timeOutInSeconds, WebElement element) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.elementToBeSelected(element));
        return element;
    }

    protected WebElement waitUntilVisibilityOf(int timeOutInSeconds, WebElement element) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    protected void waitUntilInvisibilityOf(int timeOutInSeconds, WebElement element) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.invisibilityOf(element));
    }

    protected List<WebElement> waitUntilVisibilityOfAllElements(int timeOutInSeconds, List<WebElement> elements) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }

    protected List<WebElement> waitUntilInvisibilityOfAllElements(int timeOutInSeconds, List<WebElement> elements) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.invisibilityOfAllElements(elements));
        return elements;
    }

    protected void waitUntilInvisibilityOfElementLocated(int timeOutInSeconds, By locator) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void waitUntilNumberOfWindowsToBe(int timeOutInSeconds, int expectedNumberOfWindows) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
    }

    protected void waitUntilPresenceOfAllElementsLocatedBy(int timeOutInSeconds, By locator) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected void waitUntilPresenceOfElementLocatedBy(int timeOutInSeconds, By locator) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void waitUntilUrlContains(int timeOutInSeconds, String fraction) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.urlContains(fraction));
    }

    protected void waitUntilUrlToBe(int timeOutInSeconds, String url) {
        (new WebDriverWait(driver, timeOutInSeconds)).until(ExpectedConditions.urlToBe(url));
    }

    public void goToFinance() {
        //driver.get("https://finance.google.com/finance");
        open(WEB_URL);
    }
}
