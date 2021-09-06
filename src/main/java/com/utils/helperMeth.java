package com.utils;

import com.tngtech.junit.dataprovider.UseDataProvider;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;

public class helperMeth {

    ReadConfig readconfig=new ReadConfig();
    public String baseURL=readconfig.getApplicationURL();

    private static final LoggerUtils LOGGER = new LoggerUtils(helperMeth.class);


    protected RequestSpecification reqSpec;


    @Order(1)
    @UseDataProvider("createNewBooking")
    public Response createBooking() {

        JSONObject body = new JSONObject();
        body.put("firstname", "Carlos");
        body.put("lastname", "Adjetey");
        body.put("totalprice", 300);
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2021-09-05");
        bookingdates.put("checkout", "2021-09-20");
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "Breakfast");

        String endPoint = "/booking";

        Response res = RestAssured.given(reqSpec).contentType(ContentType.JSON).body(body.toString())
                .post(endPoint);
        LOGGER.info("End point set as: " + endPoint);
        LOGGER.info("Converted response body to required model");



        return res;


    }

    @Before
    public void setUp() {
        reqSpec = new RequestSpecBuilder().
                setBaseUri(baseURL).build();
        LOGGER.info("Calling GET request by passing URI " + baseURL);
    }


}
