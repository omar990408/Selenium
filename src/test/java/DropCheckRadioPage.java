import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DropCheckRadioPage extends PageBase{

    private By dropdownLocator = By.xpath("//div//select[@id='dropdowm-menu-1']");
    private  By radioGroupColorLocator = By.xpath("//form[@id='radio-buttons']//input[@type='radio']");

    public DropCheckRadioPage(WebDriver driver){
        super(driver);
        this.driver.get("https://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
    }

    public String getSelectedDropdownOption() {
        return this.waitAndReturnElement(dropdownLocator).getAttribute("value");
    }

    public String getSelectedRadioOption(String value) {
        List<WebElement> radioGroupColor = this.driver.findElements(radioGroupColorLocator);
        for (WebElement radio : radioGroupColor) {
            if (radio.getAttribute("value").equalsIgnoreCase(value)) {
                if (!radio.isSelected()) {
                    radio.click();
                }
                return radio.getAttribute("value");
            }
        }
        throw new RuntimeException("not Found") ;
    }


}
