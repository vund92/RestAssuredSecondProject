package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class CreateUser {
	
	@Test
	void testCreateUser(ITestContext context) {
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "inactive");
		
		String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		
		int id = 
				given()
					.headers("Authorization", "Bearer " + bearerToken)
					.contentType("application/json")
					.body(data.toString())
				.when()
					.post("https://gorest.co.in/public/v2/users")
					.jsonPath().getInt("id");
		
		System.out.println("Generated id is: " + id);
		
		//context.setAttribute("user_id", id);  //chi work khi run tụi con lai chung <test> context
		
		context.getSuite().setAttribute("user_id", id); //work cho ca khi tui con lai chung test hoac chung suite, chi can chung suite la work duoc het ko quan tam no co chung test ko
	}

}
