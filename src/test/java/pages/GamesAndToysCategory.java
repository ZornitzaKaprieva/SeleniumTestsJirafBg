package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GamesAndToysCategory extends BasePage {

    //private final static String PRODUCT_ID = "add-to-cart-sauce-labs-";//константа (nqma adToCartBtn in jitaf.bg productpage)

    public GamesAndToysCategory(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void addItemToTheCart(String xPath){
        WebElement itemFromGamesAndToysToBeSelected = driver.findElement(By.xpath(xPath));
        itemFromGamesAndToysToBeSelected.click();
//        WebElement item2ToBeAdded = driver.findElement(By.xpath("/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article[2]/div/div[2]/h3/a"));
//        item2ToBeAdded.click();
    }
}
