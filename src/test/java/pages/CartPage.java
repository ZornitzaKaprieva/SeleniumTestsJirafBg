package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage{

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[2]/div/div[2]/div[2]/a")
    private WebElement proceedToCheckoutBtnFromCart;
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CheckOut goToCheckOutAfterAddToCart(){
        proceedToCheckoutBtnFromCart.click();
        return new CheckOut(driver);
    }


}
