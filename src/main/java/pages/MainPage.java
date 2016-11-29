package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.security.Credentials;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class MainPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private List<WebElement> bugs;
    private WebElement button;
    private String notes;
    private String priority;
    private Actions action;

    public MainPage(WebDriver driver){
        webDriver = driver;
        wait = new WebDriverWait(webDriver, 30);
    }

    public WebElement findBugByName(String name){
        WebElement bug = null;

        try {
            bugs = webDriver.findElement(By.cssSelector("table[class='x-grid-table x-grid-table-resizer']")).findElements(By.tagName("tr"));
        } catch (Exception ex){
            throw new RuntimeException("Bug block was not found");
        }

        for (int i=1; i<bugs.size()-1; i++) {
            if (bugs.get(i).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(name)) {
                bug = bugs.get(i);
                break;
            }
        }
            if (!bugs.get(bugs.size()-1).findElements(By.tagName("td")).get(2).findElement(By.tagName("div")).getText().equals(name) && bug==null)
                throw new RuntimeException("There is no bug with name: " + name);

        return bug;
    }

    public String getBugNotes(WebElement bug){
        notes = bug.findElements(By.tagName("td")).get(3).findElement(By.tagName("div")).getText();
        return notes;
    }

    public String getBugPriority(WebElement bug){
        priority = bug.findElements(By.tagName("td")).get(4).findElement(By.tagName("div")).getText();
        return priority;
    }

    public WebElement addButton(){
        try {
            button = webDriver.findElements(By.cssSelector("div[class='x-btn x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']"))
                    .get(0).findElement(By.tagName("span"));
        }
        catch (Exception ex){
            throw new RuntimeException("Button was not found");
        }
        return button;
    }

    public void addName(String myname){
        action = new Actions(webDriver);
        bugs = webDriver.findElement(By.cssSelector("table[class='x-grid-table x-grid-table-resizer']")).findElements(By.tagName("tr"));
        WebElement name = bugs.get(bugs.size()-1).findElements(By.tagName("td")).get(2).findElement(By.tagName("div"));
        action.doubleClick(name).perform();
        webDriver.findElements(By.cssSelector("div[class='x-editor x-small-editor x-grid-editor x-editor-default x-layer']")).get(0)
                .findElement(By.tagName("input")).sendKeys(myname, Keys.ENTER);
    }

    public void addNote(String mynote){
        action = new Actions(webDriver);
        bugs = webDriver.findElement(By.cssSelector("table[class='x-grid-table x-grid-table-resizer']")).findElements(By.tagName("tr"));
        WebElement note = bugs.get(bugs.size()-1).findElements(By.tagName("td")).get(3).findElement(By.tagName("div"));
        action.doubleClick(note).perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='x-editor x-small-editor x-grid-editor x-editor-default x-layer']")));
        webDriver.findElements(By.cssSelector("div[class='x-editor x-small-editor x-grid-editor x-editor-default x-layer']")).get(1)
                        .findElement(By.tagName("textarea")).sendKeys(mynote);
    }

    public void addPriority(String mypriority){
        action = new Actions(webDriver);
        bugs = webDriver.findElement(By.cssSelector("table[class='x-grid-table x-grid-table-resizer']")).findElements(By.tagName("tr"));
        WebElement priority = bugs.get(bugs.size()-1).findElements(By.tagName("td")).get(4).findElement(By.tagName("div"));
        action.doubleClick(priority);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='x-field x-form-item x-field-default x-form-dirty']")));
        webDriver.findElement(By.cssSelector("div[class='x-field x-form-item x-field-default x-form-dirty']"))
                .findElement(By.tagName("input")).sendKeys(mypriority, Keys.ENTER);
    }

    public WebElement apply(){
        WebElement button =  webDriver.findElements(By.cssSelector("div[class='x-btn x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']"))
                .get(1).findElement(By.tagName("span"));
        return button;
    }

    public void deleteBug(WebElement bug){
        bug.click();
        try {
            webDriver.findElements(By.cssSelector("div[class='x-btn x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']"))
                    .get(3).findElement(By.tagName("span")).click();
        }
        catch (Exception ex){
            throw new RuntimeException("Button 'Delete' was not found");
        }
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='x-box-inner']")));
        webDriver.findElement(By.cssSelector("div[class='x-toolbar x-window-item x-toolbar-footer x-docked x-docked-bottom x-toolbar-docked-bottom x-toolbar-footer-docked-bottom x-box-layout-ct']"))
                .findElement(By.cssSelector("div[class='x-box-inner']"))
                .findElements(By.cssSelector("div[class='x-btn x-box-item x-toolbar-item x-btn-default-small x-noicon x-btn-noicon x-btn-default-small-noicon']"))
                .get(1).findElement(By.tagName("button")).click();

    }

    public WebElement addDialog(){
        WebElement buttton =  webDriver.findElements(By.cssSelector("div[class='x-btn x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']"))
                .get(2).findElement(By.tagName("span"));
        return buttton;
    }
    public void fillInDialog(String name, String note, String priority){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='x-window-header-text x-window-header-text-default']")));
        webDriver.findElement(By.name("name")).sendKeys(name);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("notes")));
        webDriver.findElement(By.name("notes")).sendKeys(note);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.name("priority")));
        webDriver.findElement(By.name("priority")).sendKeys(priority);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("button[data-qtip='OK']")));
        webDriver.findElement(By.cssSelector("button[data-qtip='OK']")).click();

    }

}
