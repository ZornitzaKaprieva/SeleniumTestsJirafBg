package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage { //todo да се опишат елементите

    @FindBy(xpath = "//span[text()=\"Разбрах\"] ")
    private WebElement agreeBtn;
    @FindBy(xpath = "//span[text()=\"Вход\"] ")
    private WebElement enterBtn;


    //item
    @FindBy(xpath = "//a[@href='https://jiraf.bg/bg/kreativnost/105-szdajte-svoi-sobstveni-gigantski-okeanski-origami.html']")
    private WebElement productBtnOceanAnimals;

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToLogin(){
        agreeBtn.click();
        enterBtn.click();
    }

    public void selectItem(String xPath){ //(WebElement item)
        //WebElement itemFromHomePageToBeSelected = driver.findElement(By.xpath("/html/body/main/section/div[1]/div/div/section/section/div[5]/div/div/section/div/article[1]/div/div[2]/h3"));
        WebElement itemFromHomePageToBeSelected = driver.findElement(By.xpath(xPath));
        itemFromHomePageToBeSelected.click();
    }
}
