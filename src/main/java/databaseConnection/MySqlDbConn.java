package databaseConnection;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static Config.ConfigProperties.appConfig;

public class MySqlDbConn {


    private MySqlDbConn() {}
    public static Connection dbConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("automation_user");
        dataSource.setPassword("kd2R:C)6BEfdAZ]}");
        dataSource.setServerName("sonar-metrics.c87wxijx5ezi.us-east-1.rds.amazonaws.com");
        dataSource.setPortNumber(3306);
        Connection conn = dataSource.getConnection();
        return  conn;
    }

    public static List<String> getProjectsList() throws SQLException {
        List<String> projects = new ArrayList<String>();

        Connection conn =  dbConnection();

        Statement stmt = conn.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM Automation.AutomationReport");
        rs.setFetchSize(100);
        while (rs.next()){
            projects.add(rs.getString("Project_Name"));
        }
        rs.close();
        stmt.close();
        conn.close();

        return projects;
    }


    public static void insertReportingDataIntoDB(Date exec_date, Integer passedCount, Integer failedCount, Integer skippedCount, Date start_time, Date end_time) throws  SQLException{
        Connection conn =  dbConnection();

        String execution_Date = new SimpleDateFormat("yyyy-MM-dd")
                .format(new Date(exec_date.getTime()));

        String Start_Date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                .format(new Date(start_time.getTime()));

        String End_Date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                .format(new Date(end_time.getTime()));


        Statement stmt = conn.createStatement();
//        stmt.executeUpdate("INSERT INTO `Automation`.`AutomationReport` (`Project_Name`, `Platform`, `Build`, `Commit_Number`, `Branch_Name`, `Execution_Date` , `Total_Cases`, `Passed`, `Failed`, `Skipped`, `Execution_Start_Time`, `Execution_End_Time`) VALUES ('"+appConfig.getProject()+"', 'BE', '"+appConfig.getBUILD_NUMBER()+"', '"+appConfig.getGIT_COMMIT()+"', '"+appConfig.getGIT_BRANCH()+"', '"+execution_Date+"' , '"+ (passedCount+failedCount+skippedCount)  +"', '"+passedCount+"', '"+failedCount+"', '"+skippedCount+"', '"+Start_Date+"', '"+End_Date+"');");
        stmt.executeUpdate("INSERT INTO `sonardb`.`automation_report` (`Project_Name`, `Platform`, `Build`, `Environment`, `Component_id`, `Commit_Number`, `Branch_Name`, `Execution_Date` , `Total_Cases`, `Passed`, `Failed`, `Skipped`, `Execution_Start_Time`, `Execution_End_Time`) VALUES ('"+appConfig.getProject()+"', 'BE', '"+appConfig.getBUILD_NUMBER()+"', '"+appConfig.getENV()+"', '"+appConfig.getCOMPONENT_ID()+"', '"+appConfig.getGIT_COMMIT()+"', '"+appConfig.getGIT_BRANCH()+"', '"+execution_Date+"' , '"+ (passedCount+failedCount+skippedCount)  +"', '"+passedCount+"', '"+failedCount+"', '"+skippedCount+"', '"+Start_Date+"', '"+End_Date+"');");
        //rs.close();
        stmt.close();
        conn.close();
    }

}
