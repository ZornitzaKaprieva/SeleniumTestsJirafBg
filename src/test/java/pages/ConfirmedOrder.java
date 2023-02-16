package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class ConfirmedOrder extends BasePage{
    public ConfirmedOrder(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}
