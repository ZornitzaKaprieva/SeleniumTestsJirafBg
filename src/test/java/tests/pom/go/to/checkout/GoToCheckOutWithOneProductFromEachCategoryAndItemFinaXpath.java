package tests.pom.go.to.checkout;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;

import java.time.Duration;

public class GoToCheckOutWithOneProductFromEachCategoryAndItemFinaXpath extends TestUtil {

    //RESUME: вариант с метода public void selectCategoryAndItemFromCategory(String categoryXpath, String itemXPath){}
    @Test(dataProvider = "correctCredentials")//управляваме през тестовите данни (през самите параметри)

    public void goToCheckOutWithOneProductFromEachCategoryAndItemParam (String email, String password) {
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
        logInPage.login(email, password);
        myProfilePage.goToHomePage();

        gameAndPlay.selectCategoryAndItemFromCategory("[1]", "[1]/div/div[2]/h3/a");
        itemFromGameAndPlay.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromGameAndPlay.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter(itemFromStem)");


        costumesAndRolePlaying.selectCategoryAndItemFromCategory("[2]", "[3]/div/div[1]/a");
        itemFromCostumesAndRolePlaying.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromCostumesAndRolePlaying.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter(itemFromStem)");


        accessories.selectCategoryAndItemFromCategory("[3]", "[1]/div/div[2]/h3/a");
        itemFromAccessories.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromAccessories.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter(itemFromStem)");


        creativity.selectCategoryAndItemFromCategory("[4]", "[14]/div/div[2]/h3/a");
        itemFromCreativity.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromCreativity.getHowManyItemsInTheCart(), "КОЛИЧКА: 4", "Problem with addToCartCounter(itemFromStem)");


        shoesAndSlippers.selectCategoryAndItemFromCategory("[5]", "[3]/div/div[2]/h3/a");
        itemFromShoesAndSlippers.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromShoesAndSlippers.getHowManyItemsInTheCart(), "КОЛИЧКА: 5", "Problem with addToCartCounter(itemFromStem)");


        stem.selectCategoryAndItemFromCategory("[6]", "[1]/div/div[2]/h3/a");
        itemFromStem.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(itemFromStem.getHowManyItemsInTheCart(), "КОЛИЧКА: 6", "Problem with addToCartCounter(itemFromStem)");


        //final Assertion:
       //Assert.assertEquals(itemFromStem.getHowManyItemsInTheCart(), "КОЛИЧКА: 6", "Problem with addToCartCounter(itemFromStem)");

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

    //    @DataProvider(name = "correctCredentials"): moved to TestUtil
//    @DataProvider(name = "correctCredentials") //името на DataProvider, който ще използваме
//    public static Object[][] readCorrectCredentialsFromCsv(){
//        try{
//            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv"));
//            List<String[]> csvData = csvReader.readAll();
//            Object[][] csvDataObject = new Object[csvData.size()][2];
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

