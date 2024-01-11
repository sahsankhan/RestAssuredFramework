package payloads;

import Config.EnvGlobals;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class EditLocation {

     public static int randomNum = ThreadLocalRandom.current().nextInt(1, 10000 + 1);
     public static int randomZipCode = 10000 + new Random().nextInt(90000);

    public static String editLocation()
    {
        return "{\n" +
                "   \"id\": "+ EnvGlobals.locationId+",\n" +
                "   \"is_delivery\": true,\n" +
                "   \"is_installation\": false,\n" +
                "   \"location_id\": "+ EnvGlobals.locationId +",\n" +
                "   \"is_enable\": false,\n" +
                "   \"is_shipping\": false,\n" +
                "   \"is_pickup\": false,\n" +
                "   \"zipcode\": [\"" + randomZipCode + "\" ]\n" +
                "}";
    }


    public static String addInvalidZipCode()
    {
        return "{\n" +
                "   \"id\": "+ EnvGlobals.locationId+",\n" +
                "   \"is_delivery\": true,\n" +
                "   \"is_installation\": false,\n" +
                "   \"location_id\": "+ EnvGlobals.locationId +",\n" +
                "   \"is_enable\": false,\n" +
                "   \"is_shipping\": false,\n" +
                "   \"is_pickup\": false,\n" +
                "   \"zipcode\": [\"" + randomNum + "\" ]\n" +
                "}";
    }




}
