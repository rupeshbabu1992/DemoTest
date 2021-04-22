import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.Payload;
import files.ReusableMethods;

public class DemoClass {

	public static void main(String[] args) throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com/";

//add place
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json")
				//.body(Payload.AddPlace()).when().post("maps/api/place/add/json").then().log().all().assertThat()
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\brupesh\\Downloads\\addPlace.json")))).
				when().post("maps/api/place/add/json")
				.then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract()
				.response().asString();

		System.out.println(response);
		JsonPath js = new JsonPath(response);
		String placeId = js.get("place_id");
		System.out.println(placeId);
		
 //Update place
		String newAddress = "Nehru Street, Krishnapuram";
		
		given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json")
		.body("{\r\n"
				+ "    \"place_id\": \""+placeId+"\",\r\n"
				+ "    \"address\": \""+newAddress+"\",\r\n"
				+ "    \"key\":\"qaclick123\"\r\n"
				+ "}").
		when().put("/maps/api/place/update/json")
		.then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));
		

//get place 
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
		.queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		//JsonPath js1 = new JsonPath(getPlaceResponse);
		JsonPath js1 = ReusableMethods.rawToJson(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress); 
	}
}
