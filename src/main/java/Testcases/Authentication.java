package Testcases;

import Config.ConfigProperties;
import Config.EndpointURLs;
import Config.EnvGlobals;
import Config.ReusableFunctions;
import org.testng.annotations.Test;

public class Authentication  {


    @Test
    public static void Authorization() {
       String Requestpayload = payloads.Authentication.publicUserLogin( ConfigProperties.email,ConfigProperties.password );

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers(), Requestpayload);
        ReusableFunctions.whenFunction("post", ConfigProperties.baseUrl + EndpointURLs.loginUser);
        ReusableFunctions.thenFunction(200);
        EnvGlobals.authorizationToken = "bearer " + EnvGlobals.response.body().path("token").toString();
        System.out.println( EnvGlobals.authorizationToken );

        Validations.Authentication.VerifyAuthorization(Requestpayload);
    }



    @Test
    public static void authorization_With_InvalidEmail() {

        ReusableFunctions.givenHeaderPayload(ReusableFunctions.headers(), payloads.Authentication.invalidLoginCreds());
        ReusableFunctions.whenFunction("post", ConfigProperties.baseUrl + EndpointURLs.loginUser);
        ReusableFunctions.thenFunction(404);

        Validations.Authentication.VerifyinvalidAuthorization();
    }



}