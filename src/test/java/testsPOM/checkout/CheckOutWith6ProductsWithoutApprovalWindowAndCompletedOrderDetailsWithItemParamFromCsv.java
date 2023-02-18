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

public class CheckOutWith6ProductsWithoutApprovalWindowAndCompletedOrderDetailsWithItemParamFromCsv extends TestUtil {

    //RESUME: вариант с Assert в края на теста и данни от .csv файл за айтемите  (логинът е хардкорнат)
    // (ако ни гръмне някой тест поради липса на наличност, не знаем кой е продукта) //todo може и да стане с if за колко броя продукти има в количката
    // (проверяваме през теста GoToCheckOutWithOneProductFromEachCategoryWithoutApprovalWindowWithAssertions, който е по-дълъг, но по-надежден)

    @Test(dataProvider = "xPathOneItemFromEachCategoryCsv")//управляваме през тестовите данни (през самите параметри)

    public void checkOutWith6ProductsWithoutApprovalWindowAndCompletedOrderDetailsWithItemParamFromCsv (String xpathGamesAndToys, String xpathGTItem1,
                                                                                                        String xpathCostumesAndRolePlaying, String xpathCRPItem1,
                                                                                                        String xpathAccessories, String xpathAItem1,
                                                                                                        String xpathCreativity, String xpathCItem1,
                                                                                                        String xpathShoesAndSlippers, String xpathSSItem1,
                                                                                                        String xpathStem, String xpathSItem1) throws InterruptedException {
        HomePage homePage = new HomePage(driver); //един page, един обект
        LogInPage logInPage = new LogInPage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);

        Categories gameAndPlay = new Categories(driver);
        ProductPage itemFromGameAndPlay = new ProductPage(driver);

        Categories costumesAndRolePlaying = new Categories(driver);
        ProductPage itemFromCostumesAndRolePlaying = new ProductPage(driver);

        Categories accessories = new Categories(driver);
        ProductPage itemFromAccessories = new ProductPage(driver);

        Categories creativity = new Categories(driver);
        ProductPage itemFromCreativity = new ProductPage(driver);

        Categories shoesAndSlippers = new Categories(driver);
        ProductPage itemFromShoesAndSlippers = new ProductPage(driver);

        Categories stem = new Categories(driver);
        ProductPage itemFromStem = new ProductPage(driver);

        homePage.goToLogin();
        logInPage.login("qa-test1122@abv.bg","test1122");//хардкорваме стойностите, защото нямаме провайдър з тях
        myProfilePage.goToHomePage();

        gameAndPlay.selectCategoryAndItemFromCategory(xpathGamesAndToys, xpathGTItem1);
        itemFromGameAndPlay.goToHomePageAfterAddToCartByClickingOnProductPage();

        costumesAndRolePlaying.selectCategoryAndItemFromCategory(xpathCostumesAndRolePlaying, xpathCRPItem1);
        itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCartByClickingOnProductPage();

        accessories.selectCategoryAndItemFromCategory(xpathAccessories, xpathAItem1);
        itemFromAccessories.goToHomePageAfterAddToCartByClickingOnProductPage();


        creativity.selectCategoryAndItemFromCategory(xpathCreativity, xpathCItem1);
        itemFromCreativity.goToHomePageAfterAddToCartByClickingOnProductPage();

        shoesAndSlippers.selectCategoryAndItemFromCategory(xpathShoesAndSlippers, xpathSSItem1);
        itemFromShoesAndSlippers.goToHomePageAfterAddToCartByClickingOnProductPage();

        stem.selectCategoryAndItemFromCategory(xpathStem, xpathSItem1);
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

        //deliveryDetails:
        CheckOut fillShippingDetails = new CheckOut(driver);
        //fillShippingDetails.fillDeliveryDetails("QA","Test. Test","ул. проф. д-р Г.Павлов17","1111","София", "Това е поредният спам от мен. Като ви писна, кажете си");
        fillShippingDetails.fillDeliveryDetailsWithoutAddress("Това е поредният спам от мен. Като ви писна, кажете си");
    }

    @DataProvider(name = "xPathOneItemFromEachCategoryCsv") //името на DataProvider, който ще използваме
    public static Object[][] readXPathOneItemFromEachCategoryCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/oneItemFromEachCategory.csv")); // има ексепшън, който трябва да хванем (IOException)
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

