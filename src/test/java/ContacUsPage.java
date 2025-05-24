import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class ContacUsPage extends PageBase{

    private By nameLocator = By.xpath("//input[@name='first_name']");
    private By lastNameLocator = By.xpath("//input[@name='last_name']");
    private By emailLocator = By.xpath("//input[@name='email']");
    private By messageLocator = By.xpath("//textarea[@name='message']");

    public ContacUsPage(WebDriver driver){
        super(driver);
        this.driver.get("https://webdriveruniversity.com/Contact-Us/contactus.html");
    }

    public ThankYouPage fillContactForm(String name, String lastName, String email, String message) {
        this.waitAndReturnElement(nameLocator).sendKeys(name);
        this.waitAndReturnElement(lastNameLocator).sendKeys(lastName);
        this.waitAndReturnElement(emailLocator).sendKeys(email);
        this.waitAndReturnElement(messageLocator).sendKeys(message);
        this.waitAndReturnElement(messageLocator).submit();
        return new ThankYouPage(this.driver);

    }

}
