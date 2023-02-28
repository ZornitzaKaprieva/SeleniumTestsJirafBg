package base;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class TestUtil {

    public WebDriver driver; //слагаме драйвър
    public String applicationUrl, browser; //променливите от config file
    public int implicitWait; //за изчакване на времето,виж config

    @BeforeMethod
    public void setUp() { //в setUp извикваме методите, които сме създали
        readConfig("src/test/resources/config.properties"); //описваме path, откъдето да вземем и прочетем данните (url, browser, implicitWait)
        setupBrowserDriver(browser);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        loadTestUrl(applicationUrl);
    }

    //todo if(comment){temporally}
//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

    private void setupBrowserDriver (String browser) {

        switch (browser){
            case "chrome":
                driver = setupChromeDriver(); // за да успеем да сетнем драйвъра и да не дава грешка, добавяме метод private WebDriver setupChromeDriver(){}
                break;
            case "firefox":
                driver = setupFirefoxDriverDriver(); //т.е. добавяме метод за всеки кейс, респ. браузър
                break;
            case "edge":
                driver = setupEdgeDriver();
        }
    }

    private WebDriver setupChromeDriver() { //да връща уебдрайвър
        WebDriverManager.chromedriver().setup(); // така сваляме автоматично драйвър за хром с правилната версия за нашата машина.
        //ще го направим централизирано: driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait)); //set на времето, което сме подали в config
        return new ChromeDriver(); //след което го инициализираме. Може и driver = new ChromeDriver();
    }

    private WebDriver setupFirefoxDriverDriver() {
        WebDriverManager.firefoxdriver().setup();
        //ще го направим централизирано: driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        return new FirefoxDriver();
    }

    private WebDriver setupEdgeDriver(){
        WebDriverManager.edgedriver().setup();
        //ще го направим централизирано: driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
        return new EdgeDriver();
    }

    private void readConfig(String filePath) { //не е особено ясно защо е с try-catch todo
        try {
            FileInputStream configFile = new FileInputStream(filePath);//иска throws IOException
            Properties config =  new Properties();
            config.load(configFile); //така в този config вече сме лоуднали configFile, т.е.прочели сме тези неща.
            // Сега искаме да ги сетнем за самите променливи:
            applicationUrl = config.getProperty("url"); //взимаме стойността на даден ключ от config
            browser = config.getProperty("browser"); //взимаме стойността на даден ключ от config
            implicitWait = Integer.parseInt(config.getProperty("implicitWait")); //от int към string: взимаме стойността на даден ключ от config
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void loadTestUrl(String url) {driver.get(url);} //това казва на драйвъра да зареди този url

    @DataProvider(name = "correctCredentials") //името на DataProvider, който ще използваме
    public static Object[][] readCorrectCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/correctCredentials.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][2]; //все едно това ни е броя на редовете в scv. В случая имаме само 2 стойности в scv, затова можем да ги хардкорнем, но не можем да хардкорнем редовете, защото те се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }
    }

    @DataProvider(name = "wrongCredentials") //името на DataProvider, който ще използваме
    public static Object[][] readWrongCredentialsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/wrongCredentials.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][2]; //все едно това ни е броя на редовете в scv. В случая имаме само 2 стойности в scv, затова можем да ги хардкорнем, но не можем да хардкорнем редовете, защото те се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }
    }

    @DataProvider(name = "homePageItems") //името на DataProvider, който ще използваме
    public static Object[][] readHomePageItemsFromCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/homePageItems.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][csvData.size()]; // няма да хардкорнем редовете и стойностите, защото може да се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }

    }

    @DataProvider(name = "xPathOneItemFromEachCategoryCsv") //името на DataProvider, който ще използваме
    public static Object[][] readXPathOneItemFromEachCategoryCsv() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/oneItemFromEachCategory.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][csvData.size()]; // няма да хардкорнем редовете и стойностите, защото може да се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        } catch (IOException e) {
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e) {
            System.out.println("Something went wrong!");
            return null;
        }

    }

    @DataProvider(name = "loginCategoriesItems") //името на DataProvider, който ще използваме
    public static Object[][] readLoginCategoriesItemsCsv(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/loginCategoriesItems.csv")); // има ексепшън, който трябва да хванем (IOException)
            List<String[]> csvData = csvReader.readAll();// методът csvReader.readAll(); също има ексепшън, който трябва да хванем
            Object[][] csvDataObject = new Object[csvData.size()][csvData.size()]; // няма да хардкорнем редовете и стойностите, защото може да се променят

            for (int i = 0; i < csvData.size(); i++) {
                csvDataObject[i] = csvData.get(i);
            }
            return csvDataObject;

        }catch (IOException e){
            System.out.println("Not possible to find CSV!");
            return null;
        } catch (CsvException e){
            System.out.println("Something went wrong!");
            return null;
        }

    }

}

