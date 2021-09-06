import com.tngtech.junit.dataprovider.UseDataProvider;
import com.utils.LoggerUtils;
import com.utils.helperMeth;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import com.utils.XcelUtils;

public class TC00_1 extends helperMeth {


    private static final LoggerUtils LOGGER = new LoggerUtils(TC00_1.class);



    @Test
    @Order(1)
    @UseDataProvider ("createNewBooking")
    public void createBookingTest() {

        Response response = createBooking();
        response.prettyPrint();
        int expected = 200;
        int actual = response.getStatusCode();
        response.prettyPrint();
        Assertions.assertEquals(expected, actual, "It's supposed to be 200, but it is " + actual);
        System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
        LOGGER.info("GET call has been made");

        String actualFirstName = response.jsonPath().getString("booking.firstname");
        String expectedFirstName = "Carlos";
        Assertions.assertEquals(expectedFirstName, actualFirstName, "The name in the response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName);
        System.out.println("The name in response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName + ".");

        String actualLastName = response.jsonPath().getString("booking.lastname");
        String expectedLastName = "Adjetey";
        Assertions.assertEquals(expectedLastName, actualLastName, "The actual last name is " + actualLastName
                + ", and it was expected to be " + expectedLastName);

        int actualTotalPrice = response.jsonPath().getInt("booking.totalprice");
        int expectedTotalPrice = 300;
        Assertions.assertEquals(expectedTotalPrice, actualTotalPrice, "The actual total price is " + actualTotalPrice
                + ", and it was expected to be " + expectedTotalPrice);

        boolean actualDepositPaid = response.jsonPath().getBoolean("booking.depositpaid");
        boolean expectedDepositPaid = true;
        Assertions.assertEquals(expectedDepositPaid, actualDepositPaid, "The actual deposit paid status is " + actualDepositPaid
                + ", and it was expected to be " + expectedDepositPaid);

        String actualCheckIn = response.jsonPath().getString("booking.bookingdates.checkin");
        String actualCheckOut = response.jsonPath().getString("booking.bookingdates.checkout");
        String expectedCheckIn = "2021-09-05";
        String expectedCheckOut = "2021-09-20";
        Assertions.assertEquals(expectedCheckIn, actualCheckIn, "checkin in response is not expected");
        Assertions.assertEquals(expectedCheckOut, actualCheckOut, "checkout in response is not expected");

        String actualadditionalneeds = response.jsonPath().getString("booking.additionalneeds");
        String expectedadditionalneeds = "Breakfast";
        Assertions.assertEquals(expectedadditionalneeds, actualadditionalneeds, "checkout in response is not expected");

    }
}
