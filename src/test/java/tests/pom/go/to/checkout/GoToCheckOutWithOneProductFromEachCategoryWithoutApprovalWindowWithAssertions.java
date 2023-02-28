package tests.pom.go.to.checkout;

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

    //RESUME: вариант с Assert на всяко действие и данни от .csv файл за айтемите (логинът е хардкорнат)
    // (ако ни гръмне някой тест поради липса на наличност, тук лесно можем да проверим кой е продуктът:

    @Test(dataProvider = "xPathOneItemFromEachCategoryCsv")

    public void goToCheckOutWithOneProductFromEachCategoryWithoutApprovalWindowWithAssertions(String xpathGamesAndToys, String xpathGTItem1,
                                                                                              String xpathCostumesAndRolePlaying, String xpathCRPItem1,
                                                                                              String xpathAccessories, String xpathAItem1,
                                                                                              String xpathCreativity, String xpathCItem1,
                                                                                              String xpathShoesAndSlippers, String xpathSSItem1,
                                                                                              String xpathStem, String xpathSItem1) throws InterruptedException {
        HomePage homePage = new HomePage(driver); //един page, един обект
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");

        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        MyProfilePage myProfilePage = logInPage.login("qa-test1122@abv.bg", "test1122"); //ако нямаме това = трябват асършани todo
        myProfilePage.goToHomePage();
        Assert.assertTrue(applicationUrl.equals(applicationUrl));

        //item from first category:
        Categories gameAndPlay = new Categories(driver);
        ProductPage itemFromGameAndPlay = new ProductPage(driver);
        gameAndPlay.selectCategoryAndItemFromCategory(xpathGamesAndToys, xpathGTItem1);
        itemFromGameAndPlay.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromGameAndPlay.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter");

        //item from second category:
        Categories costumesAndRolePlaying = new Categories(driver);
        ProductPage itemFromCostumesAndRolePlaying = new ProductPage(driver);
        costumesAndRolePlaying.selectCategoryAndItemFromCategory(xpathCostumesAndRolePlaying, xpathCRPItem1);
        itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromCostumesAndRolePlaying.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter");

        //item from third category:
        Categories accessories = new Categories(driver);
        ProductPage itemFromAccessories = new ProductPage(driver);
        accessories.selectCategoryAndItemFromCategory(xpathAccessories, xpathAItem1);
        itemFromAccessories.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");

        //item from fourth category:
        Categories creativity = new Categories(driver);
        ProductPage itemFromCreativity = new ProductPage(driver);
        creativity.selectCategoryAndItemFromCategory(xpathCreativity, xpathCItem1);
        itemFromCreativity.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 4", "Problem with addToCartCounter(itemFromCreativity)");

        //item from fifth category:
        Categories shoesAndSlippers = new Categories(driver);
        ProductPage itemFromShoesAndSlippers = new ProductPage(driver);
        shoesAndSlippers.selectCategoryAndItemFromCategory(xpathShoesAndSlippers, xpathSSItem1);
        itemFromShoesAndSlippers.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 5", "Problem with addToCartCounter(itemFromShoesAndSlippers)");

        //item from sixth category:
        Categories stem = new Categories(driver);
        ProductPage itemFromStem = new ProductPage(driver);
        stem.selectCategoryAndItemFromCategory(xpathStem, xpathSItem1);
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

    //    @DataProvider(name = "xPathOneItemFromEachCategoryCsv"): moved to TestUtil
//    @DataProvider(name = "xPathOneItemFromEachCategoryCsv")
//    public static Object[][] readXPathOneItemFromEachCategoryCsv() {
//        try {
//            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/oneItemFromEachCategory.csv"));
//            List<String[]> csvData = csvReader.readAll();
//            Object[][] csvDataObject = new Object[csvData.size()][csvData.size()]; //няма да хардкорнем редовете и стойностите, в случай че се променят.
//
//            for (int i = 0; i < csvData.size(); i++) {
//                csvDataObject[i] = csvData.get(i);
//            }
//            return csvDataObject;
//
//        } catch (IOException e) {
//            System.out.println("Not possible to find CSV!");
//            return null;
//        } catch (CsvException e) {
//            System.out.println("Something went wrong!");
//            return null;
//        }
//
//    }
}
