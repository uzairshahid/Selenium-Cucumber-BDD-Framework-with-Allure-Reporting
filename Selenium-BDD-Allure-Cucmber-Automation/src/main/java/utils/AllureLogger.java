package utils;

import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureLogger {
    private static final Logger logger = LogManager.getLogger(AllureLogger.class);

    public static void info(String message) {
        logger.info(message);
    }

    public static void debug(String message) {
        logger.debug(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void attachScreenshot(WebDriver driver, String name) {
        if (driver instanceof TakesScreenshot) {
            String screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            Allure.addAttachment(name, "image/png", screenshot);
        }
    }
}