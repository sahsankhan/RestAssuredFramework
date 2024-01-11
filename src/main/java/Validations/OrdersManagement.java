package Validations;

import Config.EnvGlobals;
import Config.ReusableFunctions;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;

public class OrdersManagement {


    public static void verifyDeliveryStatusForArchive() {
        assertTrue( EnvGlobals.deliveryStatus.matches( "Delivered|Canceled" ) );
    }

    public static void getArchiveOrdersList() {
        for (int i = 0; i < EnvGlobals.pagesize; i++) {

            Assert.assertNotNull(ReusableFunctions.getResponsePath("orders[" + i + "].id"));
            Assert.assertNotNull(ReusableFunctions.getResponsePath("orders[" + i + "].order_number"));
            Assert.assertNotNull(ReusableFunctions.getResponsePath("orders[" + i + "].zipcode"));
        }
    }



    public static void verifyDeliveryStatusForActive() {
        assertTrue( EnvGlobals.deliveryStatus.matches( "In Transit|Created|Returned" ) );
    }

    public static void getActiveOrdersList() {
        for (int i = 0; i < EnvGlobals.pagesize; i++) {

            Assert.assertNotNull(ReusableFunctions.getResponsePath("orders[" + i + "].id"));
            Assert.assertNotNull(ReusableFunctions.getResponsePath("orders[" + i + "].order_number"));
            Assert.assertNotNull(ReusableFunctions.getResponsePath("orders[" + i + "].zipcode"));
        }
    }


    public static void verifyServiceableZipCode(String Requestpayload) {

        Assert.assertEquals(JsonPath.read(Requestpayload, "zipCode"), ReusableFunctions.getResponsePath("zipcode"));
    }

    public static void verifyDeliverySlots(String Requestpayload) {


        Assert.assertEquals(JsonPath.read(Requestpayload, "deliveryId"), Integer.valueOf(ReusableFunctions.getResponsePath("filteredSlots.deliveryId[0]")) );

    }

    public static void verifyRescheduleDelivery()
    {
        Assert.assertEquals( ReusableFunctions.getResponsePath( "success" ), "true" );
        //  Assert.assertEquals(ReusableFunctions.getResponsePath("message"),"Rescheduled successfully");
    }

    public static void cancelDelivery() {
        Assert.assertEquals( ReusableFunctions.getResponsePath( "success" ),  "true");
        Assert.assertEquals( ReusableFunctions.getResponsePath( "message" ),  "Cancelled successfully");
    }
    public static void verifyCancelledDelivery() {
        Assert.assertEquals( ReusableFunctions.getResponsePath( "status" ),  "500");
        Assert.assertEquals( ReusableFunctions.getResponsePath( "error" ),  "Cannot cancel a canceled delivery");
    }


}
