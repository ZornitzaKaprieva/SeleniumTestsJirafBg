package tests.pom.go.to.checkout;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class GoToCheckOutWithDifferentItemFromDifferentCategoriesAfterLogin extends TestUtil {

    //RESUME: вариант с Assert на всяка стъпка + full xPath
    // да се добави в количката през approval window todo

    @Test(dataProvider = "correctCredentials")

    public void goToCheckOutWithDifferentItemFromDifferentCategoriesAfterLogin (String email, String password) {
        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");

        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        MyProfilePage myProfilePage = logInPage.login(email, password); //ако нямаме това = трябват асършани
        myProfilePage.goToHomePage();
        Assert.assertTrue(applicationUrl.equals(applicationUrl));

        Categories gameAndPlay = new Categories(driver);
        gameAndPlay.selectCategory("[1]");
        WebElement gameAndPlayTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(gameAndPlayTitle.isDisplayed());

        Categories item1GameAndPlay = new Categories(driver);
        item1GameAndPlay.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[1]/div");
        WebElement itemTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(itemTitle.isDisplayed(), "Item Title is not displayed.");

        ProductPage itemFromGameAndPlay = new ProductPage(driver);
        itemFromGameAndPlay.selectItemAndAddToCart();

        WebElement successfullyAddToCartMsg = driver.findElement(By.xpath("/html/body/div[6]/div/div/div[1]/button/span"));
        Assert.assertTrue(successfullyAddToCartMsg.isEnabled(), "successfullyAddToCartMsg is not displayed");

        itemFromGameAndPlay.addToCartAndContinueToShopping(); //todo за повече от 1 айтем


    }

    //    @DataProvider(name = "correctCredentials"): moved to TestUtil
//    @DataProvider(name = "correctCredentials")
//    public static Object[][] readCorrectCredentialsFromCsv(){
//        try{
//            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv"));
//            List<String[]> csvData = csvReader.readAll();
//            Object[][] csvDataObject = new Object[csvData.size()][2]; //В случая имаме само 2 стойности в scv, затова можем да ги хардкорнем, но не можем да хардкорнем редовете, защото те се променят
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
//
//    }
}

