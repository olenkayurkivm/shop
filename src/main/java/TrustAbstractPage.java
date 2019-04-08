import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.codeborne.selenide.Selenide.open;

/**
 * Created by OYurkiv on 2/13/2019.
 */
public class TrustAbstractPage {

    final static Logger LOG = Logger.getLogger(TrustAbstractPage.class);

    final static public String ESA_URL = "https://www.trustpilot.com/evaluate/getesa.com";
    final static public String TP_URL = "https://www.trustpilot.com/";
    final static public String FIND_IP_URL = "https://ru.infobyip.com";
    final static public String MINUTE_MAIL_URL = "https://10minutemail.net/";
    static WebDriver driver;
    //private final WebDriverWait webDriverWait = new WebDriverWait(driver, 5);


    @FindBy(css = "#sa_rounded")
    private WebElement ratePopUp;

    @FindBy(css = "#Overall5")
    private WebElement overall5;

    public TrustAbstractPage()  {
        System.out.println("Abstr without");
    }


    public TrustAbstractPage(ProxyObject ip, String browser) throws Exception {
        //PageFactory.initElements((new DefaultElementLocatorFactory(driver)), this);
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriverv2.41.exe");
        //Configuration.browser = "chrome";
        //open(WEB_URL);


        driver = DriverFactory.getInstance(ip, browser);
        //WebDriver driver = new ChromeDriver();
        //driver.get(WEB_URL);
        //driver.get(ESA_URL);
    }

    public static WebDriver getDriver(){
        return driver;
    }

    public void getUserAgent()throws  Exception{
       driver.get("https://www.whatismybrowser.com/detect/what-is-my-user-agent");
    }

    public void goToTrustPilot()throws  Exception{
        LOG.info(String.format("Going to : %s", TP_URL));
        driver.get(TP_URL);
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("input.hero__search-input")));
        LOG.info("Entering company name : getesa");
        driver.findElement(By.cssSelector("input.hero__search-input")).sendKeys("getesa");
        LOG.info("Clicking on [Search] button");
        driver.findElement(By.cssSelector("button.hero__search-form__submit")).click();
        Thread.sleep(5000);
        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[contains(text(),'getesa.com')]")));
        LOG.info("Clicking to go to 'getesa.com' review page");
        driver.findElement(By.xpath("//a[contains(text(),'getesa.com')]")).click();
        Thread.sleep(5000);
    }

    public void doRating5()throws  Exception{
        LOG.info(String.format("Going to : %s", ESA_URL));
        //goToTrustPilot();
        driver.get(ESA_URL);
        Thread.sleep(7000);
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("div.star-selector.clearfix a:nth-child(5)")));
        LOG.info("Clicking to make 5-star rate");
        driver.findElement(By.cssSelector("div.star-selector.clearfix a:nth-child(5)")).click();
        Thread.sleep(3000);
    }

    public void leaveReview(String comment)throws  Exception{
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#txtReview")));
        LOG.info(String.format("Entering review : %s", comment));
        driver.findElement(By.cssSelector("#txtReview")).sendKeys(comment);
        Thread.sleep(3000);
    }

    public void clickOnEmailButton()throws  Exception{
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("#iFrameResizer0")));
        driver.switchTo().frame("tp-login-frame");
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("#email-signup")));
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#email-signup")));
        LOG.info("Clicking on [EMAIL] button");
        driver.findElement(By.cssSelector("#email-signup")).click();
        Thread.sleep(3000);
    }

    public void enterTrustEmail(String email)throws  Exception{
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#email-signup-email")));
        LOG.warn(String.format("Entering email : %s", email));
        driver.findElement(By.cssSelector("#email-signup-email")).sendKeys(email);
        Thread.sleep(2000);
    }

    public void enterFullTrustName(String fullName)throws  Exception{
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#email-signup-username")));
        LOG.info(String.format("Entering full name : %s", fullName));
        driver.findElement(By.cssSelector("#email-signup-username")).sendKeys(fullName);
        Thread.sleep(2000);
    }

    public void acceptTerms()throws  Exception{
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("#email-signup-accept-terms")));
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#email-signup-accept-terms")));
        LOG.info("Checking 'I Accept Terms & Conditions'");
        driver.findElement(By.cssSelector("#email-signup-accept-terms")).click();
        Thread.sleep(4000);
    }

    public void submitTrustForm()throws  Exception{
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#email-signup-button")));
        LOG.warn("Submitting [TrustPilot] rating form");
        driver.findElement(By.cssSelector("#email-signup-button")).click();
        driver.switchTo().defaultContent();
        Thread.sleep(10000);
    }

    public void fillTrustRatingForm(String email)throws Exception{
        doRating5();
        leaveReview(MyCSVParser.parseCSVToComments());
        clickOnEmailButton();
        enterTrustEmail(email);
        User targetUser = MyCSVParser.parseCSVToUsers();
        enterFullTrustName(String.format("%s %s",targetUser.getFirstName(), targetUser.getLastName()));
        acceptTerms();
        submitTrustForm();
        Thread.sleep(3000);
    }


    //---------------------Find Sate for Proxy-----------------------------------------------------------------

    public String findStateForProxy(String ip) throws  Exception{
        LOG.info(String.format("Going to : %s", FIND_IP_URL));
        driver.get(FIND_IP_URL);
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("input[name='ip']")));
        LOG.info(String.format("Entering IP : %s", ip));
        driver.findElement(By.cssSelector("input[name='ip']")).sendKeys(ip);
        LOG.info("Clicking on [OK] button");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
        Thread.sleep(5000);
        LOG.info("Getting US state for Entered IP");
//        if(!driver.findElement(By.xpath("//table[@class='results wide home']//tr[2]/td[2]")).getText().contains("US")){
//            LOG.info("IP Country is not USA!");
//            throw new Exception("We are not posting reviews for Non-US IP's!!!");
//        }
        return driver.findElement(By.xpath("//table[@class='results wide home']//tr[3]/td[2]")).getText();
    }

    //--------------10 Minute email------------------------------------------------------------------

    public String registerMinuteEmail() throws  Exception{
        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
        driver.get(MINUTE_MAIL_URL);
        waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#mailAddress")));
        LOG.info("Getting registered 10 minute email");
        return driver.findElement(By.cssSelector("#mailAddress")).getAttribute("value");
    }

    public void confirmRateUsingMinuteEmail() throws  Exception{
        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
        driver.get(MINUTE_MAIL_URL);
        Thread.sleep(6*60*1000);
        //driver.navigate().refresh();
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#messagesList >h3")));
        LOG.info("Clicking to expand received email");
        driver.findElement(By.cssSelector("#messagesList >h3")).click();
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        //LOG.info("Clicking on confirmation link in received email");
        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        //waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        LOG.info("Clicking on confirmation link in received email");
        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")).click();
        Thread.sleep(10000);
    }

    public String registerTrustMinuteEmail() throws  Exception{
        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
        driver.get(MINUTE_MAIL_URL);
        waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#fe_text")));
        LOG.info("Getting registered 10 minute email");
        return driver.findElement(By.cssSelector("#fe_text")).getAttribute("value");
    }

    public void confirmTrustRateUsingMinuteEmail() throws  Exception{
        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
        driver.get(MINUTE_MAIL_URL);
        Thread.sleep(4*60*1000);
        //driver.navigate().refresh();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//a[contains(text(),'Trustpilot')]")));
        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[contains(text(),'Trustpilot')]")));
        LOG.info("Clicking to expand received email");
        driver.findElement(By.xpath("//a[contains(text(),'Trustpilot')]")).click();
        Thread.sleep(7000);
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.xpath("//a[contains(text(),'Activate')]")));
        //LOG.info("Clicking on confirmation link in received email");
        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        //waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        LOG.info("Clicking on confirmation link in received email");
        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        String parent_handle= driver.getWindowHandle();
        waitUntilElementToBeClickable(10, driver.findElement(By.xpath("//a[contains(text(),'Activate')]")));
        driver.findElement(By.xpath("//a[contains(text(),'Activate')]")).click();
        Thread.sleep(7000);
        new WebDriverWait(driver,10).until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> handles = driver.getWindowHandles();
        for(String handle1:handles)
            if(!parent_handle.equals(handle1))
            {
                driver.switchTo().window(handle1);
            }
    }

    public void createTrustPassword()throws  Exception{
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#activation-password")));
        LOG.info(String.format("Entering activation password : password"));
        driver.findElement(By.cssSelector("#activation-password")).sendKeys("password");
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#activation-submit")));
        LOG.warn("Clicking on [Set Your Password] button");
        driver.findElement(By.cssSelector("#activation-submit")).click();
        Thread.sleep(12000);

    }

    public void testMinuteEmail() throws  Exception{
        LOG.info(String.format("Going to : %s", MINUTE_MAIL_URL));
        driver.get(MINUTE_MAIL_URL);
        Thread.sleep(60*1000);
        //driver.navigate().refresh();
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("#messagesList >h3")));
        LOG.info("Clicking to expand received email");
        driver.findElement(By.cssSelector("#messagesList >h3")).click();
        Thread.sleep(3000);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].scrollIntoView()", driver.findElement(By.cssSelector(".message a:not([href='#'])")));
        //jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        //waitUntilVisibilityOf(10, driver.findElement(By.cssSelector("#messagesList>h3+div br+a:not([href='#']):not([href='http://getesa.com'])")));
        LOG.info("Clicking on confirmation link in received email");
        jse.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(".message a:not([href='#'])")));
//        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector(".message a:not([href='#'])")));
//        driver.findElement(By.cssSelector(".message a:not([href='#'])")).click();
        Thread.sleep(3000);
        waitUntilElementToBeClickable(10, driver.findElement(By.cssSelector("img[alt='Logo']")));
        driver.findElement(By.cssSelector("img[alt='Logo']")).click();
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

}
