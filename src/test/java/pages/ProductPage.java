package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BasePage {

    @FindBy(xpath = "/html/body/main/header/div[2]/div/div[2]")
    private WebElement menuWrapper;
    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div[2]/div[2]/div[3]/div[2]/form/div[2]/div[2]/div/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div/div[2]/div/div/button")  //todo
    private WebElement continueToShoppingButton;

    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div/div[2]/div/div/a/i") //todo
    private WebElement goToCheckOutBtn;
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

    public HomePage goToHomePageAfterAddToCart(){//todo
        addToCartButton.click();
       // continueToShoppingButton.click(); //За да работи в теста следва да сложим фрейм и да инциалзираме уебелемент: driver.switchTo().frame(continueToShoppingButton);//todo
        driver.switchTo().frame(continueToShoppingButton);
        homePageBtn.click();
        return new HomePage(driver);
    }

    public HomePage goToHomePageAfterAddToCartByClickingOnProductPage(){
        addToCartButton.click();
        menuWrapper.click();
        homePageBtn.click();
        return new HomePage(driver);
    }

    public String getHowManyItemsInTheCart(){
        //return Integer.parseInt(cartBadge.getText());
        return cartBadge.getText();
    }

    public CartPage goToCartFromProductPage(){//todo
        addToCartButton.click();
        //goToCheckOutBtn.click(); //За да работи в теста следва да сложим фрейм и да инциалзираме уебелемент: driver.switchTo().frame(continueToShoppingButton);//todo
        driver.switchTo().frame(goToCheckOutBtn);
        return new CartPage(driver);
    }


}
