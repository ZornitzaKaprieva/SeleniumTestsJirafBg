package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div[2]/div[2]/div[3]/div[2]/form/div[2]/div[2]/div/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div/div[2]/div/div/button")
    private WebElement continueToShoppingButton;

    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div/div[2]/div/div/a")
    private WebElement goToCartButton;

    @FindBy (xpath = "/html/body/main/div/div/nav/ol/li[1]/a/span")
    private WebElement homePageBtn;

    @FindBy(xpath = "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/a/span[1]") //xpath = "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/a/span[1]" //span[@class='cart-products-count']
    WebElement cartBadge;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void selectItemAndAddToCart(){
        addToCartButton.click();
    }

    public void addToCartAndContinueToShopping(){
        addToCartButton.click();
        continueToShoppingButton.click();
    }

    public HomePage goToHomePageAfterAddToCart(){
        addToCartButton.click();
        continueToShoppingButton.click();
        homePageBtn.click();
        return new HomePage(driver);
    }

    public String getItemsInTheCart(){
        //return Integer.parseInt(cartBadge.getText());
        return cartBadge.getText();
    }
}
