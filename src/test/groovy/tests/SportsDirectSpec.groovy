package tests

import pageobject.CartPage
import pageobject.DefaultPage
import spock.lang.Subject

class SportsDirectSpec extends BaseSpec {

    @Subject
    def user = new DefaultPage()

    def "should calculate bag cost correctly"() {

        given: "user has two products in bag"
        user.goToMainPage()
            .getProductList().chooseFirstItem().buyItem()
            .getProductList().chooseSecondItem().buyItem()

        when: "user add product instance from bag view"
        user.getCartPage().increaseFirstProductInstance()

        then: "bag cost is correct"
        CartPage.checkIfTotalCostIsCorrect()
    }

}


