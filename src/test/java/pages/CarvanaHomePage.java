package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class CarvanaHomePage {
    public CarvanaHomePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(css = "div[data-qa='logo-wrapper']")
    public WebElement logo;

    @FindBy(css = "div[data-qa='navigation-wrapper']>div>a")
    public List<WebElement> mainNavSectionItems;

    @FindBy()
    public WebElement mainSignInLink;

    @FindBy()
    public WebElement signInModal;

    @FindBy()
    public WebElement emailInputBox;

    @FindBy()
    public WebElement passwordInputBox;

    @FindBy()
    public WebElement modalSignInButton;

    @FindBy()
    public WebElement errorMessage;

    @FindBy(css = "a[data-cv-test='headerSearchLink']")
    public WebElement searchCarsLink;
}