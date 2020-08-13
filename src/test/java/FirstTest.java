import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/*****************************************************************************
 * Author:      Onur Baskirt
 * Description: This is the first Selenium TestNG test.
 *              It opens swtestacademy homepage and prints and checks its title.
*******************************************************************************/

public class FirstTest {

    //-----------------------------------Global Variables-----------------------------------
    //Declare a Webdriver variable
    public WebDriver driver;

    //Declare a test URL variable
    public String testURL = "https://google.com";

    //-----------------------------------Test Setup-----------------------------------
    @BeforeMethod
    public void setupTest (){
        //Create a new ChromeDriver
        System.setProperty("webdriver.chrome.driver","/usr/local/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);

        driver.navigate().to(testURL);
        String path;
        try {
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + "success_screenshots_" + formater.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(source, new File(path)); 
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
    }

    //-----------------------------------Tests-----------------------------------
    @Test
    public void firstTest () {
        //Get page title
        String title = driver.getTitle();

        //Print page's title
        System.out.println("Page Title: " + title);

        //Assertion
        Assert.assertEquals(title, "Google", "Title assertion is passed!");
    }

    //-----------------------------------Test TearDown-----------------------------------
    @AfterMethod
    public void teardownTest (){
        //Close browser and end the session
        driver.quit();
    }
}
