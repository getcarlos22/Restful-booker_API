import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class TestBooker {

    @Test
    public void firstTest(){

        assertThat(RestAssured.config(), instanceOf(RestAssuredConfig.class));
    }
}
