package pages;

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

    public void selectItem(){ //(WebElement item)
        productBtnOceanAnimals.click();
    }
}
