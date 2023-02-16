package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOut extends BasePage {

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[2]/div/div/form/div/div/section/div[1]/div/input")
    private WebElement firstNameField;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[2]/div/div/form/div/div/section/div[2]/div/input")
    private WebElement lastNameField;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[2]/div/div/form/div/div/section/div[5]/div/input")
    private WebElement addressField;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[2]/div/div/form/div/div/section/div[7]/div/input")
    private WebElement zipCodeField;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[2]/div/div/form/div/div/section/div[8]/div/input")
    private WebElement cityField;


    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[2]/div/div/form/div/div/section/div[11]/div/span/label/input")
    private WebElement checkboxInvoice; //todo отметката е сложена автоматично

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[2]/div/div/form/div/div/footer/button")
    private WebElement continueBtn;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[3]/div/div[2]/form/div/div[2]/div/textarea")
    private WebElement commentField;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[3]/div/div[2]/form/button")
    private WebElement wayOfDeliveryContinueBtn;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[4]/div/div[1]/div[1]/div/span/input")
    private WebElement radioBtn;

    @FindBy(xpath = "/html/body/main/section/div/div/div/section/div/div[1]/section[4]/div/div[2]/div[1]/button")
    private WebElement confirmBtn;




    public CheckOut(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ConfirmedOrder fillDeliveryDetails (String firstName, String lastName, String address, String zipCode, String city, String comment){
        firstNameField.click();
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        lastNameField.click();
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        addressField.click();
        addressField.clear();
        addressField.sendKeys(address);

        zipCodeField.click();
        zipCodeField.clear();
        zipCodeField.sendKeys(zipCode);

        cityField.click();
        cityField.clear();
        cityField.sendKeys(city);

        continueBtn.click();

        commentField.click();
        commentField.clear();
        commentField.sendKeys(comment);

        wayOfDeliveryContinueBtn.click();
        radioBtn.click();
        confirmBtn.click();


        return new ConfirmedOrder(driver);
    }

}
