package com.utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Before;

public class helperMeth {

    protected RequestSpecification reqSpec;

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


        Response res = RestAssured.given(reqSpec).contentType(ContentType.JSON).body(body.toString())
                .post("/booking");
        return res;
    }

    @Before
    public void setUp() {
        reqSpec = new RequestSpecBuilder().
                setBaseUri("https://restful-booker.herokuapp.com").build();
    }


}
