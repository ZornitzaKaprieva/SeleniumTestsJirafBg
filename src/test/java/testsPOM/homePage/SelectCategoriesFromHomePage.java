package testsPOM.homePage;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Categories;
import pages.HomePage;

public class SelectCategoriesFromHomePage extends TestUtil {

    @Test
    public void selectCategoriesFromHomaPage(){ //повтаря се с SelectCategoryWithoutLogIn
//        HomePage homePage = new HomePage(driver);
//        homePage.goToCategoryFromHomePage("[1]");

        HomePage gameAndPlay = new HomePage(driver);
        HomePage costumesAndRolePlaying = new HomePage(driver);
        HomePage accessories = new HomePage(driver);
        HomePage creativity = new HomePage(driver);
        HomePage shoesAndSlippers = new HomePage(driver);
        HomePage stem = new HomePage(driver);

        gameAndPlay.goToCategoryFromHomePage("[1]");
        WebElement gameAndPlayTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(gameAndPlayTitle.isDisplayed());

        costumesAndRolePlaying.goToCategoryFromHomePage("[2]");
        WebElement costumesAndRolePlayingTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(costumesAndRolePlayingTitle.isDisplayed());

        accessories.goToCategoryFromHomePage("[3]");
        WebElement accessoriesTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(accessoriesTitle.isDisplayed());

        creativity.goToCategoryFromHomePage("[4]");
        WebElement selectCategoryTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(selectCategoryTitle.isDisplayed());

        shoesAndSlippers.goToCategoryFromHomePage("[5]");
        WebElement shoesAndSlippersTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(shoesAndSlippersTitle.isDisplayed());

        stem.goToCategoryFromHomePage("[6]");
        WebElement stemTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(stemTitle.isDisplayed());


    }
}
