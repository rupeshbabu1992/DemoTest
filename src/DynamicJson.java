import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test (dataProvider="BooksData")
	public void addBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String response = given().log().all().header("Content-type", "application/json").
		body(Payload.bookDetail(isbn,aisle)).
		when().post("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(response);
		String id = js.get("ID");
		System.out.println(id);
	}
	
/*	@Test (dataProvider="BooksData")
	public void deleteBook(String isbn, String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String DeleteResponse = given().log().all().header("Content-type", "application/json").
		body(Payload.bookDetail(isbn,aisle)).
		when().delete("Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).
		extract().response().asString();
		
		JsonPath js = ReusableMethods.rawToJson(DeleteResponse);
		String id = js.get("ID");
		System.out.println(id);
	} */
	
	
	@DataProvider (name="BooksData")
	public Object[][] getData() {
		return new Object[][]  { {"ajis", "1234"}, {"lesh", "4321"}, {"jlen", "0987"} };
	}
	

}
