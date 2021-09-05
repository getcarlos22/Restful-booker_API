import com.utils.LoggerUtils;
import com.utils.ReadConfig;
import com.utils.helperMeth;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class TC00_5 extends helperMeth {

    private static final LoggerUtils LOGGER = new LoggerUtils(TC00_5.class);

    ReadConfig readconfig=new ReadConfig();
    public String username=readconfig.getUsername();
    public String password=readconfig.getPassword();

    @Test
    public void deleteBookingTests(){
        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        int bookingid = responseCreate.jsonPath().getInt("bookingid");
        LOGGER.info("Booking ID Generated After Booking is " + bookingid);

        Response responseDelete = RestAssured.given(reqSpec).auth().preemptive().basic(username, password)
                .delete("/booking/" + bookingid);
        responseDelete.print();
        LOGGER.info("ID to be Deleted is " + bookingid);
        Assertions.assertEquals(201, responseDelete.getStatusCode(),  "Status code should be 201, but it's not.");

        Response responseGet = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingid);
        responseGet.print();
        Assertions.assertEquals("Not Found",responseGet.getBody().asString(),  "Body should be 'Not Found', but it's not.");
        LOGGER.info("Booking ID " + bookingid + " is Deleted Successfully");
    }
}
