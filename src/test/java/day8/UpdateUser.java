package day8;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class UpdateUser {

	@Test
	void testUpdateUser(ITestContext context) {
		Faker faker = new Faker();
		
		JSONObject data = new JSONObject();
		
		data.put("name", faker.name().fullName());
		data.put("gender", "Male");
		data.put("email", faker.internet().emailAddress());
		data.put("status", "active");
		
		String bearerToken = "c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		
		//int id = (Integer) context.getAttribute("user_id"); 		// this should come from createuser request //chi work khi run tụi con lai chung <test> context
		
		int id = (Integer) context.getSuite().getAttribute("user_id");   //work cho ca khi tui con lai chung test hoac chung suite, chi can chung suite la work duoc het ko quan tam no co chung test ko
		
		given()
					.headers("Authorization", "Bearer " + bearerToken)
					.contentType("application/json")
					.pathParam("id", id)
					.body(data.toString())
				.when()
					.put("https://gorest.co.in/public/v2/users/{id}")
				.then()
					.statusCode(200)
					.log().all();
	}
}
