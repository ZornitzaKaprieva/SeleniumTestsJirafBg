package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MyProfilePage extends BasePage {

    public MyProfilePage (WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

}


