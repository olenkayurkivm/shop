import org.apache.log4j.Logger;


import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
/**
 * Created by OYurkiv on 2/12/2019.
 */
public class SimpleRetryer implements IRetryAnalyzer {
    private final static Logger LOG = Logger.getLogger(SimpleRetryer.class);

    private int retryCount = 0;
    private static final int MAX_RETRY_COUNT = 40;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {                         //Check if test not succeed
            if (retryCount < MAX_RETRY_COUNT) {                 //Check if maxtry count is reached
                retryCount++;                                    //Increase the maxTry count by 1
                iTestResult.setStatus(ITestResult.FAILURE);//Mark test as failed
                LOG.info(String.format("%s : Error in [%s] --> Retrying %d more times",
                        Thread.currentThread().getName(), iTestResult.getName(), (MAX_RETRY_COUNT + 1 - retryCount)));
                return true;                                     //Tells TestNG to re-run the test
            } else {
                LOG.info("Definitely Failed");
                //             iTestResult.setStatus(ITestResult.FAILURE); //If maxCount reached,test marked as failed
            }
//        } else {
//            iTestResult.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
}
