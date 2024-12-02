package Rest3;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
public class Testcases {

    @Test
    public void getCatFact() {
        given()
                .baseUri("https://catfact.ninja")
                .basePath("/fact")
                .when().get()
                .then().log().all()
                .statusCode(200)  // Verify that the response status is 200
                .body("fact", notNullValue());  // Ensure the "fact" field exists and is not null
    }

    @Test
    public void getBitcoinPriceIndex() {
        given()
                .baseUri("https://api.coindesk.com")
                .basePath("/v1/bpi/currentprice.json")
                .when().get()
                .then().log().all()
                .statusCode(200)  // Verify that the response status is 200
                .body("time.updated", notNullValue())  // Ensure the "time.updated" field exists
                .body("bpi.USD.rate", notNullValue())  // Check that the USD rate is present
                .body("bpi.USD.code", equalTo("USD")); // Verify the currency code is "USD"
    }
    @Test
    public void getPredictedAgeForName() {
        given()
                .baseUri("https://api.agify.io")
                .queryParam("name", "meelad")  // Pass the name as a query parameter
                .when().get()
                .then().log().all()
                .statusCode(200)  // Verify that the response status is 200
                .body("name", equalTo("meelad"))  // Ensure the name in the response matches the query parameter
                .body("age", greaterThanOrEqualTo(0))  // Ensure age is a valid non-negative number
                .body("count", greaterThan(0));  // Verify that the count of occurrences is greater than 0
    }

    @Test
    public void getPopulationData() {
        given()
                .baseUri("https://datausa.io")
                .basePath("/api/data")
                .queryParam("drilldowns", "Nation")  // Query parameter for drilling down by Nation
                .queryParam("measures", "Population")  // Query parameter for population measure
                .when().get()
                .then().log().all()
                .statusCode(200)  // Verify that the response status is 200
                .body("data", notNullValue())  // Ensure that "data" exists in the response
                .body("data.size()", greaterThan(0))  // Ensure that "data" contains at least one record
                .body("data[0].Nation", notNullValue())  // Ensure that the "Nation" field is present in the first record
                .body("data[0].Population", greaterThan(0));  // Verify that the "Population" field is a positive number
    }
    @Test
    public void getRandomDogImage() {
        given()
                .baseUri("https://dog.ceo")
                .basePath("/api/breeds/image/random")  // Endpoint for random dog image
                .when().get()
                .then().log().all()
                .statusCode(200)  // Verify that the response status is 200
                .body("message", notNullValue())  // Ensure the "message" field is present
                .body("status", equalTo("success"))  // Verify the "status" field is "success"
                .body("message", containsString("https://"));  // Ensure the "message" contains a valid URL (image)
    }
}
