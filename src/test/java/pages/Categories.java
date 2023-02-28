package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Categories extends  BasePage {

    private final static String CATEGORY_XPATH = "/html/body/main/header/div[2]/div/div[2]/div/div/div/div[1]/div/div/ul/li";//константа (после към този линк добавяме конкретен айтем)
    private final static String PRODUCT_CATEGORY_XPATH = "/html/body/main/section/div/div/div[2]/section/section/div[3]/div/div[1]/article";//константа (после към този линк добавяме конкретен айтем)
    private final static String CHECKOUT_BTN = "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/div/div[1]";//константа

    @FindBy(xpath = "//span[text()=\"Разбрах\"] ")
    private WebElement agreeBtn;

    //опит за откриване на категория по клас: todo
//    @FindBy(className = "amenu-item mm-1 plex")
//    private WebElement gamesCategory;

    public Categories(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectCategory(String categoryName){
        WebElement selectCategory = driver.findElement(By.xpath(CATEGORY_XPATH + categoryName)); //към контантата добавяме конкретен айтем
        if (agreeBtn.isDisplayed()){
            agreeBtn.click();
        }
        selectCategory.click();
    }

    public void selectItemFromCategory (String xPath){ //(WebElement item)
        WebElement itemFromCategoryToBeSelected = driver.findElement(By.xpath(xPath));
        itemFromCategoryToBeSelected.click();
    }

    public void goToCheckoutBtn(){
        WebElement selectCategory = driver.findElement(By.xpath(CHECKOUT_BTN));
        selectCategory.click();
    }

    //new:
    public void selectCategoryAndItemFromCategory(String categoryXpath, String itemXPath){
        WebElement selectCategory = driver.findElement(By.xpath(CATEGORY_XPATH + categoryXpath)); //към контантата добавяме конкретен айтем

        if (agreeBtn.isDisplayed()){
            agreeBtn.click();
        }

        selectCategory.click();
        WebElement itemFromCategoryToBeSelected = driver.findElement(By.xpath(PRODUCT_CATEGORY_XPATH + itemXPath));
        itemFromCategoryToBeSelected.click();
    }


    //опит за откриване на категория по клас:


//    public void selectCategoryGamesAndToys() {
//        if (agreeBtn.isDisplayed()) {
//            agreeBtn.click();
//        }
//        gamesCategory.click();
//    }
}
