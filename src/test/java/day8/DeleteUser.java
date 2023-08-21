package day8;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class DeleteUser {
	
	@Test
	void test_deleteUser(ITestContext context)
	{
		String bearerToken="c35e10e748c6f113775527bcef204e9929b4c9f4b995a8ee253eec46aed57b06";
		
		//int id = (Integer) context.getAttribute("user_id"); 		// this should come from createuser request //chi work khi run tụi con lai chung <test> context
		
		int id = (Integer) context.getSuite().getAttribute("user_id");   //work cho ca khi tui con lai chung test hoac chung suite, chi can chung suite la work duoc het ko quan tam no co chung test ko
		
		given()
			.headers("Authorization", "Bearer "+bearerToken)
			.pathParam("id", id)
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().all();
	}

}
