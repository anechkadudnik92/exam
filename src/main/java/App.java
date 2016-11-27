import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

public class App {
    private WebDriver webDriver;
    private WebDriverWait wait;

    public App(WebDriver driver) {
        webDriver = driver;
    }

    public MainPage mainPage(){
        return new MainPage(webDriver);
    }
}
