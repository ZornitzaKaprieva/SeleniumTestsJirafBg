package tests.pom.logIn;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.MyProfilePage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class LogInTestWrongCredentials extends TestUtil {

    @Test (dataProvider = "wrongCredentials")
    public void loginWithWrongCredentials (String email, String password) throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");


        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        logInPage.login(email, password);
        //Thread.sleep(1000);

        WebElement errorMsg = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/section/section/div/ul/li"));
        Assert.assertTrue(errorMsg.isDisplayed(), "Error message is not displayed.");
    }

    //@DataProvider(name = "wrongCredentials"): moved to TestUtil
//    @DataProvider(name = "wrongCredentials")
//    public static Object[][] readWrongCredentialsFromCsv(){
//        try{
//            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongCredentials.csv"));
//            List<String[]> csvData = csvReader.readAll();
//            Object[][] csvDataObject = new Object[csvData.size()][2]; //само 2 стойности в scv
//
//            for (int i = 0; i < csvData.size(); i++) {
//                csvDataObject[i] = csvData.get(i);
//            }
//            return csvDataObject;
//
//        }catch (IOException e){
//            System.out.println("Not possible to find CSV!");
//            return null;
//        } catch (CsvException e){
//            System.out.println("Something went wrong!");
//            return null;
//        }
//    }
}
