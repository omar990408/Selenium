import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UploadFilePage extends PageBase{

    private By uploadFileLocator = By.id("myFile");
    private By uploadButtonLocator = By.id("submit-button");

    public UploadFilePage(WebDriver driver){
        super(driver);
        this.driver.get("https://webdriveruniversity.com/File-Upload/index.html");
    }

    public void uploadFile(String path) {
        this.waitAndReturnElement(uploadFileLocator).sendKeys(path);
        this.waitAndReturnElement(uploadButtonLocator).click();
    }

    public boolean isUploadedSuccessful() {
        try {
            this.wait.until(ExpectedConditions.alertIsPresent());
            String alertText = driver.switchTo().alert().getText();
            driver.switchTo().alert().accept();
            return alertText.equals("Your file has now been uploaded!");
        } catch (Exception e) {
            return false;
        }
    }
}
