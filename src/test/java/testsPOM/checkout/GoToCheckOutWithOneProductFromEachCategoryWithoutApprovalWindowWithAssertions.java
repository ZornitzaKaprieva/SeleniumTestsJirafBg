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

public class GoToCheckOutWithOneProductFromEachCategoryWithoutApprovalWindowWithAssertions extends TestUtil {

    //вариант с Assert на всяко действие:

    @Test(dataProvider = "correctCredentials")//управляваме през тестовите данни (през самите параметри)

    public void goToCheckOutWithOneProductFromEachCategoryWithoutApprovalWindowWithAssertions (String email, String password) throws InterruptedException {
        HomePage homePage = new HomePage(driver); //един page, един обект
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");

        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        MyProfilePage myProfilePage = logInPage.login(email, password); //ако нямаме това = трябват асършани todo
        myProfilePage.goToHomePage();
        Assert.assertTrue(applicationUrl.equals(applicationUrl));

        //item from first category:
        Categories gameAndPlay = new Categories(driver);
        gameAndPlay.selectCategory("[1]");
        WebElement gameAndPlayTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(gameAndPlayTitle.isDisplayed());

        Categories item1GameAndPlay = new Categories(driver);
        item1GameAndPlay.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[1]/div");
        WebElement itemTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(itemTitle.isDisplayed(), "Item1 Title is not displayed.");

        ProductPage itemFromGameAndPlay = new ProductPage(driver);
        //itemFromGameAndPlay.goToHomePageAfterAddToCart();//todo
        itemFromGameAndPlay.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromGameAndPlay.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter");

        //item from second category:
        Categories costumesAndRolePlaying = new Categories(driver);
        costumesAndRolePlaying.selectCategory("[2]");
        WebElement costumesAndRolePlayingTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(costumesAndRolePlayingTitle.isDisplayed());

        Categories itemCostumesAndRolePlaying = new Categories(driver);
        itemCostumesAndRolePlaying.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[3]/div/div[1]/a");
        WebElement item2Title = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(item2Title.isDisplayed(), "Item2 Title is not displayed.");

        ProductPage itemFromCostumesAndRolePlaying = new ProductPage(driver);
        //itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCart();//todo
        itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromCostumesAndRolePlaying.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter");

        //item from third category:
        Categories accessories = new Categories(driver);
        accessories.selectCategory("[3]");
        WebElement accessoriesTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(accessoriesTitle.isDisplayed());

        Categories itemAccessories = new Categories(driver);
        itemAccessories.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[1]/div/div[2]/h3/a");
        WebElement item3Title = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(item3Title.isDisplayed(), "Item3 Title is not displayed.");

        ProductPage itemFromAccessories = new ProductPage(driver);
        //itemFromAccessories.goToHomePageAfterAddToCart();//todo
        itemFromAccessories.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");

        //item from fourth category:
        Categories creativity = new Categories(driver);
        creativity.selectCategory("[4]");
        WebElement selectCategoryTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(selectCategoryTitle.isDisplayed());

        Categories itemCreativity = new Categories(driver);
        itemCreativity.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[1]/div/div[2]/h3/a");
        WebElement item4Title = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(item4Title.isDisplayed(), "Item4 Title is not displayed.");

        ProductPage itemFromCreativity = new ProductPage(driver);
        //itemFromCreativity.goToHomePageAfterAddToCart();//todo
        itemFromCreativity.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 4", "Problem with addToCartCounter(itemFromCreativity)");

        //item from fifth category:
        Categories shoesAndSlippers = new Categories(driver);
        shoesAndSlippers.selectCategory("[5]");
        WebElement shoesAndSlippersTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(shoesAndSlippersTitle.isDisplayed());

        Categories itemShoesAndSlippers = new Categories(driver);
        itemShoesAndSlippers.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[3]/div/div[2]/h3/a");
        WebElement item5Title = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(item5Title.isDisplayed(), "Item5 Title is not displayed.");

        ProductPage itemFromShoesAndSlippers = new ProductPage(driver);
        //itemFromShoesAndSlippers.goToHomePageAfterAddToCart();//todo
        itemFromShoesAndSlippers.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 5", "Problem with addToCartCounter(itemFromShoesAndSlippers)");

        //item from sixth category:
        Categories stem = new Categories(driver);
        stem.selectCategory("[6]");
        WebElement stemTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(stemTitle.isDisplayed());

        Categories itemStem = new Categories(driver);
        itemStem.selectItemFromCategory("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[2]/div/div[2]/h3/a"); //острилка
        WebElement item6Title = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
        Assert.assertTrue(item6Title.isDisplayed(), "Item6 Title is not displayed.");

        ProductPage itemFromStem = new ProductPage(driver);
        //itemFromStem.goToHomePageAfterAddToCart();//todo
        itemFromStem.goToHomePageAfterAddToCartByClickingOnProductPage();
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

