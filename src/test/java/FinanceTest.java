import org.apache.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by OYurkiv on 10/1/2018.
 */
public class FinanceTest {
    final static Logger LOG = Logger.getLogger(FinanceTest.class);
    //private static WebDriver driver;


//    @BeforeMethod
//    public static void setUp(){
//        System.out.println("Hello finance!!");
//    }

    @Test
    public void testFinance() throws Exception{
        System.out.println("Hello finance-1!!");
//        FinancePage financePage = new FinancePage();
//        financePage.goToFinance();
//        System.out.println(financePage.getIndexAbsoluteChangeOf(1));
//        System.out.println(financePage.getIndexRelativeChangeOf(1));
        FinanceBO financeBO = new FinanceBO();
        LOG.info(String.format("Abs Change : %s", financeBO.getWorldIndexesAbsoluteChanges()));
        LOG.info(String.format("Rel Change : %s", financeBO.getWorldIndexesRelativeChanges()));
        LOG.info(String.format("Open-High-Low Values : %s", financeBO.getIndexOpenHighLowValues()));
    }

    @Test
    public void testFinance2() throws Exception{
//        EmailSender emailSender = new EmailSender();
//        emailSender.sendMail();
    }



//    @AfterMethod(alwaysRun = true)
//    public void tearDown() throws Exception {
//       DriverFactory.closeDriver();
//    }
}
