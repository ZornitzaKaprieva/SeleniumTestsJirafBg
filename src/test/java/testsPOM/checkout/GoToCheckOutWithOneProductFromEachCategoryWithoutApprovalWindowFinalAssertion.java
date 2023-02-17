package testsPOM.checkout;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class GoToCheckOutWithOneProductFromEachCategoryWithoutApprovalWindowFinalAssertion extends TestUtil {

    //вариант с Assert на накрая:(следва да направя тест с подаване на параметри от .csv 6 реда с категориите и xpath на продуктите)
    @Test(dataProvider = "correctCredentials")//управляваме през тестовите данни (през самите параметри)

    public void goToCheckOutWithOneProductFromEachCategoryWithoutApprovalWindow (String email, String password) throws InterruptedException {
        HomePage homePage = new HomePage(driver); //един page, един обект
        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        MyProfilePage myProfilePage = new MyProfilePage(driver);

        Categories gameAndPlay = new Categories(driver);
        Categories item1GameAndPlay = new Categories(driver);
        ProductPage itemFromGameAndPlay = new ProductPage(driver);

        Categories costumesAndRolePlaying = new Categories(driver);
        Categories itemCostumesAndRolePlaying = new Categories(driver);
        ProductPage itemFromCostumesAndRolePlaying = new ProductPage(driver);

        Categories accessories = new Categories(driver);
        Categories itemAccessories = new Categories(driver);
        ProductPage itemFromAccessories = new ProductPage(driver);

        Categories creativity = new Categories(driver);
        Categories itemCreativity = new Categories(driver);
        ProductPage itemFromCreativity = new ProductPage(driver);

        Categories shoesAndSlippers = new Categories(driver);
        Categories itemShoesAndSlippers = new Categories(driver);
        ProductPage itemFromShoesAndSlippers = new ProductPage(driver);

        Categories stem = new Categories(driver);
        Categories itemStem = new Categories(driver);
        ProductPage itemFromStem = new ProductPage(driver);

        homePage.goToLogin();
        logInPage.login(email, password);
        myProfilePage.goToHomePage();

        gameAndPlay.selectCategory("[1]");
        item1GameAndPlay.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[1]/div");
        itemFromGameAndPlay.goToHomePageAfterAddToCartByClickingOnProductPage();

        costumesAndRolePlaying.selectCategory("[2]");
        itemCostumesAndRolePlaying.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[3]/div/div[1]/a");
        itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCartByClickingOnProductPage();

        accessories.selectCategory("[3]");
        itemAccessories.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[1]/div/div[2]/h3/a");
        itemFromAccessories.goToHomePageAfterAddToCartByClickingOnProductPage();


        creativity.selectCategory("[4]");
        itemCreativity.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[14]/div/div[2]/h3/a");
        itemFromCreativity.goToHomePageAfterAddToCartByClickingOnProductPage();

        shoesAndSlippers.selectCategory("[5]");
        itemShoesAndSlippers.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[3]/div/div[2]/h3/a");
        itemFromShoesAndSlippers.goToHomePageAfterAddToCartByClickingOnProductPage();

        stem.selectCategory("[6]");
        itemStem.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[1]/div/div[2]/h3/a");
        itemFromStem.goToHomePageAfterAddToCartByClickingOnProductPage();

        //final Assertion:
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 6", "Problem with addToCartCounter(itemFromStem)");

        //to checkout:
        homePage.goToCartFromHomePage();

        CartPage newPurchase = new CartPage(driver);
        newPurchase.goToCheckOutAfterAddToCart();
        WebElement personalInfoTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div/div[1]/section[1]/h1"));
        Assert.assertTrue(personalInfoTitle.isDisplayed());

        //explicit Wait:
        WebDriverWait wait05 = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait05.until(ExpectedConditions.visibilityOf(personalInfoTitle));
    }

    @DataProvider(name = "correctCredentials") //името на DataProvider, който ще използваме
    public static Object[][] readCorrectCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv")); // има ексепшън, който трябва да хванем (IOException)
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

