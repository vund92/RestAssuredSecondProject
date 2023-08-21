package day4;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType; 
import io.restassured.response.Response;

import static io. restassured.RestAssured.*;
import static io. restassured.matcher.RestAssuredMatchers.*; 
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class ParsingJSONResponseData {
	
	//@Test(priority = 1)
	void testJsonResponse() {
		// Appoach1
//		given()
//			.contentType(ContentType.JSON)
//		.when()
//			.get("http://localhost:3000/store")
//		.then()
//			.statusCode(200)
//			.header("Content-Type", "application/json; charset=utf-8")
//			.body("books[3].title", equalTo("The Lord of the Rings")) 
//			.log().all();
		
//		Response response = given()
//				.contentType(ContentType.JSON)
//				.when()
//					.get("http://localhost:3000/books");
//		
//		System.out.println(response.asPrettyString());
		
		
		//Approach2
		Response res = given()
				.contentType(ContentType.JSON)
		.when()
			.get("http://localhost:3000/store");
		
		Assert.assertEquals(res.getStatusCode(), 200); //validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		
		String bookname = res.jsonPath().get("books[3].title").toString(); 
		Assert.assertEquals(bookname, "The Lord of the Rings");
		
	}
	
	@Test(priority=2)
	void testJsonResponseBodyData()
	{
		Response res= given()
			.contentType(ContentType.JSON)  // .contentType("ContentType.JSON") van ok 
		.when()
			.get("http://localhost:3000/store");
		
		System.out.println(res.asPrettyString());
		
		Assert.assertEquals(res.getStatusCode(), 200);                   //validation 1
		Assert.assertEquals(res.header("Content-Type"), "application/json; charset=utf-8");
		String bookname=res.jsonPath().get("books[3].title").toString(); 
		Assert.assertEquals(bookname, "The Lord of the Rings");
		
		//JSONObject class.
		JSONObject jo=new JSONObject(res.asString()); // converting response to json object type - phai la asString() nha
																					  //  doi voi JSONArray jarr = new JSONArray(res.asString()); thi cung phai dung asString
																					  // {[{},{},{}]} --> day la JSONObject -> JSONArray -> JSONObjects
																					 //  [{},{},{}] --> day la JSONArray -> JSONObjects
		
		//print all titles of books
		for (int i = 0; i < jo.getJSONArray("books").length(); i++) {
			String bookTitle = jo.getJSONArray("books").getJSONObject(i).get("title").toString();  // Json Object --> Json Array --> Json Object (theo dung cau truc cua file json data
																																					// Vi du neu no la Array san roi: jarr.getJSONObject(i).get("title").toString();
																																					// Xem https://www.youtube.com/watch?v=SBp5rStA3vQ&list=PLUDwpEzHYYLvLZX_QEGTNolPvNADXid0I&index=7 (tu doan add faker library
			
			System.out.println(bookTitle);
		}
		
		//search for title of the book in json - validation 1
		boolean status = false;

		for (int i = 0; i < jo.getJSONArray("books").length(); i++) {
			String bookTitle = jo.getJSONArray("books").getJSONObject(i).get("title").toString();
			if (bookTitle.equals("The Lord of the Rings")) {
				status = true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
		//validate total price of books - validation 2
		double totalprice=0;
		
		for (int i = 0; i < jo.getJSONArray("books").length(); i++) {
			String price = jo.getJSONArray("books").getJSONObject(i).get("price").toString();
			totalprice = totalprice + Double.parseDouble(price);
		}
		
		System.out.println("Total price of books is: " + totalprice);
		Assert.assertEquals(totalprice, 526.0);
	
	}
}
