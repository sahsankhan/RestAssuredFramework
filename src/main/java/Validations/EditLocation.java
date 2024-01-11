package Validations;


import Config.ConfigProperties;
import Config.EnvGlobals;
import Config.ReusableFunctions;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

import static general.FlatMapUtil.transformJson;
import static payloads.EditLocation.randomZipCode;
//import static payloads.EditLocation.getRandomZipCode;

public class EditLocation {

    public static void getAllLocationsList() {
        for (int i = 0; i < EnvGlobals.pagesize; i++) {

            Assert.assertNotNull(ReusableFunctions.getResponsePath("locations[" + i + "].id"));
            Assert.assertNotNull(ReusableFunctions.getResponsePath("locations[" + i + "].name"));
            Assert.assertNotNull(ReusableFunctions.getResponsePath("locations[" + i + "].zip"));
        }
    }

    public static void verifySingleLocationDetails() {

        Assert.assertNotNull(ReusableFunctions.getResponsePath("id"));
        Assert.assertNotNull(ReusableFunctions.getResponsePath("shop_id"));
        Assert.assertEquals( ReusableFunctions.getResponsePath( "api_ref_key" ),  EnvGlobals.shopifyLocationId);

    }

    public static void verifyEditLocationSettings(String Requestpayload) {

        Assert.assertEquals(JsonPath.read(Requestpayload, "id"), Integer.valueOf(ReusableFunctions.getResponsePath("[1][0].id")));
        Assert.assertEquals(JsonPath.read(Requestpayload, "location_id"),Integer.valueOf(ReusableFunctions.getResponsePath("[1][0].location_id")));
    }
    

       public static void verifyAddedZipCode()
    {
        Assert.assertEquals(EnvGlobals.actZipCode,Integer.toString(randomZipCode));
    }

    public static void verifyInvalidZipCode(String Requestpayload)
    {
        Assert.assertEquals( ReusableFunctions.getResponsePath( "errors[0].msg" ),  "Zipcode must be equal to 5 digits");
        Assert.assertEquals( ReusableFunctions.getResponsePath( "errors[0].param" ),  "zipcode");
        Assert.assertEquals(JsonPath.read(Requestpayload, "zipcode[0]"),ReusableFunctions.getResponsePath("errors[0].value[0]"));
    }

}