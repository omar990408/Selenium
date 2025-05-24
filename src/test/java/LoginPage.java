import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

class LoginPage extends PageBase {


    private By usernameLocator = By.id("text");
    private By passwordLocator = By.id("password");
    private By loginButtonLocator = By.xpath("//button[@id='login-button']");


    public LoginPage(WebDriver driver){
        super(driver);
        this.driver.get("https://webdriveruniversity.com/Login-Portal/index.html");
    }

    public void login(String username, String password){
        this.waitAndReturnElement(usernameLocator).sendKeys(username);
        this.waitAndReturnElement(passwordLocator).sendKeys(password);
        this.waitAndReturnElement(loginButtonLocator).click();
    }

    public boolean isLoginSuccessful() {
        try {
            this.wait.until(ExpectedConditions.alertIsPresent());
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            return alertText.equals("validation succeeded");
        } catch (Exception e) {
            return false;
        }
    }

}
