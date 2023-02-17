package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage { //todo да се опишат елементите


    //повтаря се с Categories:
    private final static String CATEGORY_XPATH = "/html/body/main/header/div[2]/div/div[2]/div/div/div/div[1]/div/div/ul/li";//константа (после към този линк добавяме конкретен айтем)

    private final static String PRODUCT_FROM_HOMEPAGE_XPATH = "/html/body/main/section/div[1]/div/div/section/section/div[5]/div/div/section/div/article";
    private final static String CHECKOUT_BTN = "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/div/div[1]";//константа

    @FindBy(xpath = "//span[text()=\"Разбрах\"] ")
    private WebElement agreeBtn;
    @FindBy(xpath = "//span[text()=\"Вход\"] ")
    private WebElement enterBtn;


    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToLogin(){
        if (agreeBtn.isDisplayed()){
            agreeBtn.click();
        }
        enterBtn.click();
    }

    public void selectItem(String xPath){ //(WebElement item)
        WebElement itemFromHomePageToBeSelected = driver.findElement(By.xpath(xPath));
        itemFromHomePageToBeSelected.click();
    }


    //повтаря се с Categories
    public void goToCategoryFromHomePage (String categoryName){
        WebElement selectCategory = driver.findElement(By.xpath(CATEGORY_XPATH + categoryName)); //към контантата добавяме конкретен айтем
        if (agreeBtn.isDisplayed()){
            agreeBtn.click();
        }
        selectCategory.click();
    }

    //повтаря се с Categories
    public CartPage goToCartFromHomePage(){
        WebElement clickCheckOutBtn = driver.findElement(By.xpath(CHECKOUT_BTN));
        clickCheckOutBtn.click();
        return new CartPage(driver);
    }

    //повтаря се с Categories
    //new:
    public void selectItemFromHomePageParam(String itemXPath){

        if (agreeBtn.isDisplayed()){
            agreeBtn.click();
        }

        WebElement itemFromCategoryToBeSelected = driver.findElement(By.xpath(PRODUCT_FROM_HOMEPAGE_XPATH + itemXPath));
        itemFromCategoryToBeSelected.click();
    }
}
