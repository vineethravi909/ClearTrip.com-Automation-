package cleartrip2;
//Imports
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.*;



public class testng {
    WebDriver driver;

    //Before the test starts
    @BeforeTest
    public void beforeteststart() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Browser drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        String pageTitle = driver.getTitle();
        Assert.assertEquals(pageTitle,"#1 Site for Booking Flights, Hotels, Packages, Trains & Local activities.");
    }

    //Test
    @Test(priority = 0)
    public void FlightsTest() throws InterruptedException {

        //Select Flights
        WebElement Flights = driver.findElement(By.xpath("//a[contains(text(),\"Flights\")]"));
        Flights.click();

        //One-Way
        driver.findElement(By.xpath("//input[@value='OneWay']")).click();

        //From Place
        WebElement From = driver.findElement(By.id("FromTag"));
        From.sendKeys("Bangalore");
        From.click();

        //To Place
        WebElement To = driver.findElement(By.id("ToTag"));
        To.sendKeys("Mumbai");
        To.click();

        //Departure Date
        driver.findElement(By.id("DepartDate")).click();
        int date = driver.findElements(By.xpath("//td[contains(@data-handler,'selectDay')]")).size();
        for (int i = 0;i < date; i++){
            if(i==30)
            {
                driver.findElements(By.xpath("//td[contains(@data-handler,'selectDay')]")).get(i).click();
            }
        }

        //No of Adults
        Select dropPort = new Select(driver.findElement(By.name("adults")));
        dropPort.selectByValue("1");

        //No of Kids
        Select dropPort1 = new Select(driver.findElement(By.name("childs")));
        dropPort1.selectByValue("1");

        //No of Infants
        Select dropPort2 = new Select(driver.findElement(By.name("infants")));
        dropPort2.selectByValue("1");

        //Search Flights Button
        driver.findElement(By.id("SearchBtn")).submit();

        Thread.sleep(12000);

        //Check Non-Stop
        driver.findElement(By.xpath("//p[text()='Non-stop']")).click();

        Thread.sleep(2000);

        //Selecting th Range from the slider
        WebElement slider = driver.findElement(By.className("input-range__slider"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider,-128,0).build().perform();
        slider.click();

        //De-Selecting Multi-Airline itineraries
        Thread.sleep(2000);
        driver.findElement(By.xpath("//p[text()='Show multi-airline itineraries']")).click();

        //Book Button
        Thread.sleep(3000);
        driver.findElement(By.xpath("(//button[.='Book'])[1]")).click();

        //Switch Window
        Thread.sleep(15000);
        String winHandleBefore = driver.getWindowHandle();
        for(String winHandle : driver.getWindowHandles())
        {
            driver.switchTo().window(winHandle);
        }

        //Continue Button
        driver.findElement(By.id("itineraryBtn")).click();

        //Adding Email
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id=\"username\"]")).sendKeys("abcdefgh@gmail.com");

        //Continue Button After Email-Id
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@id='LoginContinueBtn_1']")).click();

        //Adding Traveller Details
        //Adult Details
        Thread.sleep(8000);
        Select drp = new Select(driver.findElement(By.xpath("//select[@name='AdultTitle1']")));
        drp.selectByVisibleText("Mrs");

        driver.findElement(By.xpath("//input[@name='AdultFname1']")).sendKeys("Rama");

        driver.findElement(By.xpath("//input[@name='AdultLname1']")).sendKeys("Suresh");

        //Child Details
        Select drp1 = new Select(driver.findElement(By.xpath("//select[@name='ChildTitle1']")));
        drp1.selectByVisibleText("Mstr");

        driver.findElement(By.xpath("//input[@name='ChildFname1']")).sendKeys("Kumar");

        driver.findElement(By.xpath("//input[@name='ChildLname1']")).sendKeys("Suresh");

        Select day = new Select(driver.findElement(By.xpath("//select[@id='ChildDobDay1']")));
        day.selectByVisibleText("14");
        Select month = new Select(driver.findElement(By.xpath("//select[@id='ChildDobMonth1']")));
        month.selectByVisibleText("Feb");
        Select year = new Select(driver.findElement(By.xpath("//select[@id='ChildDobYear1']")));
        year.selectByVisibleText("2017");

        //Infant Details
        Select drp2 = new Select(driver.findElement(By.xpath("//select[@name='InfantTitle1']")));
        drp2.selectByVisibleText("Mstr");

        driver.findElement(By.xpath("//input[@name='InfantFname1']")).sendKeys("Chinnu");

        driver.findElement(By.xpath("//input[@name='InfantLname1']")).sendKeys("Suresh");

        Select day1 = new Select(driver.findElement(By.xpath("//select[@id='InfantDobDay1']")));
        day1.selectByVisibleText("20");
        Select month1 = new Select(driver.findElement(By.xpath("//select[@id='InfantDobMonth1']")));
        month1.selectByVisibleText("Feb");
        Select year1 = new Select(driver.findElement(By.xpath("//select[@id='InfantDobYear1']")));
        year1.selectByVisibleText("2020");

        driver.findElement(By.xpath("//input[@name='contact1']")).sendKeys("9876543210");

        driver.findElement(By.xpath("//input[@id='travellerBtn']")).click();
        Thread.sleep(8000);
    }
    @AfterTest
    public void aftertestends() {
        driver.quit();
    }
}