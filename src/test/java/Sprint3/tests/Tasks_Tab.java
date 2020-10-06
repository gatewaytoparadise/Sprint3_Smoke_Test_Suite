package Sprint3.tests;

import Sprint3.util.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Tasks_Tab {

    WebDriver driver;
    String browserType = "chrome";
    //Truck driver Credentials
    String URL = "https://login2.nextbasecrm.com/";
    String userName = "helpdesk28@cybertekschool.com";
    String password = "UserUser";

    @BeforeMethod
    public void setUp(){
        driver = WebDriverFactory.getDriver(browserType);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(URL);
        driver.findElement(By.xpath("//input[@name='USER_LOGIN']")).sendKeys(userName);
        driver.findElement(By.xpath("//input[@name='USER_PASSWORD']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Test
    public void VerifyDeadline() {
        // Selects "Task" tab on the main selection menu
        driver.findElement(By.xpath("//*[@id=\"feed-add-post-form-tab-tasks\"]/span")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //Selects "Deadline" textbox
        driver.findElement(By.xpath("//*[@id=\"bx-component-scope-lifefeed_task_form\"]/div/div[3]/div[2]/div/div[1]/span[1]/span/input[1]")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


        // In the date picker pop up window, select Halloween for the day of the "DEAD"line lololol
        {
            List<WebElement> elements = driver.findElements(By.xpath("//*[@id=\"popup-window-content-calendar_popup_0.866357455244481\"]/div"));

            for (WebElement element : elements) {
                System.out.println(element.getText());

//Selecting the month
                if (element.getText().equals("October")) {

//Selecting the date
                    List<WebElement> days = driver.findElements(By.xpath("//div[@class='bx-calendar-cell bx-calendar-weekend bx-calendar-active "));

                    for (WebElement d : days) {
                        System.out.println(d.getText());
                        if (d.getText().equals(31)) {
                            d.click();
                            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                            return;
                        }
                    }

                }

            }

            // Click on <Select> button on the date-picker(calendar) pop up window
            driver.findElement(By.xpath("//a[.='Select']")).click();
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            //Click on "Time Planning" link text
            //driver.findElement(By.xpath("/div/div[5]/a[1]/span[2]")).click();
            //driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
