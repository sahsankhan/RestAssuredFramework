package Testcases;

import Config.ConfigProperties;
import Config.EndpointURLs;
import Config.EnvGlobals;
import Config.ReusableFunctions;
import general.BaseTest;
import org.testng.annotations.Test;

public class EditLocation extends BaseTest {

    @Test
    public static void getAllLocations()
    {
        ReusableFunctions.givenHeaders(ReusableFunctions.headers("Authorization",EnvGlobals.authorizationToken) );
        ReusableFunctions.whenFunction( "get",ConfigProperties.baseUrl+ EndpointURLs.getAllLocations );
        ReusableFunctions.thenFunction( 200 );
        EnvGlobals.shopifyLocationId = ReusableFunctions.getResponsePath("locations.id[0]");
        EnvGlobals.pagesize = ReusableFunctions.getResponseLengthByKey("locations.size()");
        System.out.println(EnvGlobals.pagesize);

        Validations.EditLocation.getAllLocationsList();
    }

    @Test
    public static void getSingleLocation()
    {
        ReusableFunctions.givenHeaders(ReusableFunctions.headers("Authorization",EnvGlobals.authorizationToken) );
        ReusableFunctions.whenFunction( "get",ConfigProperties.baseUrl+ EndpointURLs.getSingleLocation + EnvGlobals.shopifyLocationId);
        ReusableFunctions.thenFunction( 200 );
        EnvGlobals.locationId = ReusableFunctions.getResponsePath("id");
        EnvGlobals.deliveryId = ReusableFunctions.getResponsePath("Deliveries.id[0]");

        Validations.EditLocation.verifySingleLocationDetails();
    }

    @Test
    public static void addZipCodeInLocation() {
        String Requestpayload = payloads.EditLocation.editLocation();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken), Requestpayload);
        ReusableFunctions.whenFunction("put", ConfigProperties.baseUrl + EndpointURLs.addZipCode + EnvGlobals.locationId);
        ReusableFunctions.thenFunction(200);

        Validations.EditLocation.verifyEditLocationSettings(Requestpayload);
    }

    @Test
    public static void verifyAddedZipcode() {

        ReusableFunctions.givenHeaders(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken));
        ReusableFunctions.whenFunction("get", ConfigProperties.baseUrl + EndpointURLs.getSingleLocation + EnvGlobals.shopifyLocationId);
        ReusableFunctions.thenFunction(200);
        EnvGlobals.actZipCode = ReusableFunctions.getResponsePath("Deliveries.LocationZipCodes.zipcode[0][0]");

        Validations.EditLocation.verifyAddedZipCode();
    }


    @Test
    public static void addInvalidZipCodeInLocation() {
        String Requestpayload = payloads.EditLocation.addInvalidZipCode();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken), Requestpayload);
        ReusableFunctions.whenFunction("put", ConfigProperties.baseUrl + EndpointURLs.addZipCode + EnvGlobals.locationId);
        ReusableFunctions.thenFunction(422);

        Validations.EditLocation.verifyInvalidZipCode(Requestpayload);
    }

}
