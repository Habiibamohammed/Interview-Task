package StepDef;

import Helpers.DriverActions;
import Helpers.DriverHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
// import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StepsOfFlow {

    private String mobilePhone;
    private String passwordUser;
    private String searchItem;

    public StepsOfFlow() throws IOException {
        String loginJsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/DataFile.json")));
        JSONArray jsonArray = new JSONArray(loginJsonContent);
        JSONObject jsonObject = jsonArray.getJSONObject(0);
        mobilePhone = jsonObject.getString("MobilePhone");
        passwordUser = jsonObject.getString("Password");

        String searchJsonContent = new String(Files.readAllBytes(Paths.get("src/main/resources/SearchData.json")));
        JSONArray searchJsonArray = new JSONArray(searchJsonContent);
        JSONObject sJsonObject = searchJsonArray.getJSONObject(0);
        searchItem = sJsonObject.getString("search");
    }

    By loginIconInHope = By.xpath("//*[@id='userProfileMenu']");
    By mobileNumber = By.xpath("//*[@tabindex='1']");
    By password = By.xpath("//*[@tabindex='2']");
    By button = By.xpath("//input[@value='Go to my account']");
    By cookies = By.xpath("//*[@id='onetrust-accept-btn-handler']");
    By firstItem = By.xpath("(//img)[1]");
    By secondItem = By.xpath("(//img)[2]']");
    By addToCart = By.xpath("//*[@class='add-to-cart']");
    By getBackToHome = By.xpath("//img[@alt=\"vodafone's logo\"]");
    By searchIcon = By.xpath("//*[@title='Search for:']");
    By cartIcon = By.xpath("//img[@alt='shopping trolly']");
    By cookieReject = By.xpath("//button[@id=\"onetrust-reject-all-handler\"]");
    By cancelButton = By.xpath("//button[@class='close-modal-desktop']");

    // @Test
    @Given("User logged to the website")
    public void user_logged_to_store() {
        WebDriverManager.edgedriver().setup();
        DriverHelper.getInstance().get("https://eshop.vodafone.com.eg/ar/");
        DriverActions.getInstance().click(cookieReject);
        DriverActions.getInstance().click(cancelButton);
        DriverActions.getInstance().click(loginIconInHope);
        DriverActions.getInstance().sendKeys(mobileNumber, mobilePhone);
        DriverActions.getInstance().sendKeys(password, passwordUser);
        DriverActions.getInstance().click(button);
    }

    // @Test
    @When("user select products to cart")
    public void user_select_products_to_cart() throws InterruptedException {
        DriverHelper.getInstance().wait(3000); // Replace with explicit wait if necessary
        DriverActions.getInstance().click(firstItem);
        DriverActions.getInstance().click(addToCart);
        DriverActions.getInstance().click(getBackToHome);
        DriverActions.getInstance().click(secondItem);
        DriverActions.getInstance().click(addToCart);
        DriverActions.getInstance().click(getBackToHome);
    }

    // @Test
    @And("user add product")
    public void user_add_product() throws InterruptedException {
        DriverHelper.getInstance().wait(3000); // Replace with explicit wait if necessary
        DriverActions.getInstance().click(searchIcon);
        DriverActions.getInstance().sendKeys(searchIcon, searchItem);
        DriverActions.getInstance().click(addToCart);
        DriverActions.getInstance().click(getBackToHome);
    }

    // @Test
    @Then("open cart to check order")
    public void open_cart_to_check_order() throws InterruptedException {
        DriverHelper.getInstance().wait(3000);
        DriverActions.getInstance().click(cancelButton);
        DriverActions.getInstance().click(cartIcon);
    }
}
