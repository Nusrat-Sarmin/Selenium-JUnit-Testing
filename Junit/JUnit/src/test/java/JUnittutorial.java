import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;

public class JUnittutorial {
    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headed");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/");
       String title = driver.getTitle();
        System.out.println(title);
        Assert.assertEquals(title,"ToolsQA");
    }
    @Test
    public void writeText(){
        driver.get("https://demoqa.com/automation-practice-form");
        //driver.findElement(By.id("firstName")).sendKeys("Tania");
        WebElement txtFirstName = driver.findElement(By.id("firstName"));
        txtFirstName.sendKeys("Nusrat");

        WebElement txtLastName = driver.findElement(By.id("lastName"));
        txtLastName.sendKeys("Sarmin");

        WebElement txtEmail = driver.findElement(By.id("userEmail"));
        txtEmail.sendKeys("Nusrat@gmail.com");

        WebElement txtMobile = driver.findElement(By.id("userNumber"));
        txtMobile.sendKeys("0123456789");

        WebElement txtAddress = driver.findElement(By.id("currentAddress"));
        txtAddress.sendKeys("Dhanmondi,Dhaka");
    }
    @Test
    public void StateAndCity(){
        driver.get("https://demoqa.com/automation-practice-form");
        //driver.findElement(By.id("dateOfBirthInput")).clear();
        //driver.findElement(By.id("dateOfBirthInput")).sendKeys("20 Aug 2022");
        


    }

   @After
    public void closeDriver(){
        //driver.close();
    }


}
