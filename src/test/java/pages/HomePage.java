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

    public HomePage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void goToLogin(){
        agreeBtn.click();
        enterBtn.click();
    }
}
