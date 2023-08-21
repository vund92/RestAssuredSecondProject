package day6;

import org.testng.annotations.Test;

import static io. restassured.RestAssured.*;
import static io. restassured.matcher.RestAssuredMatchers.*; 
import static org.hamcrest.Matchers.*;

import io.restassured.module.jsv.JsonSchemaValidator;


//json ---> jsonschema converter
//https://jsonformatter.org/json-to-jsonschema

//Json Response (.json) ---> Json schema (.json)
//XML Response (.xml) ---> xml schema (.xsd)

//xml ---> xsd: https://www.liquid-technologies.com/online-xml-to-xsd-converter

public class JSONSchemaValidation {
	
	@Test
	void jsonSchemaValidation() {
		
		given() 
		.when()
			.get("http://localhost:3000/store")
		.then()
			.assertThat()
			.body (JsonSchemaValidator.matchesJsonSchemaInClasspath("storeJsonSchema.json"));
		
	}

}
