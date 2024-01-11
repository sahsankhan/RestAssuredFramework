package payloads;

public class Authentication {


    public static String publicUserLogin(String email, String password) {
        return "{\n" +
                "\t\"email\": \""+ email +"\", \n" +
                "\t\"password\": \""+ password +"\"\n" +
                "}";
    }


    public static String invalidLoginCreds(){
        return "{\n" +
                "\t\"email\": \"test@vd.com\", \n" +
                "\t\"password\": \"pass123\"\n" +
                "}";
    }
}
