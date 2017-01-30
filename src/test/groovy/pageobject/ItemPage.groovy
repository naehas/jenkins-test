package pageobject

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import utils.DriverFactory

class ItemPage extends DefaultPage {

    @FindBy(className = "addToBag")
    private WebElement addTobagBtn

    public ItemPage() {
        driver = new DriverFactory().getDriver()
        PageFactory.initElements(driver, this)
    }

    public ItemPage buyItem() {
        addTobagBtn.click()
        log.info "-item bought: " + driver.getCurrentUrl()
        this
    }
}
