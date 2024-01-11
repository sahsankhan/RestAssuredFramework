package payloads;

import Config.EnvGlobals;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class OrdersManagement {


    private static int randomNum1 = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
    private static int randomNum2 = ThreadLocalRandom.current().nextInt(1, 10000 + 1);


    private static String getCurrentDate() {
        return Instant.now().toString();
    }

    private static String getTomorrowDate() {
        return Instant.now().plus(Duration.ofDays(1)).toString();
    }

    private static String getyesterdayDate() {
        return Instant.now().plus(Duration.ofDays(-1)).toString();
    }


    //Functions
    public static String getFutureDate(String dateFormat)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date()); // Use today date
        c.add(Calendar.DATE, 5); // Adding 5 days from the current date
        String output = sdf.format(c.getTime());
        return output;
    }


    // Payloads
    public static String fetchServiceableZipCode()
    {
        return "{\n" +
                "\t\"zipCode\": \"" +EnvGlobals.zipCode+ "\"\n" +
                "}";
    }

    public static String getDeliverySlots()
    {
        return "{\n" +
                " \"zipCode\": \""+ EnvGlobals.zipCode +"\",\n" +
                " \"deliveryId\": " + EnvGlobals.deliveryId + ",\n" +
                " \"deliveryType\": \"delivery\",\n" +
                " \"deliveryDate\": \""+ getFutureDate("dd-MM-yyyy")+"\"\n" +
                "}";
    }


    public static String rescheduleDelivery()
    {

        return "{\n" +
                " \"orderId\": \""+EnvGlobals.orderId+"\",\n" +
                " \"timeWindowId\": \""+EnvGlobals.timeWindowId+"\",\n" +
                " \"readyBy\": \""+ getFutureDate("dd-MM-yyyy")+"\",\n" +
                " \"formattedTimeWindow\": \""+EnvGlobals.formattedTimeWindow+"\"\n" +
                "}";
    }

}
