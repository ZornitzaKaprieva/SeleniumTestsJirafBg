package tests.pom.categories;

import base.TestUtil;
import org.testng.annotations.Test;
import pages.HomePage;

public class SelectCategoriesByClass extends TestUtil {

    @Test
    public void selectCategories() throws InterruptedException {

        HomePage homePageSelectCategory = new HomePage(driver);
        homePageSelectCategory.goToCategoryFromHomePageClass();
        //Thread.sleep(1000);

    }
}
