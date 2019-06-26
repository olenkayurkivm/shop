import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by OYurkiv on 10/1/2018.
 */
@Listeners(MyListener.class)
public class FinanceTest {
    final static Logger LOG = Logger.getLogger(FinanceTest.class);
    //private static WebDriver driver;
    FinanceBO financeBO;
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";
    //private static final int invocationCountR = 5 + (int)(Math.random()*9) ;
    List<ProxyObject> usedProxiesToRecord = new ArrayList<>();



//    @BeforeMethod
//    public static void setUp(){
//
//    }


    //@Parameters("ip")
    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance1() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
        //page1.parseAllProxySites();
        //page1.parseSpysOne();
        //AbstractPage.getDriver().get("https://getesa.com/");
        //page1.testMinuteEmail();
        //Thread.sleep(10*1000);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance2() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance3() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance4() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance5() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance6() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance7() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance8() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance9() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }

    @Test(retryAnalyzer = SimpleRetryer.class)
    public void testFinance10() throws Exception{
        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
        String targetEmail = page1.registerMinuteEmail();
        page1.fillRatingForm(targetEmail);
        usedProxiesToRecord.add(proxy);
        page1.confirmRateUsingMinuteEmail();
        page1.fillSatisfactionSurvey(stateForProxy);
    }


//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance11() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance12() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance13() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance14() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance15() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }

//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance16() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance17() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance18() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance19() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance20() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        AbstractPage page1 = new AbstractPage(proxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        usedProxiesToRecord.add(proxy);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//    }

//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance11() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        String targetProxy = String.format("%s:%s", proxy.getProxyIp(), proxy.getProxyPort());
//        AbstractPage page1 = new AbstractPage(targetProxy, CHROME);
//        throw new WebDriverException("Custom error");
//    }
//
//    @Test(retryAnalyzer = SimpleRetryer.class)
//    public void testFinance12() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        String targetProxy = String.format("%s:%s", proxy.getProxyIp(), proxy.getProxyPort());
//        AbstractPage page1 = new AbstractPage(targetProxy, CHROME);
//        throw new WebDriverException("Standard error");
//    }



    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) throws Exception {
        DriverFactory.closeDriver();
        if (result.getStatus() == ITestResult.SUCCESS) {
            int sleepTime = (int) (Math.random() * 1200000) + 1800000;
            //int sleepTime = (int)(Math.random()*3000)+5000;
            LOG.info(String.format("Waiting for another bot session : %d", sleepTime));
            Thread.sleep(sleepTime);
        }
    }

    @AfterClass
    public void endDay() throws Exception {
        LOG.warn("----------------------------------------------------");
        LOG.warn(String.format("Total proxies used : %d", usedProxiesToRecord.size()));
        LOG.warn("---LIST OF USED PROXIES---");
        for(ProxyObject proxy : usedProxiesToRecord) {
            System.out.println(String.format("%s,%s,%s", proxy.getProxyIp(), proxy.getProxyPort(), proxy.getProxyType()));
            LOG.warn(String.format("%s,%s,%s", proxy.getProxyIp(), proxy.getProxyPort(), proxy.getProxyType()));
        }
//            int sleepTime = 12*60*60*1000;
//            //int sleepTime = (int)(Math.random()*3000)+5000;
//            LOG.warn(String.format("Waiting for another day : %d", sleepTime));
//            Thread.sleep(sleepTime);
    }

}
