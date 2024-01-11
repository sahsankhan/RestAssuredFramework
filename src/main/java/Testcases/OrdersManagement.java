package Testcases;

import Config.ConfigProperties;
import Config.EndpointURLs;
import Config.EnvGlobals;
import Config.ReusableFunctions;
import general.BaseTest;
import org.testng.annotations.Test;

public class OrdersManagement extends BaseTest {


    @Test
    public void fetchArchiveOrders()
    {
        ReusableFunctions.givenHeaders(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken));
        ReusableFunctions.whenFunction("get", ConfigProperties.baseUrl + EndpointURLs.getArchiveOrders);
        ReusableFunctions.thenFunction(200);
        EnvGlobals.deliveryStatus = ReusableFunctions.getResponsePath("orders.delivery_status[0]");
        EnvGlobals.pagesize = ReusableFunctions.getResponseLengthByKey("orders.size()");
        System.out.println(EnvGlobals.pagesize);

        Validations.OrdersManagement.verifyDeliveryStatusForArchive();
        Validations.OrdersManagement.getArchiveOrdersList();

    }

    @Test
    public void fetchActiveOrders()
    {
        ReusableFunctions.givenHeaders(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken));
        ReusableFunctions.whenFunction("get", ConfigProperties.baseUrl + EndpointURLs.getActiveOrders);
        ReusableFunctions.thenFunction(200);
        EnvGlobals.deliveryStatus = ReusableFunctions.getResponsePath("orders.delivery_status[0]");
        EnvGlobals.orderId = ReusableFunctions.getResponsePath( "orders.id[0]" );
        EnvGlobals.trackingNumber = ReusableFunctions.getResponsePath( "orders.tracking_number[0]" );
        EnvGlobals.zipCode = ReusableFunctions.getResponsePath("orders.zipcode[0]");
        EnvGlobals.pagesize = ReusableFunctions.getResponseLengthByKey("orders.size()");
        System.out.println(EnvGlobals.pagesize);

        Validations.OrdersManagement.verifyDeliveryStatusForActive();
        Validations.OrdersManagement.getActiveOrdersList();

    }


    @Test
    public static void FetchServiceableZipcodes() {
        String Requestpayload = payloads.OrdersManagement.fetchServiceableZipCode();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken), Requestpayload);
        ReusableFunctions.whenFunction("post", ConfigProperties.baseUrl + EndpointURLs.getServiceableZipcodes);
        ReusableFunctions.thenFunction(200);
        EnvGlobals.deliveryId=ReusableFunctions.getResponsePath("delivery_id");

        Validations.OrdersManagement.verifyServiceableZipCode(Requestpayload);

    }

    @Test
    public static void getDeliverySlots() {
        String Requestpayload = payloads.OrdersManagement.getDeliverySlots();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken), Requestpayload);
        ReusableFunctions.whenFunction("post", ConfigProperties.baseUrl + EndpointURLs.getDeliverySlots);
        ReusableFunctions.thenFunction(200);
        EnvGlobals.timeWindowId = ReusableFunctions.getResponsePath("filteredSlots.id[0]");
        EnvGlobals.formattedTimeWindow = ReusableFunctions.getResponsePath("filteredSlots.formattedTimeWindow[0]");
        EnvGlobals.deliveryCharge = ReusableFunctions.getResponsePath("filteredSlots.deliveryCharge[0]");

        Validations.OrdersManagement.verifyDeliverySlots(Requestpayload);

    }

    @Test
    public static void RescheduleDelivery() {
        String Requestpayload = payloads.OrdersManagement.rescheduleDelivery();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken), Requestpayload);
        ReusableFunctions.whenFunction("put", ConfigProperties.baseUrl + EndpointURLs.rescheduleDelivery +EnvGlobals.trackingNumber);
        ReusableFunctions.thenFunction(200);

        Validations.OrdersManagement.verifyRescheduleDelivery();
    }

    @Test
    public static void CancelDelivery()
    {
        ReusableFunctions.givenHeaders(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken));
        ReusableFunctions.whenFunction("delete", ConfigProperties.baseUrl + EndpointURLs.cancelDelivery + EnvGlobals.trackingNumber);
        ReusableFunctions.thenFunction(200);

        Validations.OrdersManagement.cancelDelivery();
    }


    @Test
    public static void VerifyCancelledDelivery()

    {
        ReusableFunctions.givenHeaders(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken));
        ReusableFunctions.whenFunction("delete", ConfigProperties.baseUrl + EndpointURLs.cancelDelivery + EnvGlobals.trackingNumber);
        ReusableFunctions.thenFunction(500);
        EnvGlobals.deletedOrderError = ReusableFunctions.getResponsePath("error");

        Validations.OrdersManagement.verifyCancelledDelivery();
    }


}






