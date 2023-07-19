package tests;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static io.restassured.matcher.RestAssuredMatchers.*;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class GetAndPostExample {
	
//	@Test
	public void testGet() {
		
		//String baseURI = "https://reqres.in/api";
		
		//given().get("/users?page=2").then().statusCode
		get("https://reqres.in/api/users?page=2").
		then().
		statusCode(200).
		body("data[4].first_name", equalTo("George")).
		body("data.first_name", hasItems("George", "Rachel"));
	}
	
	@Test
	public void testPost(){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
//		map.put("name", "Jitesh");
//		map.put("job", "Student");
//		
//		System.out.println(map);
		
		JSONObject request = new JSONObject();
		
		request.put("name", "Jitesh");
		request.put("Job", "Student");
		
		
		System.out.println(request.toJSONString());
		
		RestAssured.baseURI = "https://reqres.in/api";
		
		given().
		header("Content-Type","application/json").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toJSONString()).
		when().
		post("/user").
		then().
		statusCode(201).log().all();
		
		
		
		
		
	}

}
