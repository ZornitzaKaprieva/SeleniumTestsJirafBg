package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Categories extends  BasePage {

    private final static String CATEGORY_ID = "/html/body/main/header/div[2]/div/div[2]/div/div/div/div[1]/div/div/ul/li";//константа (после към този линк добавяме конкретен айтем)

    @FindBy(xpath = "//span[text()=\"Разбрах\"] ")
    private WebElement agreeBtn;

    public Categories(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectCategory(String categoryName){
        WebElement selectCategory = driver.findElement(By.xpath(CATEGORY_ID + categoryName)); //към контантата добавяме конкретен айтем
        if (agreeBtn.isDisplayed()){
            agreeBtn.click();
        }
        selectCategory.click();
    }
}
