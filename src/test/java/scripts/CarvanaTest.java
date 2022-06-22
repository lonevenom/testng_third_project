package scripts;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.CarvanaHomePage;
import pages.CarvanaSearchPage;
import utilities.ConfigReader;
import utilities.ExpectedTexts;
import utilities.Waiter;

public class CarvanaTest extends Base{

    CarvanaHomePage carvanaHomePage;
    CarvanaSearchPage carvanaSearchPage;

    @BeforeClass
    public void setupPages(){
        carvanaHomePage = new CarvanaHomePage();
        carvanaSearchPage = new CarvanaSearchPage();
    }

    /*
    Test Case 1:
    Test name = Validate Carvana home page title and url
    Test priority = 1
    Given user is on "https://www.carvana.com/"
    Then validate title equals to "Carvana | Buy & Finance Used Cars Online | At Home Delivery"
    And validate url equals to "https://www.carvana.com/"
     */


    @Test(priority = 1, description = "Validate Carvana home page title and url")
    public void testTitleAndUrl(){
        Assert.assertEquals(driver.getCurrentUrl(), ConfigReader.getProperty("url"));
        Assert.assertEquals(driver.getTitle(), "Carvana | Buy & Finance Used Cars Online | At Home Delivery");
    }

    /*
    Test Case 2:
    Test name = Validate the Carvana logo
    Test priority = 2
    Given user is on "https://www.carvana.com/"
    Then validate the logo of the “Carvana” is displayed
     */
    @Test(priority = 2, description = "Validate the Carvana logo")
    public void testLogo(){
        Assert.assertTrue(carvanaHomePage.logo.isDisplayed());
    }

    /*
    Test Case 3:
    Test name = Validate the main navigation section items
    Test priority = 3
    Given user is on "https://www.carvana.com/"
    Then validate the navigation section items below are displayed
    |HOW IT WORKS     	|
    |ABOUT CARVANA    	|
    |SUPPORT & CONTACT	|
     */
    @Test(priority = 3, description = "Validate the main navigation section items")
    public void testMainNavSectionItems(){
        //TODO replace this hard-coded wait with explicit wait
        Waiter.pause(3);
        for (int i = 0; i < 3; i++) {
            Assert.assertEquals(carvanaHomePage.mainNavSectionItems.get(i).getText(), ExpectedTexts.mainNavSectionItems[i]);
        }
    }

    /*
    Test Case 4:
    Test name = Validate the sign in error message
    Test priority = 4
    Given user is on "https://www.carvana.com/"
    When user clicks on “SIGN IN” button
    Then user should be navigated to “Sign in” modal
    When user enters email as “johndoe@gmail.com”
    And user enters password as "abcd1234"
    And user clicks on "SIGN IN" button
    Then user should see error message as "Email address and/or password combination is incorrect
    Please try again or reset your password."
     */

    @Test(priority = 4, description = "Validate the sign in error message")
    public void testSignInErrorMessage(){

    }

    /*
    Test Case 5:
    Test name = Validate the search filter options and search button
    Test priority = 5
    Given user is on "https://www.carvana.com/"
    When user clicks on "SEARCH CARS" link
    Then user should be routed to "https://www.carvana.com/cars"
    And user should see filter options
    |PAYMENT & PRICE     	|
    |MAKE & MODEL	    	|
    |BODY TYPE		        |
    |YEAR & MILEAGE     	|
    |FEATURES	    	    |
    |MORE FILTERS		    |
     */
    @Test(priority = 5, description = "Validate the search filter options and search button")
    public void testSearchFilterOptions(){

    }

    /*
    Test Case 6:
    Test name = Validate the search result tiles
    Test priority = 6
    Given user is on "https://www.carvana.com/"
    When user clicks on "SEARCH CARS" link
    Then user should be routed to "https://www.carvana.com/cars"
    When user enters "mercedes-benz" to the search input box
    And user clicks on "GO" button in the search input box
    Then user should see "mercedes-benz" in the url
    And validate each result tile
    VALIDATION OF EACH TILE INCLUDES BELOW
    Make sure each result tile is displayed with below information
    1. an image
    2. add to favorite button
    3. tile body
    ALSO VALIDATE EACH TILE BODY:
    Make sure each tile body has below information
    1. Inventory type - text should be displayed and should not be null
    2. Year-Make-Model information - text should be displayed and should not be null
    3. Trim-Mileage information - text should be displayed and should not be null
    4. Price - Make sure that each price is more than zero
    5. Monthly Payment information - text should be displayed and should not be null
    6. Down Payment information - text should be displayed and should not be null
    7. Delivery chip must be displayed as “Free Shipping”
     */
    @Test(priority = 6, description = "Validate the search result tiles")
    public void testSearchResultTiles(){
        Waiter.pause(3);
        carvanaHomePage.searchCarsLink.click();
        Assert.assertEquals(driver.getCurrentUrl(), ConfigReader.getProperty("url") + "cars");
        carvanaSearchPage.searchInputBox.sendKeys("mercedes-benz");
        carvanaSearchPage.goButton.click();

        while(carvanaSearchPage.paginationNextButton.isEnabled()){
            Assert.assertTrue(driver.getCurrentUrl().contains("mercedes-benz"));
            Waiter.pause(3);
            for (int i = 0; i < carvanaSearchPage.tiles.size(); i++) {
                Assert.assertTrue(carvanaSearchPage.tiles.get(i).isDisplayed());
                Assert.assertTrue(carvanaSearchPage.tileImages.get(i).isDisplayed());
                Assert.assertTrue(carvanaSearchPage.tileAddToFavoritesButtons.get(i).isDisplayed());
                Assert.assertTrue(carvanaSearchPage.tileInventoryTypes.get(i).isDisplayed());
                Assert.assertFalse(carvanaSearchPage.tileInventoryTypes.get(i).getText().isEmpty());
                Assert.assertNotNull(carvanaSearchPage.tileInventoryTypes.get(i).getText());
                Assert.assertTrue(carvanaSearchPage.tileMakeModelAndYears.get(i).isDisplayed());
                Assert.assertFalse(carvanaSearchPage.tileMakeModelAndYears.get(i).getText().isEmpty());
                Assert.assertNotNull(carvanaSearchPage.tileMakeModelAndYears.get(i).getText());
                Assert.assertTrue(carvanaSearchPage.tileTrimAndMileages.get(i).isDisplayed());
                Assert.assertFalse(carvanaSearchPage.tileTrimAndMileages.get(i).getText().isEmpty());
                Assert.assertNotNull(carvanaSearchPage.tileTrimAndMileages.get(i).getText());
                Assert.assertTrue(carvanaSearchPage.tilePrices.get(i).isDisplayed());
                Assert.assertTrue(Integer.parseInt(carvanaSearchPage.tilePrices.get(i).getText().replaceAll("[^0-9]", "")) > 0);
                Assert.assertTrue(carvanaSearchPage.tileMonthlyPayments.get(i).isDisplayed());
                Assert.assertFalse(carvanaSearchPage.tileMonthlyPayments.get(i).getText().isEmpty());
                Assert.assertNotNull(carvanaSearchPage.tileMonthlyPayments.get(i).getText());
                Assert.assertTrue(carvanaSearchPage.tileDownPayments.get(i).isDisplayed());
                Assert.assertFalse(carvanaSearchPage.tileDownPayments.get(i).getText().isEmpty());
                Assert.assertNotNull(carvanaSearchPage.tileDownPayments.get(i).getText());
                Assert.assertTrue(carvanaSearchPage.tileDeliveryChips.get(i).isDisplayed());
                Assert.assertFalse(carvanaSearchPage.tileDeliveryChips.get(i).getText().isEmpty());
                Assert.assertNotNull(carvanaSearchPage.tileDeliveryChips.get(i).getText());
            }
            carvanaSearchPage.paginationNextButton.click();
        }
    }
}