package Validations;

import Config.EnvGlobals;
import Config.ReusableFunctions;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;

import java.util.ArrayList;

import static payloads.DeliveryLocationSettings.*;

public class DeliveryLocationSettings {
    public static ArrayList<String> slotsList;


    public static void verifyAddDeliverySlotForWeek(String Requestpayload) {

        Assert.assertEquals(JsonPath.read(Requestpayload, "start_time"), ReusableFunctions.getResponsePath("start_time[0]"));
        Assert.assertEquals(JsonPath.read(Requestpayload, "end_time"), ReusableFunctions.getResponsePath("end_time[0]"));
        Assert.assertEquals(JsonPath.read(Requestpayload, "delivery_charge"), ReusableFunctions.getResponsePath("delivery_charge[0]"));
    }
    public static void verifyAddedDeliveryForSingeDay() {

        Assert.assertEquals(ReusableFunctions.getResponsePath("start_time"),EnvGlobals.expStartTimeDay);
        Assert.assertEquals(ReusableFunctions.getResponsePath("end_time"),EnvGlobals.expEndTimeDay);
        EnvGlobals.actDeliveryId = ReusableFunctions.getResponsePath("delivery_id");
    }


    public static void verifyDeliverySlotConflictMsg() {

        Assert.assertEquals(ReusableFunctions.getResponsePath("isError[0]"),("true"));
        Assert.assertEquals(ReusableFunctions.getResponsePath("message[0]"),"This time slot cannot be applied due to conflict(s). Please edit manually.");
    }

    public static void verifyDeleteDeliverySlot() {

        Assert.assertEquals( ReusableFunctions.getResponsePath("message"), "Slot deleted successfully" );
        Assert.assertEquals( ReusableFunctions.getResponsePath("success"), "true" );
    }

    public static void verifyAddedBlackoutDate() {

        Assert.assertEquals( ReusableFunctions.getResponsePath("success"), "true" );
        Assert.assertEquals( ReusableFunctions.getResponsePath("message"), "Blackout dates updated successfully" );
    }

    public static void verifyDeleteBlackoutDate() {

        Assert.assertEquals(EnvGlobals.actBlackoutDelMsg,EnvGlobals.expBlackoutDelMsg);
        Assert.assertEquals( ReusableFunctions.getResponsePath("success"), "true" );
    }

    public static void verifyUpdateDeliverySurcharge() {
        Assert.assertEquals(ReusableFunctions.getResponsePath("message"),"Updated successfully");
        Assert.assertEquals( ReusableFunctions.getResponsePath("success"), "true" );
    }

    public static void verifyUpdateFutureDateRange() {
        Assert.assertEquals(ReusableFunctions.getResponsePath("message"),"Updated successfully");
        Assert.assertEquals( ReusableFunctions.getResponsePath("success"), "true" );
    }

    public static void verifyLocationDetails() {
        Assert.assertEquals( getSameDaySurcharge(), sameDaySurcharge );
        Assert.assertEquals( getNextDaySurcharge(), nextDaySurcharge );
        Assert.assertEquals( getFutureDateRange(), futureDateRange );
    }

    public static void getListOfDeliverySlots()
    {
         slotsList = new ArrayList<String>();


        for (int i = 0; i < EnvGlobals.slotSize; i++) {

            slotsList.add(ReusableFunctions.getResponsePath("Deliveries.DeliverySlots.id[0]["+ i +"]"));
            System.out.println(slotsList.get(i));
//            Assert.assertNotNull(ReusableFunctions.getResponsePath("locations[" + i + "].id"));
//            Assert.assertNotNull(ReusableFunctions.getResponsePath("Deliveries.DeliverySlots.id[0]["+ i +"]"));

        }
    }

}
