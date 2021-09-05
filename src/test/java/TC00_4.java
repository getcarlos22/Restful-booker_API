import com.utils.LoggerUtils;
import com.utils.helperMeth;
import io.restassured.RestAssured;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TC00_4 extends helperMeth {

    private static final LoggerUtils LOGGER = new LoggerUtils(TC00_4.class);
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
        LOGGER.info("GET healthCheckTest has been made " + "/ping");
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
        LOGGER.info("GET getHeadersAndCookiesTest has been made " + headers);
        Header serverHeader1 = headers.get("Server");
        System.out.println(serverHeader1.getName() + ":" + serverHeader1.getValue());
        LOGGER.info("GET getHeadersAndCookiesTest Response " + serverHeader1.getName() + ":" + serverHeader1.getValue());
        String serverHeader2 = response.getHeader("Server");
        System.out.println("Server: " + serverHeader2);
        LOGGER.info("GET getHeadersAndCookiesTest has been made " + serverHeader2);
        Cookies cookies = response.getDetailedCookies();
        System.out.println("Cookies: " + cookies);
        LOGGER.info("GET Cookies has been made " + cookies);
    }
}
