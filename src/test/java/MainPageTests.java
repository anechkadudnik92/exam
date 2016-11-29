import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Thread.*;

public class MainPageTests {
    public WebDriver webdriver;
    App app;
    WebDriverWait wait;

    @Before
    public void setUp(){
        webdriver = new ChromeDriver();
        //webdriver = new FirefoxDriver();
        webdriver.get("http://autotesttask.herokuapp.com/");
        wait = new WebDriverWait(webdriver,30);

        app = new App(webdriver);
    }

    @Test
    public void findFirstTest(){
       WebElement firstBug = app.mainPage().findBugByName("First bug");
        Assert.assertEquals("Note does not match name", "notes" , app.mainPage().getBugNotes(firstBug));
        Assert.assertEquals("Priority does not match name", "1" , app.mainPage().getBugPriority(firstBug));
    }

    @Test
    public void addBugTest(){
        app.mainPage().addButton().click();
        app.mainPage().addName("myname");
        app.mainPage().addNote("MyNote");
       // app.mainPage().addPriority("3");
        app.mainPage().apply();
        WebElement newbug = app.mainPage().findBugByName("myname");
        app.mainPage().deleteBug(newbug);
        Assert.assertTrue(app.mainPage().bugIsDeleted("myname"));

    }


    @Test
    public void addDialogTest(){
        app.mainPage().addDialog().click();
        app.mainPage().fillInDialog("myname1", "note", "3");
        WebElement newbug = app.mainPage().findBugByName("myname1");
      //  app.mainPage().deleteBug(newbug);
        Assert.assertTrue(app.mainPage().bugIsDeleted("myname1"));

    }

    @Test
    public void deleteBugTest(){
        WebElement bug = app.mainPage().findBugByName("myname");
        app.mainPage().deleteBug(bug);

    }

//    @Test
//    public void BugTest(){
//        Assert.assertTrue(app.mainPage().bugIsDeleted("First bummg"));
//
//    }

    @After
    public void quit(){
        if (webdriver!=null){
            webdriver.quit();
        }
    }

}
