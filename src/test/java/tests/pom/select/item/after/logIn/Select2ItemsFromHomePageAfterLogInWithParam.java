package tests.pom.select.item.after.logIn;

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

public class Select2ItemsFromHomePageAfterLogInWithParam extends TestUtil {
    @Test(dataProvider = "correctCredentials")

    public void select2ItemsFromHomePageAfterLogInWithParam (String email, String password) throws InterruptedException {

        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");


        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        MyProfilePage myProfilePage = logInPage.login(email, password); //ако нямаме това = трябват асършани
        myProfilePage.goToHomePage();
        Assert.assertTrue(applicationUrl.equals(applicationUrl));

        HomePage homePageItem1 = new HomePage(driver);
        homePageItem1.selectItem("/html/body/main/section/div[1]/div/div/section/section/div[5]/div/div/section/div/article[1]/div/div[2]/h3" );
        WebElement itemTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(itemTitle.isDisplayed(), "Item Title is not displayed.");

    }

    @DataProvider(name = "correctCredentials")
    public static Object[][] readCorrectCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2]; // 2 стойности в scv

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


