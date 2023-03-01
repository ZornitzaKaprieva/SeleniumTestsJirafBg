package tests.pom.add.to.cart;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.MyProfilePage;
import pages.ProductPage;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class AddToCart3ItemsFromHomePageAfterLogInItemFinalXpathProducts extends TestUtil {

    @Test(dataProvider = "correctCredentials")

    public void addToCart3ItemsFromHomePageAfterLogInItemFinalXpathProducts (String email, String password) {

        //RESUME: с final XPath:

        HomePage homePage = new HomePage(driver); //един page, един обект
        LogInPage logInPage = new LogInPage(driver);
        MyProfilePage myProfilePage = new MyProfilePage(driver);

        HomePage homePageItem1 = new HomePage(driver);
        HomePage homePageItem2 = new HomePage(driver);
        HomePage homePageItem3 = new HomePage(driver);

        ProductPage item1 = new ProductPage(driver);
        ProductPage item2 = new ProductPage(driver);
        ProductPage item3 = new ProductPage(driver);

        homePage.goToLogin();
        logInPage.login(email, password);
        myProfilePage.goToHomePage();

        //item1:
        homePageItem1.selectItemFromHomePageParam("[1]/div/div[2]/h3");
        item1.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item1.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter");


        //item2:
        homePageItem2.selectItemFromHomePageParam("[2]/div/div[2]/h3/a");
        item2.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item2.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter");

        //item3:
        homePageItem3.selectItemFromHomePageParam("[3]");
        item3.goToHomePageAfterAddToCartByClickingOnProductPage();
        Assert.assertEquals(item3.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");

        //ХРОНОЛОГИЧНО ПОДРЕДЕН ВАРИАНТ (with assertions):
//
//        //item1:
//        HomePage homePageItem1 = new HomePage(driver);
//        homePageItem1.selectItem("/html/body/main/section/div[1]/div/div/section/section/div[5]/div/div/section/div/article[1]/div/div[2]/h3");
//        WebElement itemTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
//        Assert.assertTrue(itemTitle.isDisplayed(), "Item Title is not displayed.");
//
//        ProductPage item1 = new ProductPage(driver);
//        item1.goToHomePageAfterAddToCart();
//        Assert.assertEquals(item1.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter");
//
//        //item2:
//        HomePage homePageItem2 = new HomePage(driver);
//        homePageItem2.selectItem("/html/body/main/section/div[1]/div/div/section/section/div[5]/div/div/section/div/article[2]/div/div[2]/h3/a");
//        WebElement itemTitle2 = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
//        Assert.assertTrue(itemTitle2.isDisplayed(), "Item2 Title is not displayed.");
//
//        ProductPage item2 = new ProductPage(driver);
//        item2.goToHomePageAfterAddToCart();
//        Assert.assertEquals(item2.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter");
//
//        //item3:
//        HomePage homePageItem3 = new HomePage(driver);
//        homePageItem3.selectItem("/html/body/main/section/div[1]/div/div/section/section/div[5]/div/div/section/div/article[3]");
//        WebElement itemTitle3 = driver.findElement(By.xpath("/html/body/main/section/div/div/div/section/div[2]/div[2]/h1"));
//        Assert.assertTrue(itemTitle3.isDisplayed(), "Item2 Title is not displayed.");
//
//        ProductPage item3 = new ProductPage(driver);
//        item3.goToHomePageAfterAddToCart();
//        Assert.assertEquals(item3.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");

    }

//    @DataProvider(name = "correctCredentials") //името на DataProvider, който ще използваме
//    public static Object[][] readCorrectCredentialsFromCsv(){
//        try{
//            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv")); // има ексепшън, който трябва да хванем (IOException)
//            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
//            Object[][] csvDataObject = new Object[csvData.size()][2]; //все едно това ни е броя на редовете в scv. В случая имаме само 2 стойности в scv, затова можем да ги хардкорнем, но не можем да хардкорнем редовете, защото те се променят
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

