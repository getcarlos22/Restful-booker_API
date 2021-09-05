import com.utils.helperMeth;
import io.restassured.RestAssured;
import io.restassured.http.*;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class RestfulBookerTests extends helperMeth {

    @Test
    public void createBookingTest() {

        Response response = createBooking();
        response.prettyPrint();

        int expected = 200;
        int actual = response.getStatusCode();
        response.prettyPrint();

        Assertions.assertEquals(expected, actual, "It's supposed to be 200, but it is " + actual);

        System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
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

 @Test
    public void deleteBookingTests(){
     Response responseCreate = createBooking();
     responseCreate.prettyPrint();
     int bookingid = responseCreate.jsonPath().getInt("bookingid");
     Response responseDelete = RestAssured.given(reqSpec).auth().preemptive().basic("admin", "password123")
             .delete("/booking/" + bookingid);
     responseDelete.print();
     Assertions.assertEquals(201, responseDelete.getStatusCode(),  "Status code should be 201, but it's not.");

     Response responseGet = RestAssured.get("https://restful-booker.herokuapp.com/booking/" + bookingid);
     responseGet.print();

     Assertions.assertEquals("Not Found",responseGet.getBody().asString(),  "Body should be 'Not Found', but it's not.");
 }

 @Test
    public void getBookingIDs(){
     Response responseCreate = createBooking();
     responseCreate.prettyPrint();

     reqSpec.pathParam("bookingId", responseCreate.jsonPath().getInt("bookingid"));

     Response response =
             RestAssured.given(reqSpec).get("/booking/{bookingId}");
     response.prettyPrint();

     Header json = new Header("Accept", "application/json");
     reqSpec.header(json);
     int expected = 200;
     int actual = response.getStatusCode();
     Assertions.assertEquals(expected, actual, "It's supposed to be 200, but it is " + actual);
     System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
     String actualFirstName = response.jsonPath().getString("firstname");
     String expectedFirstName = "Carlos";
     Assertions.assertEquals(expectedFirstName, actualFirstName, "The name in the response is "
             + actualFirstName + ", and it's supposed to be " + expectedFirstName);
     System.out.println("The actual name in response is "
             + actualFirstName + ", and it's supposed to be " + expectedFirstName + ".");
     String actualLastName = response.jsonPath().getString("lastname");
     String expectedLastName = "Adjetey";
     Assertions.assertEquals(expectedLastName, actualLastName, "The actual last name is " + actualLastName
             + ", and it was expected to be " + expectedLastName);
     System.out.println("The actual surname in response is "
             + actualLastName + ", and it's supposed to be " + expectedLastName + ".");

     int actualTotalPrice = response.jsonPath().getInt("totalprice");
     int expectedTotalPrice = 300;
     Assertions.assertEquals(expectedTotalPrice, actualTotalPrice, "The actual total price is " + actualTotalPrice
             + ", and it was expected to be " + expectedTotalPrice);
     System.out.println("The actual total price in response is "
             + actualTotalPrice + ", and it's supposed to be " + expectedTotalPrice + ".");

     boolean actualDepositPaid = response.jsonPath().getBoolean("depositpaid");
     boolean expectedDepositPaid = true;
     Assertions.assertEquals(expectedDepositPaid, actualDepositPaid, "The actual deposit paid status is " + actualDepositPaid
             + ", and it was expected to be " + expectedDepositPaid);
     System.out.println("The actual \"expected deposit paid in\" response is "
             + actualDepositPaid + ", and it's supposed to be " + expectedDepositPaid + ".");

     String actualCheckIn = response.jsonPath().getString("bookingdates.checkin");
     String actualCheckOut = response.jsonPath().getString("bookingdates.checkout");
     String expectedCheckIn = "2021-09-05";
     String expectedCheckOut = "2021-09-20";
     Assertions.assertEquals(expectedCheckIn, actualCheckIn, "checkin in response is not expected");
     Assertions.assertEquals(expectedCheckOut, actualCheckOut, "checkout in response is not expected");
     System.out.println("The actual check-in date in response is "
             + actualCheckIn + ", and it's supposed to be " + expectedCheckIn + ".");
     System.out.println("The actual check-out date in response is "
             + actualCheckOut + ", and it's supposed to be " + expectedCheckOut + ".");
 }

 @Test
    public void healthCheckTest (){
     given().
             spec(reqSpec).
             when().
             get("/ping").
             then().
                     assertThat().statusCode(201);
     System.out.println("Response: " +
             given().
                     spec(reqSpec).
                     when().
                     get("/ping").asString());
 }

 @Test
    public void getHeadersAndCookiesTest(){
     Header someHeader = new Header("some_name", "some_value");
     reqSpec.header(someHeader);
     Cookie someCookie = new Cookie.Builder("some_name", "some_value").build();
     reqSpec.cookie(someCookie);
         Response response = RestAssured.given(reqSpec).
        log().all().
             get("/ping");
     Headers headers = response.getHeaders();
     System.out.println("Headers: " + headers);
     Header serverHeader1 = headers.get("Server");
     System.out.println(serverHeader1.getName() + ":" + serverHeader1.getValue());
     String serverHeader2 = response.getHeader("Server");
     System.out.println("Server: " + serverHeader2);
     Cookies cookies = response.getDetailedCookies();
     System.out.println("Cookies: " + cookies); // prints no cookies in the first place
 }

    @Test
    public void PartialUpdateBookingTests(){
        Response responseCreate = createBooking();
        responseCreate.prettyPrint();
        JSONObject body = new JSONObject();
        body.put("firstname", "Irina");
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-06-01");
        body.put("bookingdates", bookingdates);
        int bookingId = responseCreate.jsonPath().getInt("bookingid");
        Response responseUpdate = RestAssured.given(reqSpec)
                .auth().preemptive().basic("admin", "password123")
                .contentType(ContentType.JSON).body(body.toString())
                .patch("/booking/" + bookingId);
        int expected = 200;
        int actual = responseUpdate.getStatusCode();
        Assertions.assertEquals(expected, actual, "Status code is supposed to be 200, but it is " + actual);
        System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        String expectedFirstName = "Irina";
        Assertions.assertEquals(expectedFirstName, actualFirstName, "The name in the response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName);
        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        String expectedCheckIn = "2021-06-01";
        Assertions.assertEquals(expectedCheckIn, actualCheckIn, "checkin in response is not expected");
        responseUpdate.prettyPrint();
    }

    @Test
    public void updateBookingTests(){
        Response responseCreate = createBooking();
        responseCreate.print();

        JSONObject body = new JSONObject();
        body.put("firstname", "Irina");
        body.put("lastname", "Suslov");
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

        int expected = 200;
        int actual = responseUpdate.getStatusCode();
        Assertions.assertEquals(expected, actual, "Status code is supposed to be 200, but it is " + actual);
        System.out.println("The actual status code is " + actual + ", and it's supposed to be " + expected + ".");
        String actualFirstName = responseUpdate.jsonPath().getString("firstname");
        String expectedFirstName = "Irina";
        Assertions.assertEquals(expectedFirstName, actualFirstName, "The name in the response is "
                + actualFirstName + ", and it's supposed to be " + expectedFirstName);
        String actualLastName = responseUpdate.jsonPath().getString("lastname");
        String expectedLastName = "Suslov";
        Assertions.assertEquals(expectedLastName, actualLastName, "The actual last name is " + actualLastName
                + ", and it was expected to be " + expectedLastName);
        int actualTotalPrice = responseUpdate.jsonPath().getInt("totalprice");
        int expectedTotalPrice = 150;
        Assertions.assertEquals(expectedTotalPrice,actualTotalPrice, "The actual total price is " + actualTotalPrice
                + ", and it was expected to be " + expectedTotalPrice);
        boolean actualDepositPaid = responseUpdate.jsonPath().getBoolean("depositpaid");
        boolean expectedDepositPaid = false;
        Assertions.assertEquals(expectedDepositPaid,actualDepositPaid, "The actual deposit paid status is " + actualDepositPaid
                + ", and it was expected to be " + expectedDepositPaid);
        String actualCheckIn = responseUpdate.jsonPath().getString("bookingdates.checkin");
        String actualCheckOut = responseUpdate.jsonPath().getString("bookingdates.checkout");
        String expectedCheckIn = "2021-06-03";
        String expectedCheckOut = "2021-06-10";
        Assertions.assertEquals(expectedCheckIn,actualCheckIn, "checkin in response is not expected");
        Assertions.assertEquals(expectedCheckOut,actualCheckOut, "checkout in response is not expected");

        responseUpdate.prettyPrint();
    }

}
