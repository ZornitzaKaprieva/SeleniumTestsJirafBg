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

public class CheckOutWith6ProductsWithoutApprovalWindowAndCompletedOrderDetails extends TestUtil {


    //вариант с Assert накрая + методът public void selectCategoryAndItemFromCategory(String categoryXpath, String itemXPath){}
    @Test(dataProvider = "correctCredentials")

    public void checkOutWith6ProductsWithoutApprovalWindowAndCompletedOrderDetails (String email, String password) throws InterruptedException {

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
        logInPage.login(email, password);
        myProfilePage.goToHomePage();

        gameAndPlay.selectCategoryAndItemFromCategory("[1]", "[1]/div/div[2]/h3/a");
        itemFromGameAndPlay.goToHomePageAfterAddToCartByClickingOnProductPage();

        costumesAndRolePlaying.selectCategoryAndItemFromCategory("[2]", "[3]/div/div[1]/a");
        itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCartByClickingOnProductPage();

//        accessories.selectCategoryAndItemFromCategory("[3]", "[1]/div/div[2]/h3/a");
//        itemFromAccessories.goToHomePageAfterAddToCartByClickingOnProductPage();
//
//
//        creativity.selectCategoryAndItemFromCategory("[4]", "[14]/div/div[2]/h3/a");
//        itemFromCreativity.goToHomePageAfterAddToCartByClickingOnProductPage();
//
//        shoesAndSlippers.selectCategoryAndItemFromCategory("[5]", "[3]/div/div[2]/h3/a");
//        itemFromShoesAndSlippers.goToHomePageAfterAddToCartByClickingOnProductPage();
//
//        stem.selectCategoryAndItemFromCategory("[6]", "[1]/div/div[2]/h3/a");
//        itemFromStem.goToHomePageAfterAddToCartByClickingOnProductPage();

        //final Assertion:
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter(itemFromStem)");

        //to checkout:
        homePage.goToCartFromHomePage();

        CartPage newPurchase = new CartPage(driver);
        newPurchase.goToCheckOutAfterAddToCart();

        //to checkout assert: стъпката преди потвърди поръчката
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

    @DataProvider(name = "correctCredentials")
    public static Object[][] readCorrectCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][2];

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
