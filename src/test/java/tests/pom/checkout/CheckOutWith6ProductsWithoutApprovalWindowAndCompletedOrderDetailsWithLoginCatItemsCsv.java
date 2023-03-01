package tests.pom.checkout;

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

public class CheckOutWith6ProductsWithoutApprovalWindowAndCompletedOrderDetailsWithLoginCatItemsCsv extends TestUtil {

    //RESUME: вариант с данни от .csv файл за айтемите  (логинът е хардкорнат)
    //todo може ли да стане с if за колко броя продукти има в количката

    @Test(dataProvider = "loginCategoriesItems")

    public void checkOutWith6ProductsWithoutApprovalWindowAndCompletedOrderDetailsWithLoginCatItemsCsv (String email, String pass,
                                                                                                        String xpathGamesAndToys, String xpathGTItem1,
                                                                                                        String xpathCostumesAndRolePlaying, String xpathCRPItem1,
                                                                                                        String xpathAccessories, String xpathAItem1,
                                                                                                        String xpathCreativity, String xpathCItem1,
                                                                                                        String xpathShoesAndSlippers, String xpathSSItem1,
                                                                                                        String xpathStem, String xpathSItem1) {
        HomePage homePage = new HomePage(driver);
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
        logInPage.login(email, pass);
        myProfilePage.goToHomePage();

        gameAndPlay.selectCategoryAndItemFromCategory(xpathGamesAndToys, xpathGTItem1);
        itemFromGameAndPlay.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromGameAndPlay.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter(itemFromStem)");

        costumesAndRolePlaying.selectCategoryAndItemFromCategory(xpathCostumesAndRolePlaying, xpathCRPItem1);
        itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromCostumesAndRolePlaying.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter(itemFromStem)");

        accessories.selectCategoryAndItemFromCategory(xpathAccessories, xpathAItem1);
        itemFromAccessories.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter(itemFromStem)");



        creativity.selectCategoryAndItemFromCategory(xpathCreativity, xpathCItem1);
        itemFromCreativity.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromCreativity.getHowManyItemsInTheCart(), "КОЛИЧКА: 4", "Problem with addToCartCounter(itemFromStem)");

        shoesAndSlippers.selectCategoryAndItemFromCategory(xpathShoesAndSlippers, xpathSSItem1);
        itemFromShoesAndSlippers.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromShoesAndSlippers.getHowManyItemsInTheCart(), "КОЛИЧКА: 5", "Problem with addToCartCounter(itemFromStem)");

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

        //deliveryDetails: Note! ако дава грешка с fillShippingDetails.fillDeliveryDetails(), използвай fillShippingDetails.fillDeliveryDetailsWithoutAddress
        CheckOut fillShippingDetails = new CheckOut(driver);
        //fillShippingDetails.fillDeliveryDetails("QA","Test. Test","ул. проф. д-р Г.Павлов17","1111","София", "Това е поредният спам от мен. Като ви писна, кажете си");
        fillShippingDetails.fillDeliveryDetailsWithoutAddress("Това е поредният спам от мен. Като ви писна, кажете си");
    }

    //@DataProvider(name = "loginCategoriesItems"): moved to TestUtil
//    @DataProvider(name = "loginCategoriesItems")
//    public static Object[][] readLoginCategoriesItemsCsv(){
//        try{
//            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/loginCategoriesItems.csv")); // има ексепшън, който трябва да хванем (IOException)
//            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
//            Object[][] csvDataObject = new Object[csvData.size()][csvData.size()]; // няма да хардкорнем редовете и стойностите, защото може да се променят
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

