package pages;

import base.TestUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut extends BasePage {


    public CheckOut(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }


}
