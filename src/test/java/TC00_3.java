import com.utils.LoggerUtils;
import com.utils.ReadConfig;
import com.utils.helperMeth;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TC00_3 extends helperMeth {

    ReadConfig readconfig=new ReadConfig();
    public String username=readconfig.getUsername();
    public String password=readconfig.getPassword();
    private static final LoggerUtils LOGGER = new LoggerUtils(TC00_3.class);

    @Test
    public void PartialUpdateBookingTests(){
        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        JSONObject body = new JSONObject();
        body.put("firstname", "Irina");
        LOGGER.info("Updated firstname");

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-06-01");
        body.put("bookingdates", bookingdates);
        LOGGER.info("Updated checkin date");



        int bookingId = responseCreate.jsonPath().getInt("bookingid");
        Response responseUpdate = RestAssured.given(reqSpec)
                .auth().preemptive().basic(username, password)
                .contentType(ContentType.JSON).body(body.toString())
                .patch("/booking/" + bookingId);
        LOGGER.info("Booking ID is " + bookingId);

        int expected = 200;
        int actual = responseUpdate.getStatusCode();
        Assertions.assertEquals(expected, actual, "Status code is supposed to be 200, but it is " + actual);
        System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
        LOGGER.info("Returned " + actual );

        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        String expectedFirstName = "Irina";
        Assertions.assertEquals(expectedFirstName, actualFirstName, "The name in the response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName);
        LOGGER.info("Returned " + actualFirstName + " For First Name" );

        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        String expectedCheckIn = "2021-06-01";
        Assertions.assertEquals(expectedCheckIn, actualCheckIn, "checkin in response is not expected");
        LOGGER.info("Returned " + actualCheckIn + " For Checkin" );
        responseUpdate.prettyPrint();
    }

    @Test
    public void updateBookingTests(){
        Response responseCreate = createBooking();
        responseCreate.print();

        JSONObject body = new JSONObject();
        body.put("firstname", "Delphine");
        body.put("lastname", "Adjei");
        body.put("totalprice", 150);
        body.put("depositpaid", false);
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-06-03");
        bookingdates.put("checkout", "2021-06-10");
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Swimming Pool");

        int bookingId = responseCreate.jsonPath().getInt("bookingid");
        Response responseUpdate = RestAssured.given(reqSpec)
                .auth().preemptive().basic("admin", "password123")
                .contentType(ContentType.JSON).body(body.toString())
                .put("/booking/" + bookingId);
        LOGGER.info("Returned " + bookingId + " For Booking ID" );

        int expected = 200;
        int actual = responseUpdate.getStatusCode();
        Assertions.assertEquals(expected, actual, "Status code is supposed to be 200, but it is " + actual);
        System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
        LOGGER.info("Returned " + actual );

        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        String expectedFirstName = "Delphine";
        Assertions.assertEquals(expectedFirstName, actualFirstName, "The name in the response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName);
        LOGGER.info("Returned " + actualFirstName + " For First Name" );

        String actualLastName = responseUpdate.jsonPath().getString("lastname");
        String expectedLastName = "Adjei";
        Assertions.assertEquals(expectedLastName, actualLastName, "The actual last name is " + actualLastName
                + ", and it was expected to be " + expectedLastName);
        LOGGER.info("Returned " + actualLastName + " For Last Name" );


        int actualTotalPrice = responseUpdate.jsonPath().getInt("totalprice");
        int expectedTotalPrice = 150;
        Assertions.assertEquals(expectedTotalPrice,actualTotalPrice, "The actual total price is " + actualTotalPrice
                + ", and it was expected to be " + expectedTotalPrice);
        LOGGER.info("Returned " + actualTotalPrice + " For Total Price" );


        boolean actualDepositPaid = responseUpdate.jsonPath().getBoolean("depositpaid");
        boolean expectedDepositPaid = false;
        Assertions.assertEquals(expectedDepositPaid,actualDepositPaid, "The actual deposit paid status is " + actualDepositPaid
                + ", and it was expected to be " + expectedDepositPaid);
        LOGGER.info("Returned " + actualDepositPaid + " For Deposite Paid" );

        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        String actualCheckOut = responseUpdate.jsonPath().getString("bookingdates.checkout");
        String expectedCheckIn = "2021-06-03";
        String expectedCheckOut = "2021-06-10";
        Assertions.assertEquals(expectedCheckIn,actualCheckIn, "checkin in response is not expected");
        Assertions.assertEquals(expectedCheckOut,actualCheckOut, "checkout in response is not expected");
        LOGGER.info("Returned " + actualCheckIn + " For Checkin" );
        LOGGER.info("Returned " + actualCheckOut + " For Checkout" );

    }
}
