/*
# baseTest functions defined in this class
 */

package general;


import Testcases.DeliveryLocationSettings;
import com.relevantcodes.extentreports.ExtentReports;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import static Validations.DeliveryLocationSettings.slotsList;

//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.PropertyConfigurator;

import static Config.ConfigProperties.Environment;

public class MainCall {



    private static ExtentReports extent;

    public static void startReport() {
        extent = new ExtentReports(System.getProperty("user.dir") + "/reports/ExtentReport.html", true);
        extent.addSystemInfo("Environment", Environment);
    }

    public static ExtentReports getExtentReport() {
        if (extent != null) {
            return extent;
        } else {
            throw new IllegalStateException("Extent Report object not initialized");
        }
    }
//
//    public static DeliveryLocationSettings devLoc;

    public static void restAssuredPreReq() {
        //baseTest.REQUEST = RestAssured.given().baseUri(coreBaseUrl);
        //RestAssured.baseURI = baseUrl;
        //RestAssured.port = Integer.valueOf(Port);
        //REQUEST = RestAssured.given().contentType(ContentType.JSON);
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
//        devLoc = new DeliveryLocationSettings();
//        DeliveryLocationSettings.preReqForDeliverySlots();
      //  BasicConfigurator.configure();

    }

}
