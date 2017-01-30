package pageobject

import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import utils.DriverFactory

import static org.assertj.core.api.Assertions.*;

class CartPage extends DefaultPage{

    @FindBy(xpath = "//*[@id=\"dnn_ctr12876_View_BasketDetail_CartContents\"]/table/tbody/tr[1]/td[3]/a[2]")
    private WebElement firstProductInstanceCount

    @FindBy(xpath = "//*[@id=\"dnn_ctr12876_View_BasketDetail_UpdateQty\"]")
    private WebElement updateBagBtn

    @FindBy(id = "dnn_ctr12876_View_BasketDetail_CartContents_ctl02_ProductTotalPrice")
    private static WebElement firstCostHolder

    @FindBy(id = "dnn_ctr12876_View_BasketDetail_CartContents_ctl03_ProductTotalPrice")
    private static WebElement secondCostHolder

    @FindBy(id = "dnn_ctr12876_View_BasketDetail_CartContents_ctl04_ProductTotalPrice")
    private static WebElement flyerCostHolder

    @FindBy(id = "dnn_ctr12876_View_BasketDetail_SubTotal")
    private static WebElement totalCostHolder

    public CartPage() {
        driver = new DriverFactory().getDriver()
        PageFactory.initElements(driver, this)
    }

    public CartPage increaseFirstProductInstance() {
        firstProductInstanceCount.click()
        updateBagBtn.click()
        log.info "-first item count increased, bag updated"
        this
    }

    static double getActualTotalCost() {
        getCostOf(firstCostHolder)+getCostOf(secondCostHolder)+
                getCostOf(flyerCostHolder) //since sportsdirect adds optional paid flyers
    }

    static void checkIfTotalCostIsCorrect() {
        log.info "-sum of all items: " + getActualTotalCost()
        log.info "-total cost displayed: " + getCalculatedTotalCost()
        assertThat(getActualTotalCost()).isEqualTo(getCalculatedTotalCost())
    }

    private static double getCalculatedTotalCost() {
        new BigDecimal(getCostOf(totalCostHolder))
    }

    private static double getCostOf(WebElement holder) {
        holder.getText().substring(1) as double
    }
}
