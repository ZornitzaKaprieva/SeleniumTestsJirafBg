package tests.pom.login;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LogInPage;
import pages.MyProfilePage;

public class LogInTestCorrectCredentials extends TestUtil {


    @Test (dataProvider = "correctCredentials")

    public void successfulLogin(String email, String password) {

        HomePage homePage = new HomePage(driver);
        homePage.goToLogin();

        WebElement emailField = driver.findElement(By.name("email"));
        Assert.assertTrue(emailField.isDisplayed(), "Email Link was not displayed");


        LogInPage logInPage = new LogInPage(driver);
        //MyProfilePage myProfilePage = logInPage.login(email, password); // сега не е нужно, нямаме тестове с MyProfilePage: ако нямаме това = трябват асършани

        logInPage.login(email, password);
        WebElement myProfileContent = driver.findElement(By.id("content"));
        Assert.assertTrue(myProfileContent.isDisplayed(), "Мy Profile content was not displayed");
        //Thread.sleep(1000);
    }

    ////    @DataProvider(name = "correctCredentials"): moved to TestUtil
//        @DataProvider(name = "correctCredentials")
//        public static Object[][] readCorrectCredentialsFromCsv(){
//            try{
//                CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv"));
//                List<String[]> csvData = csvReader.readAll();
//                Object[][] csvDataObject = new Object[csvData.size()][2]; // само 2 стойности в scv
//
//                for (int i = 0; i < csvData.size(); i++) {
//                    csvDataObject[i] = csvData.get(i);
//                }
//                return csvDataObject;
//
//            }catch (IOException e){
//                System.out.println("Not possible to find CSV!");
//                return null;
//            } catch (CsvException e){
//                System.out.println("Something went wrong!");
//                return null;
//            }
//        }
}
