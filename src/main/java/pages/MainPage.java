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
    List<WebElement> bugs;
    WebElement button;
    String notes;
    String priority;
    Actions action;

    public MainPage(WebDriver driver){
        webDriver = driver;
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
        WebElement name = bugs.get(bugs.size()-1).findElements(By.tagName("td")).get(2).findElement(By.tagName("div"));
        action.doubleClick(name).perform();
        webDriver.findElement(By.cssSelector("div[class='x-panel-header x-panel-header-default x-horizontal x-panel-header-horizontal x-panel-header-default-horizontal x-top x-panel-header-top x-panel-header-default-top x-unselectable x-docked x-docked-top x-panel-header-docked-top x-panel-header-default-docked-top']"))
        .findElements(By.cssSelector("div[class='x-editor x-small-editor x-grid-editor x-editor-default x-layer']")).get(1)
                .findElement(By.tagName("input")).sendKeys(myname, Keys.ENTER);
    }

    public void addNote(String mynote){
        action = new Actions(webDriver);
        WebElement note = bugs.get(bugs.size()-1).findElements(By.tagName("td")).get(3).findElement(By.tagName("div"));
        action.doubleClick(note).perform();
        webDriver.findElement(By.cssSelector("div[class='x-panel-header x-panel-header-default x-horizontal x-panel-header-horizontal x-panel-header-default-horizontal x-top x-panel-header-top x-panel-header-default-top x-unselectable x-docked x-docked-top x-panel-header-docked-top x-panel-header-default-docked-top']"))
                .findElements(By.cssSelector("div[class='x-editor x-small-editor x-grid-editor x-editor-default x-layer']")).get(2)
                .findElement(By.tagName("textarea")).sendKeys(mynote);
    }

    public void addPriority(String mypriority){
        action = new Actions(webDriver);
        WebElement note = bugs.get(bugs.size()-1).findElements(By.tagName("td")).get(4).findElement(By.tagName("div"));
        action.doubleClick(note);
        webDriver.findElement(By.cssSelector("div[class='x-panel-header x-panel-header-default x-horizontal x-panel-header-horizontal x-panel-header-default-horizontal x-top x-panel-header-top x-panel-header-default-top x-unselectable x-docked x-docked-top x-panel-header-docked-top x-panel-header-default-docked-top']"))
                .findElements(By.cssSelector("div[class='x-editor x-small-editor x-grid-editor x-editor-default x-layer']")).get(3)
                .findElement(By.tagName("input")).sendKeys(mypriority);
    }

    public void deleteBug(WebElement bug){
        bug.click();
        try {
            webDriver.findElements(By.cssSelector("div[class='x-btn x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']"))
                    .get(3).findElement(By.tagName("span")).click();
            Alert alert = webDriver.switchTo().alert();
            alert.accept();

        }
        catch (Exception ex){
            throw new RuntimeException("Button 'Delete' was not found");
        }

    }

    public WebElement addDialog(){
        WebElement buttton =  webDriver.findElements(By.cssSelector("div[class='x-btn x-box-item x-toolbar-item x-btn-default-toolbar-small x-noicon x-btn-noicon x-btn-default-toolbar-small-noicon']"))
                .get(2).findElement(By.tagName("span"));
        return buttton;
    }

    public void fillInDialog(String name, String note, String priority){
        Alert alert = webDriver.switchTo().alert();
        webDriver.findElement(By.cssSelector("input[name = 'name']")).sendKeys(name);
        webDriver.findElement(By.cssSelector("textarea[name = 'note']")).sendKeys(note);
        webDriver.findElement(By.cssSelector("input[name = 'priority']")).sendKeys(priority);
        alert.accept();
    }

}
