package general;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Screenshots {
    private static ExtentTest logger;
    private Screenshots() {
        throw new IllegalStateException("Utility class");
    }

    public static final String takeScreenshot(String test) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_hh-mm-ss"); //colon removed, windows not support colon
        Calendar now = Calendar.getInstance();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,e);
        }
        BufferedImage screenShot = robot.createScreenCapture(new java.awt.Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        String filePath = System.getProperty("user.dir") + "/screenshots/" + test + "_" + formatter.format(now.getTime())+".jpg";

        try {
            ImageIO.write(screenShot, "JPG", new File(filePath));
        } catch (Exception e) {
            logger.log(LogStatus.FAIL,e);
        }

       filePath = filePath.replace(System.getProperty("user.dir"), "..");
        return filePath;
    }
}
