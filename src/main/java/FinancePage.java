//import com.codeborne.selenide.Configuration;
//import com.codeborne.selenide.SelenideElement;
//import org.apache.log4j.Logger;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import ru.yandex.qatools.allure.annotations.Step;
//
//import java.util.List;

//import static com.codeborne.selenide.Selenide.*;

///**
// * Created by OYurkiv on 10/1/2018.
// */
//public class FinancePage {
//    //private WebDriver driver = DriverFactory.getInstance();
//    final static Logger LOG = Logger.getLogger(FinancePage.class);
    //private final WebDriverWait webDriverWait = new WebDriverWait(driver, 5);



//    private List<SelenideElement> indexAbsoluteChangeListS = $$(By.xpath("//a//div[@class='maIvLb']/span/following-sibling::span/span[not(child::span)]"));
//    private List<SelenideElement>  indexRelativeChangeListS = $$(By.xpath("//a//div[@class='maIvLb']/span/following-sibling::span/span[child::span]/span"));
//    private List<SelenideElement>  indexDetailsLinkButtonListS = $$(By.cssSelector("g-link>.a-no-hover-decoration"));
//    private SelenideElement indexOpenValueS = $(By.xpath("//g-card-section//tbody/tr[1]/td[last()]"));
//    private SelenideElement indexMaxValueS = $(By.xpath("//g-card-section//tbody/tr[1]/td[last()]"));
//    private SelenideElement indexMinValueS = $(By.xpath("//g-card-section//tbody/tr[2]/td[last()]"));

//    @FindBy(xpath = "//a//div[@class='maIvLb']/span/following-sibling::span/span[not(child::span)]")
//    private List<WebElement> indexAbsoluteChangeList;
//
//    @FindBy(xpath = "//a//div[@class='maIvLb']/span/following-sibling::span/span[child::span]/span")
//    private List<WebElement> indexRelativeChangeList;
//
//    @FindBy(css = "g-link>.a-no-hover-decoration")
//    private List<WebElement> indexDetailsLinkButtonList;
//
//    @FindBy(xpath = "//g-card-section//tbody/tr[1]/td[last()]")
//    private WebElement indexOpenValue;
//
//    @FindBy(xpath = "//g-card-section//tbody/tr[2]/td[last()]")
//    private WebElement indexMaxValue;
//
//    @FindBy(xpath = "//g-card-section/div/div/div[2]//tbody//td[last()]")
//    private WebElement indexMinValue;


//    private SelenideElement ratePopUp = $(By.cssSelector("#sa_rounded"));
//    private SelenideElement overall5 = $(By.cssSelector("#Overall5"));
//    private SelenideElement recommend5 = $(By.cssSelector("#Recommend5"));
//    private SelenideElement price5 = $(By.cssSelector("#priceesa5"));
//    private SelenideElement quickMessage = $(By.cssSelector("#comments"));

//    @FindBy(css = "#sa_rounded")
//    private WebElement ratePopUp;
//
//    @FindBy(css = "#Overall5")
//    private WebElement overall5;
//
//    @FindBy(css = "#Recommend5")
//    private WebElement recommend5;
//
//    @FindBy(css = "#priceesa5")
//    private WebElement price5;
//
//    @FindBy(css = "#comments")
//    private WebElement quickMessage;



//
//    public FinancePage(String ip, String browser) {
//    super(ip, browser);
//
//    }
//
//    public void rate1(){
////        waitUntilVisibilityOf(10, ratePopUp);
////        waitUntilElementToBeClickable(10, overall5);
//        System.out.println("Before");
//        overall5.click();
//        System.out.println("After");
//    }

//    public void rate2(){
////        waitUntilElementToBeClickable(10, recommend5);
//        recommend5.click();
//    }
//
//    public void rate3(){
////        waitUntilElementToBeClickable(10, price5);
//        price5.click();
//    }
//
//    public void leaveComment(String comment){
//        quickMessage.sendKeys(comment);
//    }
//
//    @Step
//    public String getIndexAbsoluteChangeOf(int numberOfIndex){
//        //return indexAbsoluteChangeList.get(numberOfIndex+5).getText().trim();
//        return indexAbsoluteChangeListS.get(numberOfIndex+5).getText();
//    }
//
//    @Step
//    public String getIndexRelativeChangeOf(int numberOfIndex){
////        String relIndex = indexRelativeChangeList.get(numberOfIndex+5).getText();
////        return relIndex.substring(1, relIndex.length()-2);
//        String relIndex = indexRelativeChangeListS.get(numberOfIndex+5).getText();
//        return relIndex.substring(1, relIndex.length()-2);
//    }
//
//    @Step
//    public void clickOnIndexOf(int numberOfIndex){
//        //indexDetailsLinkButtonList.get(numberOfIndex+8).click();
//        indexDetailsLinkButtonListS.get(numberOfIndex+8).click();
//    }
//
//    @Step
//    public String getIndexOpenValue(){
//        //return indexOpenValue.getText().trim();
//        return indexOpenValueS.getText();
//    }
//
//    @Step
//    public String getIndexMaxValue(){
//        //return indexMaxValue.getText().trim();
//        return indexMaxValueS.getText();
//    }
//
//    @Step
//    public String getIndexMinValue(){
//        //return indexMinValue.getText().trim();
//        return indexMinValueS.getText();
//    }




//}
