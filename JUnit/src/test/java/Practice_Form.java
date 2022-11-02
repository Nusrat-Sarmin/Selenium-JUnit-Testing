import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Practice_Form {
    WebDriver driver;
    @Before

    public void setup(){
        System.setProperty("webdriver.chrome.driver","./src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }
    @Test
    public void getTitle(){
        driver.get("https://demoqa.com/automation-practice-form");
        String title = driver.getTitle();
        System.out.println(title);
    }
@Test
    public void logIn() throws InterruptedException, IOException, ParseException {
        driver.get("https://demoqa.com/automation-practice-form");
        //FirstName
        WebElement txtFirstName = driver.findElement(By.id("firstName"));
        txtFirstName.sendKeys("Nusrat");
        //LastName
        WebElement txtLastName = driver.findElement(By.id("lastName"));
        txtLastName.sendKeys("Sarmin");
        //Email
        WebElement txtEmail = driver.findElement(By.id("userEmail"));
        txtEmail.sendKeys("nusratsharmintania00@gmail.com");
        //Gender
        Actions actions=new Actions(driver);
        List<WebElement> List = driver.findElements(By.className("custom-control-label"));
        actions.moveToElement(List.get(1)).click().perform();
        //Mobile
        WebElement txtMobile = driver.findElement(By.id("userNumber"));
        txtMobile.sendKeys("01758585133");
        //Date of Birth
        WebElement txtDate = driver.findElement(By.id("dateOfBirthInput"));
        txtDate.sendKeys(Keys.CONTROL+"a");
        txtDate.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);
        driver.findElement(By.id("dateOfBirthInput")).sendKeys("10  Oct  1996");
        txtDate.sendKeys(Keys.ENTER);
        //Subject
        WebElement chooseSubject = driver.findElement(By.id("subjectsInput"));
        chooseSubject.sendKeys("Biology");
        //Hobbies
        WebElement Hobbies=driver.findElement(By.id("hobbies-checkbox-2"));
        Hobbies.click();
        //CurrentAddress
        WebElement txtCurrentAddress = driver.findElement(By.id("currentAddress"));
        txtCurrentAddress.sendKeys("Dhanmondi 15,Dhaka");
        //Select picture
        WebElement uploadElement = driver.findElement(By.id("uploadPicture"));
        uploadElement.sendKeys("G:\\Image\\12122.jpg");
        //Select State
        WebElement selectState = driver.findElement(By.id("react-select-3-input"));
        selectState.sendKeys("Haryana");
        selectState.sendKeys(Keys.ENTER);
        //Select City
        WebElement selectCity = driver.findElement(By.id("react-select-4-input"));
        selectCity.sendKeys("Panipat");
        selectCity.sendKeys(Keys.ENTER);
        //Submit
        WebElement txtButton1 = driver.findElement(By.id("submit"));
        txtButton1.sendKeys(Keys.ENTER);
        String text= driver.findElement(By.className("modal-body")).getText();
        driver.findElement(By.id("closeLargeModal")).sendKeys(Keys.ENTER);
        System.out.println(text);

    //student.json add
    String fileName = "./src/main/resources/students.json";
    JSONParser jsonParser = new JSONParser();
    Object obj =jsonParser.parse(new FileReader(fileName));
    JSONObject studentObj = new JSONObject();
    JSONArray studentArray = (JSONArray) obj;
    //--scraping Data From Web Table

    WebElement table = driver.findElement(By.tagName("tbody"));
    List<WebElement> allRows = table.findElements(By.className("tr"));
    int i = 0;
    for (WebElement row : allRows) {
        List<WebElement> cells = row.findElements(By.className("td"));
        for (WebElement cell : cells) {
            i++;
            studentObj.put(cells.get(0).getText(), cell.getText());

        }

    }
    studentArray.add(studentObj);
    System.out.println(studentArray);
    FileWriter file = new FileWriter(fileName);
    file.write(studentArray.toJSONString());
    file.flush();
    file.close();
    }
    public void modal(){
        driver.get("https://demoqa.com/automation-practice-form");
        driver.findElement(By.id("closeLargeModal")).click();
        String text= driver.findElement(By.className("modal-body")).getText();
        System.out.println(text);
        //driver.findElement(By.id("closeLargeModal")).click();
    }
    @After
    public void closeDriver(){
        driver.close();
        driver.quit();
    }

}
