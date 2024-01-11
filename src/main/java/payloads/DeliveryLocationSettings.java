package payloads;

import Config.EnvGlobals;

import java.util.concurrent.ThreadLocalRandom;

import static payloads.OrdersManagement.getFutureDate;

public class DeliveryLocationSettings {

    private static int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
    private static int randomNum1 = ThreadLocalRandom.current().nextInt(1, 10 + 1);
    private static int randomNum2 = ThreadLocalRandom.current().nextInt(1, 100 + 1);
    public static int sameDaySurcharge;
    public static int nextDaySurcharge;
    public static int futureDateRange;
    public static int getSameDaySurcharge () { return sameDaySurcharge = randomNum1;}
    public static int getNextDaySurcharge () { return nextDaySurcharge = randomNum1;}
    public static int getFutureDateRange() { return futureDateRange = randomNum2; }

    public static String addDeliverySlotForAweek()
    {
        return "{\n" +
                "  \"start_time\": \"" + EnvGlobals.expStartTime + "\",\n" +
                "  \"end_time\": \"" + EnvGlobals.expEndTime + "\",\n" +
                "  \"max_order_limit\": "+ EnvGlobals.orderLimit +",\n" +
                "  \"delivery_charge\": \""+ EnvGlobals.deliveryCharge +"\",\n" +
                "  \"lead_time\": 2,\n" +
                "  \"repeat\": true,\n" +
                "  \"is_enable\": true,\n" +
                "  \"week_day\": 1,\n" +
                "  \"id\": \"\",\n" +
                "  \"delivery_id\": " + EnvGlobals.deliveryId + " \n" +
                "}";
    }


    public static String addDeliverySlotForSingleDay()
    {
        return "{\n" +
                "  \"start_time\": \"" + EnvGlobals.expStartTimeDay + "\",\n" +
                "  \"end_time\": \"" + EnvGlobals.expEndTimeDay + "\",\n" +
                "  \"max_order_limit\": 50,\n" +
                "  \"delivery_charge\": \"00.00\",\n" +
                "  \"lead_time\": 2,\n" +
                "  \"repeat\": false,\n" +
                "  \"is_enable\": true,\n" +
                "  \"week_day\": 1,\n" +
                "  \"id\": \"\",\n" +
                "  \"delivery_id\": " + EnvGlobals.deliveryId + " \n" +
                "}";
    }

    public static String addBlackoutDate()
    {
        return "{\n" +
                "\t\"blackoutDates\": [\""+ getFutureDate("yyyy-MM-dd") +"\"]\n" +
                "}";
    }


    public static String updateDeliverySurcharge()
    {
        return "{\n" +
                "   \"id\": "+ EnvGlobals.deliveryId +",\n" +
                "   \"same_day_surcharge\": "+ getSameDaySurcharge() +",\n" +
                "   \"is_same_day_surcharge\": true,\n" +
                "   \"next_day_surcharge\": \""+ getNextDaySurcharge() +"\",\n" +
                "   \"is_next_day_surcharge\": true\n" +
                "}";
    }

    public static String updateFutureDateRange()
    {
        return "{\n" +
                "\t\"future_date_range\": " + getFutureDateRange() + "\n" +
                "}";
    }
    
    
}
