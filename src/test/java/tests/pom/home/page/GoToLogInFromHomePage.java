package tests.pom.home.page;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.time.Duration;

public class GoToLogInFromHomePage extends TestUtil {

    @Test
    public void successfullyGoToLogin() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();
        //Thread.sleep(1000);

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");

        //explicit Wait:
        WebDriverWait wait05 = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait05.until(ExpectedConditions.visibilityOf(emailField));


    }

}
