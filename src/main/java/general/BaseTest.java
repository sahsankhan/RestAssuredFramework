/*
# Base class - Defined TestNG annotations
# Execute before every test case
 */

package general;

import Config.ReusableFunctions;
import Testcases.Authentication;
import Testcases.DeliveryLocationSettings;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import databaseConnection.DatabaseConnectivity;
import databaseConnection.MySqlDbConn;
import io.restassured.specification.RequestSpecification;
//import org.apache.log4j.BasicConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;

import static Config.ConfigProperties.IsEnableReporting;
import static Config.EnvGlobals.Differnce;

public class BaseTest {

    private static ExtentTest logger;
    public static RequestSpecification REQUEST;

    //    For Reporting to insert into database
    static Date startTime = null;
    static Date endTime = null;
    static Integer passedCount = 0;
    static Integer failedCount = 0;
    static Integer skippedCount = 0;

    @BeforeSuite()
    public void startReport() {

        if (IsEnableReporting.equals("true")) {
            MainCall.startReport();
        }
        //   BasicConfigurator.configure();

        // connect db connection
        DatabaseConnectivity.dbConnection();


        startTime = getTime(); // For reporting into db
    }

    @BeforeMethod()
    public void beforeTest(Method method) {
        if (IsEnableReporting.equals("true")) {
            logger = MainCall.getExtentReport().startTest(method.getName(), "");
            logger.setStartedTime(getTime());
        }

        // To set Base url & content type
        MainCall.restAssuredPreReq();

        // Enable below line to execute authorization token before every test case
        Authentication.Authorization();


    }

    @AfterMethod()
    public void QuitDriver(ITestResult result) {

        if (IsEnableReporting.equals("true")) {

            if (result.getStatus() == ITestResult.FAILURE) {

                failedCount++;
                logger.log(LogStatus.FAIL, "Test Case Failed reason is: " + result.getThrowable());
                logger.log(LogStatus.FAIL, "Test Case Failed reason is: " + Differnce.toString());




//                logger.log(LogStatus.FAIL, logger.addScreenCapture(Screenshots.takeScreenshot(result.getMethod()
//                        .getMethodName())));
            } else if (result.getStatus() == ITestResult.SKIP) {

                skippedCount++;
                logger.log(LogStatus.SKIP, "Test Case Skipped is: " + result.getName());
            } else if (result.getStatus() == ITestResult.SUCCESS) {

                passedCount++;
                logger.log(LogStatus.PASS, result.getMethod().getMethodName() + " is Passed");
                logger.log(LogStatus.PASS, "All the Assertions have been Passed");

                logger.log(LogStatus.PASS, ReusableFunctions.getResponse());
            }

            logger.setEndedTime(getTime());
            MainCall.getExtentReport().endTest(logger);

            // Enable below line to print response of every API
            System.out.println("method name: " + result.getMethod().getMethodName());
            ReusableFunctions.printResponse();
        }
    }

    private Date getTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    @AfterSuite()
    public void endReport() throws InterruptedException, SQLException {
        Thread.sleep(5000);
        //WebDriverFactory.finishDriver();
        if (IsEnableReporting.equals("true")) {
            MainCall.getExtentReport().flush();
            MainCall.getExtentReport().close();
        }

        endTime = getTime(); // For reporting into db

        //This command will insert data into database
           MySqlDbConn.insertReportingDataIntoDB(startTime, passedCount, failedCount, skippedCount, startTime, endTime);
    }

}
