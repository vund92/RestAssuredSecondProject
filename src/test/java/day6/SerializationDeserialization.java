package day6;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//Pojo ---- Serialize ---> JSON object ----- de-Serialize ----> Pojo

public class SerializationDeserialization {

	// Pojo  ------> JSON (Serialization)
	//@Test
	void convertPojo2Json() throws JsonProcessingException {

		// created java object using pojo class
		Student stupojo = new Student(); // pojo

		stupojo.setName("Scott");
		stupojo.setLocation("France");
		stupojo.setPhone("123456");
		String coursesArr[] = { "C", "C++" };
		stupojo.setCourses(coursesArr);

		// convert java objet ---> json object (serialization)
		ObjectMapper objMapper = new ObjectMapper();

		String jsondata = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);

		System.out.println(jsondata);
	}
	
	// JSON  ------> Pojo (De-Serialization)
	@Test
	void convertJson2Pojo() throws JsonProcessingException {

		String jsondata = "{\r\n"
				+ "  \"name\" : \"Scott\",\r\n"
				+ "  \"location\" : \"France\",\r\n"
				+ "  \"phone\" : \"123456\",\r\n"
				+ "  \"courses\" : [ \"C\", \"C++\" ]\r\n"
				+ "}";
		
		//convert json data---> Pojo object
		ObjectMapper objMapper = new ObjectMapper();
		
		Student stupojo = objMapper.readValue(jsondata, Student.class);
		
		System.out.println("Name: "+ stupojo.getName()); 
		System.out.println("Location: "+ stupojo.getLocation()); 
		System.out.println("Phone: "+ stupojo.getPhone()); 
		System.out.println("Course 1: "+ stupojo.getCourses()[0]); 
		System.out.println("Course2: "+ stupojo.getCourses()[1]);
	}
	
	
}
