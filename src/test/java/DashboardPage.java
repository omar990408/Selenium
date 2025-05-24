import org.openqa.selenium.WebDriver;

public class DashboardPage extends PageBase {

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void logout() {
        this.driver.navigate().to("https://webdriveruniversity.com/");
    }

    public boolean isAtMainPage() {
        return this.driver.getCurrentUrl().equals("https://webdriveruniversity.com/");
    }
}