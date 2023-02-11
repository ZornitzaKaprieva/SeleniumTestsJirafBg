package pages.itemPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ItemOceanAnimal extends BasePage {

    @FindBy()
    private WebElement itemName;
    public ItemOceanAnimal(WebDriver driver) {
        super(driver);
    }
}
