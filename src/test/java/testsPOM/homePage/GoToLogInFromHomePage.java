package testsPOM.homePage;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class GoToLogInFromHomePage extends TestUtil {

    @Test
    public void successGoToLogin() throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();
        //Thread.sleep(1000);

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");

    }

}
