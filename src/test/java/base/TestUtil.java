package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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

    //todo temporally comment
//    @AfterMethod
//    public void tearDown(){
//        driver.quit();
//    }

    private  void setupBrowserDriver (String browser) {

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

    public void loadTestUrl(String url) {
        driver.get(url); //това казва да на драйвъра да зареди този url
    }
}

