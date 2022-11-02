import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class JUnitTutorial {
    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("webdriver.gecko.driver","./src/test/resources/geckodriver.exe");
        FirefoxOptions options=new FirefoxOptions();
        options.addArguments("--headed");
        driver =new FirefoxDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/");
        String title= driver.getTitle();
        Assert.assertEquals("Tools",title);
        //Assert.assertTrue(title.contains("ToolsQA"));
    }
    @Test
    public void writeText(){
        driver.get("https://demoqa.com/text-box");
        //driver.findElement(By.id("userName")).sendKeys("Salman");
//        driver.findElement(By.cssSelector("[type=text]")).sendKeys("Salman");
        //driver.findElement(By.cssSelector("[id=userName]")).sendKeys("Salman");
        //driver.findElement(By.className("form-control")).sendKeys("Salman");
        WebElement txtFirstName= driver.findElement(By.id("userName"));
        txtFirstName.sendKeys("Salman");
//        WebElement txtEmail=driver.findElement(By.xpath("//input[@id='userEmail']"));
//        txtEmail.sendKeys("salman@test.com");
        WebElement txtEmail=driver.findElement(By.cssSelector("[type='email']"));
        txtEmail.sendKeys("salman@test.com");

        List<WebElement> button=driver.findElements(By.tagName("button"));
        button.get(1).click();

        //driver.findElements(By.tagName("button")).get(0).click();
    }
    @Test
    public void handleAlert() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
//        driver.findElement(By.id("alertButton")).click();
//        Thread.sleep(2000);
//        driver.switchTo().alert().accept();
        driver.findElement(By.id("confirmButton")).click();
        driver.switchTo().alert().dismiss();
        String text= driver.findElement(By.className("text-success")).getText();
        System.out.println(text);
        Assert.assertTrue(text.contains("Cancel"));
    }
    @Test
    public void selectDate() throws InterruptedException {
        driver.get("https://demoqa.com/date-picker");
        Actions actions=new Actions(driver);
        WebElement txtDate=driver.findElement(By.id("datePickerMonthYearInput"));
//        actions.moveToElement(txtDate).
//                doubleClick().click().
//                keyDown(Keys.BACK_SPACE).
//                keyUp(Keys.BACK_SPACE).
//                perform();
        txtDate.sendKeys(Keys.CONTROL+"a");
        txtDate.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.id("datePickerMonthYearInput")).sendKeys("07/27/2022");
        txtDate.sendKeys(Keys.ENTER);

    }
    @Test
    public void keyboardEvent() throws InterruptedException {
        driver.get("https://www.google.com/");
        WebElement searchElement = driver.findElement(By.name("q"));
        Actions action = new Actions(driver);
        action.moveToElement(searchElement);
        action.keyDown(Keys.SHIFT);
        action.sendKeys("Selenium Webdriver")
                .keyUp(Keys.SHIFT)
                .doubleClick().click()
                .contextClick()
                .perform();

        Thread.sleep(5000);
    }
    @Test
    public void selectDropdown(){
        driver.get("https://demoqa.com/select-menu");
        Select select=new Select(driver.findElement(By.id("oldSelectMenu")));
        select.selectByValue("1");
        Select cars=new Select(driver.findElement(By.id("cars")));
        if (cars.isMultiple()) {
            cars.selectByValue("volvo");
            cars.selectByValue("audi");
        }
    }
    @Test
    public void mouseHover(){
        driver.get("https://green.edu.bd/");
        Actions actions=new Actions(driver);
        List<WebElement> list= driver.findElements(By.xpath("//a[contains(text(),\"About Us\")]"));
        actions.moveToElement(list.get(2)).perform();

    }
    @Test
    public void actionClick(){
        driver.get("https://demoqa.com/buttons");
        List<WebElement> buttons=driver.findElements(By.cssSelector("[type=button]"));
        Actions actions=new Actions(driver);
        actions.doubleClick(buttons.get(1)).perform();
        actions.contextClick(buttons.get(2)).perform();
    }
    @Test
    public void takeScreenShot() throws IOException {
        driver.get("https://demoqa.com");
        File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String time = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss-aa").format(new Date());
        String fileWithPath = "./src/test/resources/Screenshot/" + time + ".png";
        File DestFile = new File(fileWithPath);
        FileUtils.copyFile(screenshotFile, DestFile);
    }
    @Test
    public void uploadFile(){
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("uploadFile")).sendKeys("D:\\a.jpg");
    }
    @Test
    public void downloadFile(){
        driver.get("https://demoqa.com/upload-download");
        driver.findElement(By.id("downloadButton")).click();
    }

    @After
    public void closeDriver(){
        //driver.close();
    }
}
