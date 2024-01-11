/*
# Get properties values and set into variables
*/
package Config;
/*
# Get properties values and set into variables
*/

import ru.qatools.properties.Property;
import ru.qatools.properties.PropertyLoader;
import ru.qatools.properties.Resource.Classpath;

import java.util.ArrayList;

@Classpath({"ApplicationConfig.properties"})

public class ApplicationConfigReader {

    //                                 # --- SONAR DB --- #
    @Property("sonarDbUrl")
    private String sonarDbUrl = "";
    @Property("sonarDbUsername")
    private String sonarDbUsername = "";
    @Property("sonarDbPassword")
    private String sonarDbPassword = "";
    @Property("sonarDbPort")
    private String sonarDbPort = "";


    @Property("authUrl")
    private String authUrl;

    @Property("publicUserLogin")
    private String publicUserLogin;

    @Property("email")
    private String getEmail;

    @Property("resourcesBaseUrl")
    private String resourcesBaseUrl;

    @Property("resourcesserverPort")
    private String resourcesserverPort;

    @Property("baseUrl")
    private String baseUrl;

    @Property("coreBaseUrl")
    private String coreBaseUrl;

    @Property("username")
    private String UserName;

    @Property(("userPhoneNo"))
    private String userPhoneNo;

    @Property("password")
    private String Password;

    @Property("Environment")
    private String Environment;

    @Property("Project")
    private String Project;

    @Property("port")
    private String Port;

    @Property("isEnableReporting")
    private String isEnableReporting;

    @Property("dbUrl")
    private String dbUrl;

    @Property("dbUserName")
    private String dbUserName;

    @Property("grantType")
    private String grantType;

    @Property("dbPassword")
    private String dbPassword;

    @Property("apiVersion")
    private String apiVersion;

    @Property("lookupNames")
    private ArrayList lookupNames;

    @Property("filePath")
    private String sheetPath;


    //For reporting into database

    @Property("ENV")
    private String ENV;

    @Property("GIT_COMMIT")
    private String GIT_COMMIT;

    @Property("GIT_BRANCH")
    private String GIT_BRANCH;

    @Property("BUILD_NUMBER")
    private String BUILD_NUMBER;

    @Property("COMPONENT_ID")
    private String COMPONENT_ID;

    public ApplicationConfigReader() {
        PropertyLoader.newInstance().populate(this);
    }

    public ArrayList getLookupNames() {
        return lookupNames;
    }

    public String getGrantType() {
        return grantType;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getresourcesBaseUrl() {
        return this.resourcesBaseUrl;
    }

    public String getresourcesserverPort() {
        return this.resourcesserverPort;
    }





    public String getUserName() {
        return this.UserName;
    }
    public String getuserPhoneNo()
    { return this.userPhoneNo;  }

    public String getPassword() {
        return this.Password;
    }
    public String getEmail() {
        return this.getEmail;
    }

    public String getEnvironment() {
        return this.Environment;
    }

    public String getProject() {
        return this.Project;
    }

    public String getPort() {
        return this.Port;
    }

    public String getIsEnableReporting() {
        return this.isEnableReporting;
    }

    public String getCoreBaseUrl() {
        return coreBaseUrl;
    }

    public String getFilePath() {
        return System.getProperty("user.dir") + "/files";
    }

    public String getAuthUrl() { return authUrl; }

    public String getpublicUserLogin() { return publicUserLogin; }


    //For reporting into database

    public String getENV() { return ENV; }

    public String getGIT_COMMIT() { return GIT_COMMIT; }

    public String getGIT_BRANCH() { return GIT_BRANCH; }

    public String getBUILD_NUMBER() { return BUILD_NUMBER; }

    public String getCOMPONENT_ID() { return COMPONENT_ID; }

    //           # --- Sonar DB --- #
    public String getSonarDbUrl() { return sonarDbUrl; }
    public String getSonarDbUsername() { return sonarDbUsername; }
    public String getSonarDbPassword() { return sonarDbPassword; }
    public String getSonarDbPort() { return sonarDbPort; }

}