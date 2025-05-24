import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class SeleniumTest {
    private WebDriver driver;

    @Before
    public void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("webdriver", "webdriver123");
        Assert.assertTrue("Login should be successful", loginPage.isLoginSuccessful());
    }

    @Test
    public void testStaticPage() {
        MainPage mainPage = new MainPage(this.driver);
        String titleText = mainPage.getTitleText();
        Assert.assertTrue(this.driver.getCurrentUrl().contains("webdriveruniversity.com"));
        Assert.assertTrue("Body text should contain 'WebDriver'", titleText.contains("WebdriverUniversity.com (New Approach To Learning)"));
    }

    @Test
    public void testSendForm() throws InterruptedException {
        ContacUsPage  contacUsPage = new ContacUsPage(this.driver);
        ThankYouPage thankYouPage = contacUsPage.fillContactForm("Omar", "Gonzalez", "test@test.com", "This is a test message");
        String bodyText = thankYouPage.getBodyText();
        Assert.assertTrue("Body text should contain 'Thank You for your Message'", bodyText.contains("Thank You for your Message!"));
    }

    @Test
    public void testDropDown() {
        DropCheckRadioPage dropCheckRadioPage = new DropCheckRadioPage(this.driver);
        String dropValue =  dropCheckRadioPage.getSelectedDropdownOption();
        System.out.println(dropValue);
        Assert.assertEquals("Selected dropdown option should be 'java'", "java", dropValue);
    }

    @Test
    public void testRadio() {
        DropCheckRadioPage dropCheckRadioPage = new DropCheckRadioPage(this.driver);
        String valueRadio = dropCheckRadioPage.getSelectedRadioOption("blue");
        Assert.assertEquals("Selected radio button should be 'blue'", "blue", valueRadio);
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
