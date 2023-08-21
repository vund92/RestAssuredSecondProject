package day7;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;


public class Authentication {

	//@Test(priority=1) 
	void testBasicAuthentication()
	{
		given()
			.auth().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode (200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority=2) 
	void testDigestAuthentication()
	{
		given()
			.auth().digest("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode (200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority=3) 
	void testPreemtiveAuthentication()
	{
		given()
			.auth().preemptive().basic("postman", "password")
		.when()
			.get("https://postman-echo.com/basic-auth")
		.then()
			.statusCode (200)
			.body("authenticated", equalTo(true))
			.log().all();
	}
	
	//@Test(priority=4)
	void testBearerTokenAuthentication()
	{
		String bearerToken = "ghp_24pH0Icz1PKHC1q0tLwj57AuDYmtSz2fuYKP";
		given()
			.headers("Authorization", "Bearer " + bearerToken)
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test(priority=5)
	void testOAuth1Authentication()
	{
		given()
			.auth().oauth("consumerkey", "consumerSecrat", "accessToken", "tokenSecrate") //this is for OAuth 1.0 authentication
		.when()
			.get("url")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	//@Test
	void testOAuth2Authentication()
	{
		given ()
			.auth().oauth2("ghp_24pH0Icz1PKHC1q0tLwj57AuDYmtSz2fuYKP")
		.when()
			.get("https://api.github.com/user/repos")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	@Test
	void testAPIKeyAuthentication()
	{
		//Method 1
		
		/*given()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c") //appid is APIKey
		.when()
			.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")  // https://api.openweathermap.org => domain host
																																									   // data/2.5/forecast/daily => path parameters (before ?)
																																									   // q=Delhi&units=metric&cnt=7 => conditions/parameters (after ?)
		.then()
			.statusCode (200) 
			.log().all();*/
		
		//Method 2
		given ()
			.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
			.pathParam("mypath", "data/2.5/forecast/daily")
			.queryParam("q", "Delhi")
			.queryParam("units", "metric")
			.queryParam("cnt", "7")
		.when()
			.get("https://api.openweathermap.org/{mypath}")
		.then()
			.statusCode (200) 
			.log().all();
	}
	
}
