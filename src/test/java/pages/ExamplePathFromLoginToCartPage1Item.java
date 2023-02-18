package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ExamplePathFromLoginToCartPage1Item extends BasePage{

    //това е пътека, която не следва РОМ, дадена е за пример как с една команда да поръчаме продукт:

    ///homePage:

    private final static String CATEGORY_ID = "/html/body/main/header/div[2]/div/div[2]/div/div/div/div[1]/div/div/ul/li";//константа (после към този линк добавяме конкретен айтем)
    private final static String CHECKOUT_BTN = "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/div/div[1]";//константа

    @FindBy(xpath = "//span[text()=\"Разбрах\"] ")
    private WebElement agreeBtn;
    @FindBy(xpath = "//span[text()=\"Вход\"] ")
    private WebElement enterBtn;

    //loginPage:
    @FindBy(xpath = "/html/body/main/section/div/div/div/section/section/section/form/section/div[1]/div/input")
    private WebElement emailInput;

    @FindBy (name = "password")
    private WebElement passwordInput;

    @FindBy (id = "submit-login")
    private  WebElement loginBtn;


    //myProfilePage:
    @FindBy (xpath = "/html/body/main/div/div/nav/ol/li[1]/a/span")
    private WebElement homePageBtn;

    //ProductPage:
    @FindBy(xpath = "/html/body/main/header/div[2]/div/div[2]")
    private WebElement menuWrapper;
    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div[2]/div[2]/div[3]/div[2]/form/div[2]/div[2]/div/button")
    private WebElement addToCartButton;

    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div/div[2]/div/div/button")  //todo
    private WebElement continueToShoppingButton;

    @FindBy(xpath = "/html/body/div[6]/div/div/div[2]/div/div[2]/div/div/a/i") //todo
    private WebElement goToCheckOutBtn;

    //има го в myProfilePage:
//    @FindBy (xpath = "/html/body/main/div/div/nav/ol/li[1]/a/span")
//    private WebElement homePageBtn;

    @FindBy(xpath = "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/a/span[1]") //xpath = "/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/div/div[1]/a/span[1]" //span[@class='cart-products-count']
    WebElement cartBadge;

    //cartPage:
    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[2]/div/div[2]/div[2]/a")
    private WebElement proceedToCheckoutBtnFromCart;


    public ExamplePathFromLoginToCartPage1Item(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
    public CheckOut goToCheckOutAfterFromLoginToCartPage1Item(String email, String password, String categoryName, String xPath ){
        //homePage:
        if (agreeBtn.isDisplayed()){
            agreeBtn.click();
        }
        enterBtn.click();

        //LogIn:
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        //MyProfilePage:
        homePageBtn.click();

        //HomePage=Categories:
        WebElement selectCategory = driver.findElement(By.xpath(CATEGORY_ID + categoryName)); //към контантата добавяме конкретен айтем
        selectCategory.click();

        //Categories

        WebElement itemFromCategoryToBeSelected = driver.findElement(By.xpath(xPath));
        itemFromCategoryToBeSelected.click();

        addToCartButton.click();
        goToCheckOutBtn.click();



        proceedToCheckoutBtnFromCart.click();
        return new CheckOut(driver);
    }
}
