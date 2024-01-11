/*
Define global variables to use re-use them
 */

package Config;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;

public class EnvGlobals {


    public static StringBuilder Differnce = new StringBuilder() ;

    //Authorization Tokens
    public static Response response; // API response object
    public static RequestSpecification requestSpecification; // given store object
    public static String authorizationToken; // authorization key with bearer

    //July Admin portal
    public static String deliveryStatus;
    public static String orderId;
    public static String trackingNumber;
    public static String zipCode;
    public static String deliveryId;
    public static String sameDaySurchrage;
    public static String nextDaySurcharge;
    public static String futureDateRange;
    public static String timeWindowId;
    public static String formattedTimeWindow;
    public static String deletedOrderError;
    public static String shopifyLocationId;
    public static String locationId;
    public static String actZipCode;
    public static String actDeliveryId;
    public static String slotId;
    public static String expStartTime = "10:00:00";
    public static String expEndTime = "13:00:00";
    public static String deliveryCharge = "0.00";
    public static String expStartTimeDay = "14:00:00";
    public static String expEndTimeDay = "17:00:00";
    public static String actBlackoutDelMsg;
    public static String blackoutId;
    public static String deliverySlotId;
    public static int slotSize;
    public static int pagesize;

    //Delivery Location Settings
    public static int orderLimit = 50;

    //Validation Messages
    public static String expBlackoutDelMsg = "Blackout date deleted successfully";

}
