import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import javax.imageio.ImageIO;

public class HtmlUnitDriverTest {

    private HtmlUnitDriver driver;

    private static final String BASE_URL = "https://mail.ru";
    private static final String USERNAME = "seleniumtests10@mail.ru";
    private static final String PASSWORD = "060788avavav";
    private static final By MAILBOX_LOGIN = By.id("mailbox__login");
    private static final By MAILBOX_PASSWORD = By.id("mailbox__password");
    private static final By MAILBOX_AUTH_BUTTON = By.id("mailbox__auth__button");
    private static final By EMAIL_NAME = By.id("PH_user-email");
    private static final String EMAIL_NAME_STRING = "x-ph__menu__button__text x-ph__menu__button__text_auth";

    @BeforeMethod
    public void beforeMethod() {

        driver = new HtmlUnitDriver();
        driver.setJavascriptEnabled(true);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void main() throws IOException {

        driver.get(BASE_URL);

        WebElement usernameElement = driver.findElement(MAILBOX_LOGIN);
        usernameElement.sendKeys(USERNAME);
        WebElement passwordElement = driver.findElement(MAILBOX_PASSWORD);
        passwordElement.sendKeys(PASSWORD);
        WebElement loginElement = driver.findElement(MAILBOX_AUTH_BUTTON);
        loginElement.click();

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        // Now you can do whatever you need to do with it, for example copy somewhere
        try {
            FileUtils.copyFile(scrFile, new File("c:\\tmp\\screenshotHTML.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebElement logoutButton = driver.findElement(EMAIL_NAME);

        Assert.assertEquals(logoutButton.getAttribute("class").toString(), EMAIL_NAME_STRING);
    }

    @AfterMethod
    public void afterMethod() {

        driver.quit();

    }
}