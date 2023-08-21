package day3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PathAndQueryParameters {
	
	// https://reqres.in/api/users?page=2&id=5
	@Test
	void testQueryAndPathParameters() {
		given()
			.pathParam("mypath1", "api")
			.queryParam("page", 3)
			.pathParam("mypath2", "users") // path parameters
			.queryParam("page", 2) // query parameter
			.queryParam("id", 5) // query parameters
		.when()
			.get("https://reqres.in/{mypath1}/{mypath2}")
		.then()
			.statusCode(200)
			.log().all();
	}

}
