package gusev.api;

import gusev.constants.PropertyConstants;
import gusev.utils.PropertyManager;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;

public class BaseRestAssuredTest {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = PropertyManager.propHandler(PropertyConstants.CONFIG, "API_HOST");
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }
}
