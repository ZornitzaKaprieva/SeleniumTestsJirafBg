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

public class AddToCart3ItemsFromHomePageAfterLogInItemCsv extends TestUtil {

    //RESUME: вариант с Assert в края на теста и данни от .csv файл за айтемите (логинът е хардкорнат)
    // (ако ни гръмне някой тест поради липса на наличност, не знаем кой е продуктът)
    // (проверяваме през теста GoToCheckOutWithOneProductFromEachCategoryWithoutApprovalWindowWithAssertions, който е по-дълъг, но по-надежден)

    @Test(dataProvider = "homePageItems")

    public void addToCart3ItemsFromHomePageAfterLogInItemCsv (String xpathItem1, String xpathItem2,String xpathItem3) throws InterruptedException {

        //подреденият вариант с final assertion:
        HomePage homePage = new HomePage(driver); //един page, един обект
        LogInPage logInPage = new LogInPage(driver); //един page, един обект
        MyProfilePage myProfilePage = new MyProfilePage(driver);


        HomePage homePageItem1 = new HomePage(driver);
        HomePage homePageItem2 = new HomePage(driver);
        HomePage homePageItem3 = new HomePage(driver);

        ProductPage item1 = new ProductPage(driver);
        ProductPage item2 = new ProductPage(driver);
        ProductPage item3 = new ProductPage(driver);

        homePage.goToLogin();
        logInPage.login("qa-test1122@abv.bg","test1122"); //хардкорнато
        myProfilePage.goToHomePage();

        //item1:
        homePageItem1.selectItemFromHomePageParam(xpathItem1);
        item1.goToHomePageAfterAddToCartByClickingOnProductPage();

        //item2:
        homePageItem2.selectItemFromHomePageParam(xpathItem2);
        item2.goToHomePageAfterAddToCartByClickingOnProductPage();

        //item3:
        homePageItem3.selectItemFromHomePageParam(xpathItem3);
        item3.goToHomePageAfterAddToCartByClickingOnProductPage();

        Assert.assertEquals(item3.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");
    }

    //@DataProvider(name = "homePageItems"): moved to TestUtil
//    @DataProvider(name = "homePageItems")
//    public static Object[][] readHomePageItemsFromCsv(){
//        try{
//            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/homePageItems.csv"));
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
//
//    }
}

