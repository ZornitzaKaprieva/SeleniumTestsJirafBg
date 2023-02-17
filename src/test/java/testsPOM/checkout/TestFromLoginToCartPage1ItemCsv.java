package testsPOM.checkout;

import base.TestUtil;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.ExamplePathFromLoginToCartPage1Item;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TestFromLoginToCartPage1ItemCsv extends TestUtil {

    @Test(dataProvider = "correctCredentialsAndItemDetails")
    public void goFromLoginToCartPage1ItemCsv (String email, String password,String categoryName, String xPath) throws InterruptedException {
        ExamplePathFromLoginToCartPage1Item firstItemFromGamesAndToys = new ExamplePathFromLoginToCartPage1Item(driver);
        firstItemFromGamesAndToys.goToCheckOutAfterFromLoginToCartPage1Item( email, password, categoryName, xPath);

    }

    @DataProvider(name = "correctCredentialsAndItemDetails") //името на DataProvider, който ще използваме
    public static Object[][] readCredentialsAndItemDetails(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/credentialsAndItemDetails.csv"));
            List<String[]> csvData = csvReader.readAll();
            Object[][] csvDataObject = new Object[csvData.size()][4]; //oldVersion: [2]: все едно това ни е броя на редовете в scv. В случая имаме само 2 стойности в scv, затова можем да ги хардкорнем, но не можем да хардкорнем редовете, защото те се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find credentialsAndItemDetails.CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong with credentialsAndItemDetails!");
            return null;
        }

    }
}
