import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import pojo.AddPlace;
import pojo.Location;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest {

	
	public static void main(String args[]) {
		
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
		
		
		Response response = given().log().all().queryParam("key", "qaclick123")
		.body(place)
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).extract().response();
		
		String responseString = response.asString();
	    System.out.println(responseString);
		
	}
}
