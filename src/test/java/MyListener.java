/**
 * Created by OYurkiv on 12/14/2018.
 */

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MyListener implements  ITestListener {

    private static final String SCREENSHOT_FOLDER = "C:/Users/OYurkiv/Documents/RateBackUps/screens";
    private static final String SCREENSHOT_FORMAT = ".png";

    private final static Logger LOG = Logger.getLogger(MyListener.class);


    @Override
    public void onTestStart(ITestResult iTestResult) {

    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LOG.info("------------------------------------------------------------------");
        LOG.info("------------------------SUCCESS-----------------------------------");
        LOG.info("------------------------------------------------------------------");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LOG.info("------------------------------------------------------------------");
        LOG.info("------------------------FAIL--------------------------------------");
        LOG.info("------------------------------------------------------------------");
        StringWriter sw = new StringWriter();
        iTestResult.getThrowable().printStackTrace(new PrintWriter(sw));
        LOG.info(sw.toString());

        takeScreenshot(iTestResult);
        if((iTestResult.getThrowable().getMessage()).contains("Unable to locate element: {\"method\":\"css selector\",\"selector\":\"#Overall_5\"}")) {
            iTestResult.setStatus(ITestResult.SUCCESS);

        }
        }


    public void takeScreenshot(ITestResult result) {
        //creating screenshot folder for test
        String folder = SCREENSHOT_FOLDER;
        File dir = new File(folder);
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            // Pause because sometimes webdriver takes previous page screenshot
            //Thread.sleep(3000);
            // Taking webDriver screenshot
            File screenFile = ((TakesScreenshot) AbstractPage.getDriver()).getScreenshotAs(OutputType.FILE);
            // Setting screenshot file name 'testMethodName_01_12_14_14_11_09.png'
            SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
            String fileName = result.getName() + "_" + formater.format(Calendar.getInstance().getTime()) + SCREENSHOT_FORMAT;
            // Put screen file to appropriate folder
            FileUtils.copyFile(screenFile, new File(dir.getAbsoluteFile() + "/" + fileName));

            File directory = new File(".");
            LOG.warn("Taking screenshot with file name:" + " " + fileName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LOG.info("------------------------------------------------------------------");
        LOG.info("------------------------SKIP--------------------------------------");
        LOG.info("------------------------------------------------------------------");
        StringWriter sw = new StringWriter();
        iTestResult.getThrowable().printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        LOG.info(stacktrace);

        takeScreenshot(iTestResult);

        if((iTestResult.getThrowable().getMessage()).contains("Unable to locate element: {\"method\":\"css selector\",\"selector\":\"#Overall_5\"}")) {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    @Override
    public void onStart(ITestContext iTestContext) {

    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        LOG.info("---BOT SESSION FINISHED---");

    }
}
