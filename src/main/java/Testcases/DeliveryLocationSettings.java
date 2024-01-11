package Testcases;

import Config.ConfigProperties;
import Config.EndpointURLs;
import Config.EnvGlobals;
import Config.ReusableFunctions;
import general.BaseTest;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import static Testcases.EditLocation.getAllLocations;
import static Validations.DeliveryLocationSettings.slotsList;

public class DeliveryLocationSettings extends BaseTest {

    @Test
    public static void addDeliverySlotForWeek() {
        String Requestpayload = payloads.DeliveryLocationSettings.addDeliverySlotForAweek();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken),Requestpayload);
        ReusableFunctions.whenFunction("put", ConfigProperties.baseUrl + EndpointURLs.addSlot);
        ReusableFunctions.thenFunction(200);
        EnvGlobals.actDeliveryId = ReusableFunctions.getResponsePath("delivery_id[0]");

       Validations.DeliveryLocationSettings.verifyAddDeliverySlotForWeek(Requestpayload);
    }


    @Test
    public static void addDeliveryForSingeDay() {
        String Requestpayload = payloads.DeliveryLocationSettings.addDeliverySlotForSingleDay();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ),Requestpayload);
        ReusableFunctions.whenFunction( "put", ConfigProperties.baseUrl + EndpointURLs.addSlot);
        ReusableFunctions.thenFunction( 200 );
        EnvGlobals.slotId = ReusableFunctions.getResponsePath("id");

        Validations.DeliveryLocationSettings.verifyAddedDeliveryForSingeDay();
    }

    @Test
    public static void addAlreadyAddedDeliverySlot() {

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ),payloads.DeliveryLocationSettings.addDeliverySlotForSingleDay());
        ReusableFunctions.whenFunction( "put", ConfigProperties.baseUrl + EndpointURLs.addSlot);
        ReusableFunctions.thenFunction( 400 );
        EnvGlobals.slotId = ReusableFunctions.getResponsePath("id");

        Validations.DeliveryLocationSettings.verifyDeliverySlotConflictMsg();
    }


    @Test(dependsOnMethods = "getSingleLocation")
    public static void deleteDeliverySlot() {

        ReusableFunctions.givenHeaders(ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ));
        ReusableFunctions.whenFunction( "delete", ConfigProperties.baseUrl + EndpointURLs.deleteSlot + EnvGlobals.deliverySlotId);
        ReusableFunctions.thenFunction( 200 );

        Validations.DeliveryLocationSettings.verifyDeleteDeliverySlot();
    }


    @Test
    public static void addBlackoutDate() {
        String Requestpayload = payloads.DeliveryLocationSettings.addBlackoutDate();

        System.out.println( EnvGlobals.deliveryId);
        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers("Authorization", EnvGlobals.authorizationToken),Requestpayload);
        ReusableFunctions.whenFunction( "put", ConfigProperties.baseUrl + EndpointURLs.addBlackoutDate + EnvGlobals.deliveryId);
        ReusableFunctions.thenFunction( 200 );

        Validations.DeliveryLocationSettings.verifyAddedBlackoutDate();
    }



    @Test(dependsOnMethods = "getSingleLocation")
    public static void deleteBlackoutDate() {

        ReusableFunctions.givenHeaders(ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ));
        ReusableFunctions.whenFunction( "delete", ConfigProperties.baseUrl + EndpointURLs.deleteBlackoutDate + EnvGlobals.deliveryId + "/" + EnvGlobals.blackoutId );
        ReusableFunctions.thenFunction( 200 );
        EnvGlobals.actBlackoutDelMsg = ReusableFunctions.getResponsePath("message");

        Validations.DeliveryLocationSettings.verifyDeleteBlackoutDate();
    }


    @Test
    public static void updateDeliverySurcharge() {
        String Requestpayload = payloads.DeliveryLocationSettings.updateDeliverySurcharge();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ),Requestpayload);
        ReusableFunctions.whenFunction( "patch", ConfigProperties.baseUrl + EndpointURLs.updateDeliverySurcharge);
        ReusableFunctions.thenFunction( 200 );

        Validations.DeliveryLocationSettings.verifyUpdateDeliverySurcharge();
    }

    @Test
    public static void updateFutureDateRange() {
        String Requestpayload = payloads.DeliveryLocationSettings.updateFutureDateRange();

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ),Requestpayload);
        ReusableFunctions.whenFunction( "patch", ConfigProperties.baseUrl + EndpointURLs.updateFutureDateRange);
        ReusableFunctions.thenFunction( 200 );

        Validations.DeliveryLocationSettings.verifyUpdateFutureDateRange();
    }

    @Test
    public static void getSingleLocation() {
        ReusableFunctions.givenHeaders( ReusableFunctions.headers( "Authorization", EnvGlobals.
                authorizationToken ) );
        ReusableFunctions.whenFunction( "get", ConfigProperties.baseUrl + EndpointURLs.getSingleLocation + EnvGlobals.shopifyLocationId );
        ReusableFunctions.thenFunction( 200 );
        EnvGlobals.sameDaySurchrage = ReusableFunctions.getResponsePath( "Deliveries.same_day_surcharge" );
        EnvGlobals.nextDaySurcharge = ReusableFunctions.getResponsePath( "Deliveries.next_day_surcharge" );
        EnvGlobals.futureDateRange = ReusableFunctions.getResponsePath( "Deliveries.future_date_range" );
        EnvGlobals.blackoutId = ReusableFunctions.getResponsePath( "Deliveries.DeliveryBlackouts.id[0][0]" );
        EnvGlobals.deliverySlotId = ReusableFunctions.getResponsePath( "Deliveries[0].DeliverySlots[0].id" );
        EnvGlobals.slotSize = ReusableFunctions.getResponseLengthByKey( "Deliveries[0].DeliverySlots.size()" );

        System.out.println(EnvGlobals.slotSize);
        Validations.DeliveryLocationSettings.getListOfDeliverySlots();
    }

    @Test
    public static void preReqForDeliverySlots() {
        //getAllLocations();

        ReusableFunctions.givenHeaders( ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ) );
        ReusableFunctions.whenFunction( "get", ConfigProperties.baseUrl + EndpointURLs.getSingleLocation + EnvGlobals.shopifyLocationId );
        ReusableFunctions.thenFunction( 200 );

        if(ReusableFunctions.getResponsePath( "Deliveries[0].DeliverySlots.size()").equals("0"))
        {
            throw new SkipException("List is empty");
        }

        else {
            EnvGlobals.deliverySlotId = ReusableFunctions.getResponsePath( "Deliveries[0].DeliverySlots[0].id" );
            EnvGlobals.slotSize = ReusableFunctions.getResponseLengthByKey( "Deliveries[0].DeliverySlots.size()" );

            System.out.println( EnvGlobals.slotSize );
            Validations.DeliveryLocationSettings.getListOfDeliverySlots();
            deleteAllDeliverySlots();
        }
    }


    public static void deleteAllDeliverySlots() {

            for (int i = 0; i < EnvGlobals.slotSize; i++) {

                ReusableFunctions.givenHeaders(ReusableFunctions.headers( "Authorization", EnvGlobals.authorizationToken ));
                ReusableFunctions.whenFunction( "delete", ConfigProperties.baseUrl + EndpointURLs.deleteSlot + slotsList.get( i ));
                ReusableFunctions.thenFunction( 200 );

                Validations.DeliveryLocationSettings.verifyDeleteDeliverySlot();
            }
    }

}
