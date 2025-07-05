package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DriverFactory;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class GenderizePage {
    private WebDriver driver = DriverFactory.driver;
    private final By FullNameField = By.id("trial-input");
    private final  By SearchNameButton = By.xpath("//*[@id='trial-input-form']/button\n");

    private final  By GetPrintedInfo = By.xpath("//*[@id='genderize']/main/div[1]/div[1]/div/p\n");
    public void openWebsitePage() throws InterruptedException {
        driver.get("https://genderize.io/");
        waitForLoadingSpinner();
    }

    public void enterName(String name) {
        WebElement nameInput = driver.findElement(FullNameField);
        nameInput.sendKeys(name);
    }
    public void waitForLoadingSpinner() throws InterruptedException {
        waitSeconds(String.valueOf(1));
    }
    public void waitSeconds(String seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(Long.parseLong(seconds));
    }

    public String getMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(GetPrintedInfo));

            wait.until(d -> {
                String text = messageElement.getText();
                return text != null && !text.trim().isEmpty();
            });

            String currentMessage = messageElement.getText().trim();
            System.out.println("Captured Message: " + currentMessage);

            return "==============> " + currentMessage + " <================";

        } catch (StaleElementReferenceException e) {
            System.err.println("[ERROR] Element became stale while retrieving message");
            return "==============> STALE ELEMENT ERROR <================";
        } catch (Exception e) {
            System.err.println("[ERROR] Unexpected error while getting message: " + e.getMessage());
            return "==============> ERROR RETRIEVING MESSAGE <================";
        }
    }
    public void clickSearchButton() throws InterruptedException {
        driver.findElement(SearchNameButton).click();
        System.out.println("Time to wait...\n");
        waitForLoadingSpinner();



    }

}
