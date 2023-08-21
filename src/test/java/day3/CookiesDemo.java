package day3;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;

public class CookiesDemo {

	//@Test(priority = 1)
	void testCookies() {
		given()
		.when()
			.get("https://www.google.com/")
			.then()
				.cookie("AEC", "Ad49MVElYEonuwdcgug_ZjtvuJzuIl8eqKAp2ZmbGCp1Zp8LfGNTWQxyEA")
				.log().all();
	}
	
	@Test(priority = 1)
	void getCookiesInfo() {
		Response res = given()
		.when()
			.get("https://www.google.com/");
		
		//get single cookie info
		//String cookie_value = res.getCookie("AEC");
		//System.out.println("Value of cookie is ===> " +cookie_value);
		
		// get all cookies info
		Map<String, String> cookies_valuesMap = res.getCookies();
		
		for(String cookieName : cookies_valuesMap.keySet()) {
			String cookieValue = res.getCookie(cookieName);
			System.out.println(cookieName + " = " + cookieValue + "\n");
		}
	}
	
}
