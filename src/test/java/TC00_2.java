import com.utils.LoggerUtils;
import com.utils.helperMeth;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TC00_2 extends helperMeth {


    private static final LoggerUtils LOGGER = new LoggerUtils(TC00_2.class);
    @Test
    public void getBookingIDs(){
        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        int bookingid = responseCreate.jsonPath().getInt("bookingid");
        reqSpec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));
        Response response =
                RestAssured.given(reqSpec).get("/booking/{bookingId}");
        response.prettyPrint();
        LOGGER.info("Booking ID Passed for the Get Booking: " +bookingid);

        Header json = new Header("Accept", "application/json");
        reqSpec.header(json);
        int expected = 200;
        int actual = response.getStatusCode();
        Assertions.assertEquals(expected, actual, "It's supposed to be 200, and currently is " + actual);
        System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
        LOGGER.info("Returned " + actual );


        String actualFirstName = response.jsonPath().getString("firstname");
        String expectedFirstName = "Carlos";
        Assertions.assertEquals(expectedFirstName, actualFirstName, "The name in the response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName);
        System.out.println("The actual name in response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName + ".");
        LOGGER.info("Validating the response for first name " + actualFirstName);


        String actualLastName = response.jsonPath().getString("lastname");
        String expectedLastName = "Adjetey";
        Assertions.assertEquals(expectedLastName, actualLastName, "The actual last name is " + actualLastName
                + ", and it was expected to be " + expectedLastName);
        System.out.println("The actual surname in response is "
                + actualLastName + ", and it's supposed to be " + expectedLastName + ".");
        LOGGER.info("Validating the response for last name " + actualLastName);

        int actualTotalPrice = response.jsonPath().getInt("totalprice");
        int expectedTotalPrice = 300;
        Assertions.assertEquals(expectedTotalPrice, actualTotalPrice, "The actual total price is " + actualTotalPrice
                + ", and it was expected to be " + expectedTotalPrice);
        System.out.println("The actual total price in response is "
                + actualTotalPrice + ", and it's supposed to be " + expectedTotalPrice + ".");
        LOGGER.info("Validating the response for Total Price " + actualTotalPrice);

        boolean actualDepositPaid = response.jsonPath().getBoolean("depositpaid");
        boolean expectedDepositPaid = true;
        Assertions.assertEquals(expectedDepositPaid, actualDepositPaid, "The actual deposit paid status is " + actualDepositPaid
                + ", and it was expected to be " + expectedDepositPaid);
        System.out.println("The actual \"expected deposit paid in\" response is "
                + actualDepositPaid + ", and it's supposed to be " + expectedDepositPaid + ".");
        LOGGER.info("Validating the response for Deposit Paid " + actualDepositPaid );

        String actualCheckIn = response.jsonPath().getString("bookingdates.checkin");
        String actualCheckOut = response.jsonPath().getString("bookingdates.checkout");
        String expectedCheckIn = "2021-09-05";
        String expectedCheckOut = "2021-09-20";
        Assertions.assertEquals(expectedCheckIn, actualCheckIn, "checkin in response is not expected");
        LOGGER.info("Validating the response for checkin " + actualCheckIn );

        Assertions.assertEquals(expectedCheckOut, actualCheckOut, "checkout in response is not expected");
        System.out.println("The actual check-in date in response is "
                + actualCheckIn + ", and it's supposed to be " + expectedCheckIn + ".");
        System.out.println("The actual check-out date in response is "
                + actualCheckOut + ", and it's supposed to be " + expectedCheckOut + ".");
        LOGGER.info("Validating the response for check-in date " + actualCheckOut );

    }
}
