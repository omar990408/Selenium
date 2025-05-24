import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage extends PageBase{

    private By titleLocator = By.xpath("//a[@id='nav-title']");

    public MainPage(WebDriver driver){
        super(driver);
        this.driver.get("https://webdriveruniversity.com/");
    }

    public String getTitleText() {
        return this.waitAndReturnElement(titleLocator).getText();
    }

}