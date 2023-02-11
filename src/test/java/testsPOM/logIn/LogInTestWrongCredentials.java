package testsPOM.logIn;

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

public class LogInTestWrongCredentials extends TestUtil { //extends TestUtil, защото там инициализираме драйвъра

    @Test (dataProvider = "wrongCredentials")//управляваме през тестовите данни (през самите параметри)
    public void loginWithWrongCredentials (String email, String password) throws InterruptedException {

        HomePage homePage = new HomePage(driver); //един page, един обект
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");


        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        MyProfilePage myProfilePage = logInPage.login(email, password); //ако нямаме това = трябват асършани todo
        //Thread.sleep(1000);
    }

    @DataProvider(name = "wrongCredentials") //името на DataProvider, който ще използваме
    public static Object[][] readWrongCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongCredentials.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][2]; //все едно това ни е броя на редовете в scv. В случая имаме само 2 стойности в scv, затова можем да ги хардкорнем, но не можем да хардкорнем редовете, защото те се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }
    }
}
