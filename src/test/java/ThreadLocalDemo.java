import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
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

public class ThreadLocalDemo {

    public String testURL = "https://google.com";

    @Test
    public void firstTest () {
        //Get page title
	
        long id = Thread.currentThread().getId();
        System.out.println("Test Method firstTest . Thread id is: " + id);
	WebDriver driver=LocalDriverManager.getDriver();
	driver.get(testURL);
	String path;
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        try {
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + "success_screenshots_" + formater.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(source, new File(path)); 
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        String title = driver.getTitle();
        Assert.assertEquals(title, "Google", "Title assertion is passed!");
    }

     @Test
     public void secondTest () {
	long id = Thread.currentThread().getId();
        System.out.println("Test Method secondTest . Thread id is: " + id);
	WebDriver driver=LocalDriverManager.getDriver();
	driver.get(testURL);
        String path;
	Calendar calendar = Calendar.getInstance();
	SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
        try {
            File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            path = "./target/screenshots/" + "success_screenshots_" + formater.format(calendar.getTime()) + ".png";
            FileUtils.copyFile(source, new File(path)); 
        }
        catch(IOException e) {
            path = "Failed to capture screenshot: " + e.getMessage();
        }
        WebElement signtext = driver.findElement(By.id("gb_70"));
	Assert.assertEquals("Sign in", signtext.getText());
    }
}
