package tests.pom.categories;

import base.TestUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Categories;

public class SelectCategoryWithoutLogIn extends TestUtil {
    @Test
    public void successfullySelectedCategory() {

        Categories gameAndPlay = new Categories(driver);
        Categories costumesAndRolePlaying = new Categories(driver);
        Categories accessories = new Categories(driver);
        Categories creativity = new Categories(driver);
        Categories shoesAndSlippers = new Categories(driver);
        Categories stem = new Categories(driver);

        gameAndPlay.selectCategory("[1]");
        WebElement gameAndPlayTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
        Assert.assertTrue(gameAndPlayTitle.isDisplayed());
//
//        costumesAndRolePlaying.selectCategory("[2]");
//        WebElement costumesAndRolePlayingTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
//        Assert.assertTrue(costumesAndRolePlayingTitle.isDisplayed());
//
//        accessories.selectCategory("[3]");
//        WebElement accessoriesTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
//        Assert.assertTrue(accessoriesTitle.isDisplayed());
//
//        creativity.selectCategory("[4]");
//        WebElement selectCategoryTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
//        Assert.assertTrue(selectCategoryTitle.isDisplayed());
//
//        shoesAndSlippers.selectCategory("[5]");
//        WebElement shoesAndSlippersTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
//        Assert.assertTrue(shoesAndSlippersTitle.isDisplayed());
//
//        stem.selectCategory("[6]");
//        WebElement stemTitle = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/div/div/h1"));
//        Assert.assertTrue(stemTitle.isDisplayed());
    }
}

