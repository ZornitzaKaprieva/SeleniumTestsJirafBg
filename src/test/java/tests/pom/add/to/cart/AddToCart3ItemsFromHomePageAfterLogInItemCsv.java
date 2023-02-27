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

    @Test(dataProvider = "homePageItems")//управляваме през тестовите данни (през самите параметри)

    public void addToCart3ItemsFromHomePageAfterLogInItemParam (String xpathItem1, String xpathItem2,String xpathItem3) throws InterruptedException {

        //подреденият вариант с final assertion and param: (не можем да сложим параметри за повече от един продукт, тъй като, за да се поръча, следва да се мине през страницата на конкретния продукт
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
        logInPage.login("qa-test1122@abv.bg","test1122"); //хардкорнато е, защото не знам как да сложа 2 провайдъра
        myProfilePage.goToHomePage();

        //item1:
        homePageItem1.selectItemFromHomePageParam(xpathItem1);
        item1.goToHomePageAfterAddToCartByClickingOnProductPage();
        //Assert.assertEquals(item1.getHowManyItemsInTheCart(), "КОЛИЧКА: 1", "Problem with addToCartCounter");

        //item2:
        homePageItem2.selectItemFromHomePageParam(xpathItem2);
        item2.goToHomePageAfterAddToCartByClickingOnProductPage();
        //Assert.assertEquals(item2.getHowManyItemsInTheCart(), "КОЛИЧКА: 2", "Problem with addToCartCounter");

        //item3:
        homePageItem3.selectItemFromHomePageParam(xpathItem3);
        item3.goToHomePageAfterAddToCartByClickingOnProductPage();

        Assert.assertEquals(item3.getHowManyItemsInTheCart(), "КОЛИЧКА: 3", "Problem with addToCartCounter");
    }

    @DataProvider(name = "homePageItems") //името на DataProvider, който ще използваме
    public static Object[][] readHomePageItemsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/homePageItems.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][csvData.size()]; // няма да хардкорнем редовете и стойностите, защото може да се променят

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

