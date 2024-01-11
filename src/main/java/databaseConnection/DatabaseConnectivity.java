/*
#Database connectivity class
 */

package databaseConnection;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import static Config.ConfigProperties.*;
import static org.apache.poi.ss.usermodel.Cell.*;

public class DatabaseConnectivity {

    private DatabaseConnectivity() {}

    //DB Credentials
    private static String url = dbUrl;
    private static String username = dbUsername;
    private static String password = dbPassword;
    private static Connection connection = null;
    private static Statement statement = null;
    private static int excelSheetColumns = 0;
    private static ArrayList<String> dbColumnsName;

    private static void connectDb(String url, String username, String password) throws ClassNotFoundException,
            SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(url, username, password); //DB connection
    }

    // return the count of table rows
    public static int getDbTableRowsLength(String dbQuery) throws SQLException {
        int count = 0;

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {

        }
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(dbQuery);
        } catch (SQLException e) {

        }

        while (resultSet.next()) {
            count = resultSet.getInt(1);
        }

        resultSet.close();
        return count; //table rows length
    }

    // Get DB row value and add into json object then return it as json array
    public static JSONArray verifyDBColumns(String dbQuery) throws SQLException {
        JSONArray dbTableJsonArray = new JSONArray();

        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM ( %s order by id desc) as T ORDER " +
                        "by id",
                dbQuery));

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        String dbTableColumnValue;
        while (resultSet.next()) {
            JSONObject obj = new JSONObject();
            for (int i = 1; i <= columnCount; i++) {
                dbTableColumnValue = rsmd.getColumnName(i);
                if (rsmd.getColumnType(i) == Types.BIGINT || rsmd.getColumnType(i) == Types.INTEGER) {
                    obj.put(dbTableColumnValue, resultSet.getInt(dbTableColumnValue));
                } else if (rsmd.getColumnType(i) == Types.BOOLEAN) {
                    obj.put(dbTableColumnValue, resultSet.getBoolean(dbTableColumnValue));
                } else {
                    obj.put(dbTableColumnValue, resultSet.getString(dbTableColumnValue));
                }
            }
            dbTableJsonArray.put(obj);
        }

        resultSet.close();
        return dbTableJsonArray;
    }

    public static void dbConnection() {

        try {
            connectDb(url, username, password);


        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Connection Failed!");
            e.printStackTrace();
        }


    }

    private static void setExcelColumnsRowsLength(int excelColumns, int excelRows) {
        excelSheetColumns = excelColumns;
    }

    private static int getExcelColumns(XSSFSheet sheet, int rows) {
        int cols = 0;
        int tmp;
        XSSFRow row;
        for (int i = 0; i < 10 || i < rows; i++) {
            row = sheet.getRow(i);
            if (row != null) {
                tmp = sheet.getRow(i).getPhysicalNumberOfCells();
                if (tmp > cols) cols = tmp;
            }
        }
        return cols;
    }

    private static JSONArray getDbResults(String dbQuery, ArrayList list1) throws SQLException {

        JSONArray dbTableJsonArray = new JSONArray();

        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(dbQuery);

        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();


        for (Object aList1 : list1) {
            for (int j = 1; j < columnCount; j++) {
                if (aList1.equals(rsmd.getColumnName(j))) {
                    dbColumnsName.add(rsmd.getColumnName(j));
                }
            }

        }

        while (resultSet.next()) {
            ArrayList<String> list = new ArrayList<>();

            for (int i = 0; i < excelSheetColumns; i++) {
                list.add(resultSet.getString(dbColumnsName.get(i)));

            }
            dbTableJsonArray.put(list);
        }

        resultSet.close();

        return dbTableJsonArray;
    }

    public static void getDbValues(String sheetPath, String dbQuery) throws SQLException, InvalidFormatException,
            IOException {

        JSONArray dbTableJsonArray = new JSONArray();
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(dbQuery);
        ResultSetMetaData rsmd = resultSet.getMetaData();
        int columnCount = rsmd.getColumnCount();

        JSONArray excelSheetJsonArray = new JSONArray(); // excel sheet json array

        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFRow excelRow;
        XSSFCell cell;
        int sheetRows;
        int excelRowColumns;
        OPCPackage pkg = OPCPackage.open(new File(sheetPath));

        workbook = new XSSFWorkbook(pkg);

        sheet = workbook.getSheetAt(0);

        sheetRows = sheet.getPhysicalNumberOfRows();

        excelRowColumns = getExcelColumns(sheet, sheetRows);

        setExcelColumnsRowsLength(excelRowColumns, sheetRows);

        for (int sheetRow = 1; sheetRow < 2; sheetRow++) {
            ArrayList<Object> excelRowArray = new ArrayList<>();

            excelRow = sheet.getRow(sheetRow);


            if (excelRow != null) {
                for (int excelColumnIndex = 0; excelColumnIndex < excelRowColumns; excelColumnIndex++) {
                    cell = excelRow.getCell((short) excelColumnIndex);

                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case CELL_TYPE_NUMERIC:
                                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                    //Date localTime = (Date) cell.getDateCellValue();
//                                    DateFormat convert = new SimpleDateFormat("EEE MMM d HH:mm:ss z Y", Locale
//                                            .getDefault());

                                    //convert.setTimeZone(TimeZone.getTimeZone("GMT"));
                                    excelRowArray.add(cell.getDateCellValue().toString().replace("PKT", "GMT"));
                                } else {
                                    excelRowArray.add(cell.getNumericCellValue());
                                }
                                break;
                            case CELL_TYPE_STRING:
                                excelRowArray.add(cell.getStringCellValue());
                                break;
                            case CELL_TYPE_BOOLEAN:
                                excelRowArray.add(cell.getBooleanCellValue());
                        }
                    }
                }
            }
            excelSheetJsonArray.put(excelRowArray);
        }


        while (resultSet.next()) {
            ArrayList<Object> list = new ArrayList<>();

            for (int j = 0; j < columnCount; j++) {
                try {
                    list.add(resultSet.getDouble(rsmd.getColumnName(j + 1)));
                } catch (Exception e) {
                    list.add(resultSet.getString(rsmd.getColumnName(j + 1)));
                }
            }

            dbTableJsonArray.put(list);
        }

        resultSet.close();


        //return dbTableJsonArray;

        System.out.println(excelSheetJsonArray);
        System.out.println(dbTableJsonArray);

        JSONAssert.assertEquals(excelSheetJsonArray, dbTableJsonArray, true);

    }

    public static String getRecordId(String dbQuery) throws SQLException {

        String id = "";

        statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(dbQuery);

        while (resultSet.next()) {
            id = resultSet.getString(1);
        }

        resultSet.close();
        return id; //table rows length
    }
}