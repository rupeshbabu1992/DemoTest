import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;

public class SpecBuilder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";

		AddPlace place = new AddPlace();
		place.setAccuracy(50);
		place.setAddress("29, side layout, cohen 09");
		place.setLanguage("English-US");
		place.setPhone_number("+91-9962774717");
		place.setWebsite("https://google.com");
		place.setName("Golden Hive");
		List<String> myList = new ArrayList<String>();
		myList.add("Shoe Park");
		myList.add("Shop");
		place.setTypes(myList);
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		place.setLocation(loc);
	
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				                       .addQueryParam("key", "qaclick123")
		                               .setContentType(ContentType.JSON).build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				                        .expectContentType(ContentType.JSON).build();
		
		RequestSpecification res = given().spec(req)
		.body(place);
		
		
		Response response = res.when().post("/maps/api/place/add/json")
		.then().spec(resSpec).extract().response();
		
		String responseString = response.asString();
	    System.out.println(responseString);
		
	}
}