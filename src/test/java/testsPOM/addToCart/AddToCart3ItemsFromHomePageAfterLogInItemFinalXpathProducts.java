package testsPOM.addToCart;

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

    public void addToCart3ItemsFromHomePageAfterLogInItemFinalXpathProducts (String email, String password) throws InterruptedException {

        //RESUME: подреденият вариант с final assertion and  private final static String PRODUCT_FROM_HOMEPAGE_XPATH:

        HomePage homePage = new HomePage(driver);
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

        //item2:
        homePageItem2.selectItemFromHomePageParam("[2]/div/div[2]/h3/a");
        item2.goToHomePageAfterAddToCartByClickingOnProductPage();

        //item3:
        homePageItem3.selectItemFromHomePageParam("[3]");
        item3.goToHomePageAfterAddToCartByClickingOnProductPage();

        Assert.assertEquals(item3.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");

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

