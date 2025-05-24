import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;


public class SeleniumTest {
    private WebDriver driver;

    @Before
    public void setup()  throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        ((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginAndSendFormAndLogOut() {
        LoginPage loginPage = new LoginPage(this.driver);
        loginPage.login("webdriver", "webdriver123");
        Assert.assertTrue("Login should be successful", loginPage.isLoginSuccessful());
        //Send Form
        ContacUsPage contacUsPage = new ContacUsPage(this.driver);
        ThankYouPage thankYouPage = contacUsPage.fillContactForm("Test", "Test", "test@gmail.com","Message Test");
        String bodyText = thankYouPage.getBodyText();
        Assert.assertTrue("Body text should contain 'Thank You for your Message'", bodyText.contains("Thank You for your Message!"));
        //Log Out Simulated because the logout button is not available in the current version of the page
        DashboardPage dashboardPage = new DashboardPage(this.driver);
        dashboardPage.logout();
        Assert.assertTrue("Should be at main page after logout", dashboardPage.isAtMainPage());
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

    @Test
    public void uploadFile() {
        UploadFilePage uploadFilePage = new UploadFilePage(this.driver);
        String path = new File("files/test.txt").getAbsolutePath();
        uploadFilePage.uploadFile(path);
        Assert.assertTrue("Uploaded successful", uploadFilePage.isUploadedSuccessful());
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
