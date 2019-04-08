import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by OYurkiv on 12/14/2018.
 */
@Listeners(MyListener.class)
public class FinanceTest2 {
    final static Logger LOG = Logger.getLogger(FinanceTest.class);
    public static final String CHROME = "chrome";
    public static final String FIREFOX = "firefox";

//    @Test
//    public void testFinance6() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        String targetProxy = String.format("%s:%s", proxy.getProxyIp(), proxy.getProxyPort());
//        AbstractPage page1 = new AbstractPage(targetProxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//        Thread.sleep(2*60*60*1000);
//    }
//
//    @Test
//    public void testFinance7() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        String targetProxy = String.format("%s:%s", proxy.getProxyIp(), proxy.getProxyPort());
//        AbstractPage page1 = new AbstractPage(targetProxy, CHROME);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//        Thread.sleep(6480000);
//    }
//
//    @Test
//    public void testFinance8() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        String targetProxy = String.format("%s:%s", proxy.getProxyIp(), proxy.getProxyPort());
//        AbstractPage page1 = new AbstractPage(targetProxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//        Thread.sleep(7020000);
//    }
//
//    @Test
//    public void testFinance9() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        String targetProxy = String.format("%s:%s", proxy.getProxyIp(), proxy.getProxyPort());
//        AbstractPage page1 = new AbstractPage(targetProxy, CHROME);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//        Thread.sleep(6840000);
//    }
//
//    @Test
//    public void testFinance10() throws Exception{
//        ProxyObject proxy = MyCSVParser.parseCSVToProxy();
//        String targetProxy = String.format("%s:%s", proxy.getProxyIp(), proxy.getProxyPort());
//        AbstractPage page1 = new AbstractPage(targetProxy, FIREFOX);
//        String stateForProxy = page1.findStateForProxy(proxy.getProxyIp());
//        String targetEmail = page1.registerMinuteEmail();
//        page1.fillRatingForm(targetEmail);
//        page1.confirmRateUsingMinuteEmail();
//        page1.fillSatisfactionSurvey(stateForProxy);
//        Thread.sleep(6660000);
//    }

//    @Test
//    public void testFinance() throws Exception{
//        String targetCom = MyCSVParser.parseCSVToProxy();
//        System.out.println("Comment");
//        System.out.println(targetCom);
//
//        System.out.println("User:");
//        System.out.println(targetUser.getUserId());
//        System.out.println(targetUser.getEmail());
//        System.out.println(targetUser.getFirstName());
//        System.out.println(targetUser.getLastName());
//    }

//    @Test
//    public void testFinance2() throws Exception{
//        EmailSender emailSender = new EmailSender();
//        emailSender.sendMail();
//    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        DriverFactory.closeDriver();
    }
}
