package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends BasePage { //POM: only positive cases for now. todo

    //1. Web Driver: инициализираме web driver:protected WebDriver driver; или създаваме родителски клас, в който го инициализираме

    //2. Elements: //todo това са основните elements. да довърша!
    @FindBy(name = "email")
    private WebElement emailInput;

    @FindBy (name = "password")
    private WebElement passwordInput;

    @FindBy (id = "submit-login")
    private  WebElement loginBtn;


    //3. Constructor:
    public LogInPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this); //PageFactory е специално за POM
    }

    //4 Methods: //todo това са основните methods (към elements). да довърша!
    public MyProfilePage login(String email, String password){ //пренасочване към следващата страница( public MyProfilePage login(String email, String password){ ) // or: public void login(String email, String password){}
        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys(email);
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys(password);

        loginBtn.click();

        return new MyProfilePage(driver);
    }
}

