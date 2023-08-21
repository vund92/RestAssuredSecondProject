package day6;

import org.testng.annotations.Test;

import static io. restassured.RestAssured.*;
import static io. restassured.matcher.RestAssuredMatchers.*; 
import static org.hamcrest.Matchers.*;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;


//json ---> jsonschema converter
//https://jsonformatter.org/json-to-jsonschema

//Json Response (.json) ---> Json schema (.json)
//XML Response (.xml) ---> xml schema (.xsd)

//xml ---> xsd: https://www.liquid-technologies.com/online-xml-to-xsd-converter

public class XMLSchemaValidation {
	
	@Test
	void xmlSchemavalidation() {
		
		given() 
		.when()
			.get("http://restapi.adequateshop.com/api/Traveler")
		.then()
			.assertThat()
			.body(RestAssuredMatchers.matchesXsdInClasspath("travellerXMLSchema.xsd"));
		
	}
	
}
