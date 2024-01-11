package Validations;

import Config.ReusableFunctions;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;

public class Authentication {


    public static void VerifyAuthorization(String Requestpayload) {


        Assert.assertNotNull(ReusableFunctions.getResponsePath("token"));
        Assert.assertEquals(JsonPath.read(Requestpayload, "email"), ReusableFunctions.getResponsePath("data.email"));
        }


    public static void VerifyinvalidAuthorization() {


        Assert.assertEquals(ReusableFunctions.getResponsePath("error"), "No such user exists");
        Assert.assertEquals(ReusableFunctions.getResponsePath("status"), Integer.toString( Integer.parseInt( "500" ) ));


    }


}
