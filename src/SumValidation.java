import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void sumOfCourses() {
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		int sum = 0;
		int count = js.getInt("courses.size()");
		for(int i=0; i<count; i++) {
			int price = js.get("courses["+i+"].price");
			int copies = js.getInt("courses["+i+"].copies");
			int amount = price * copies;
			
			System.out.println(amount);
			sum = sum+amount;
		}
		System.out.println("The sum amount is:" +sum);
		int purchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("The sum amount is:" +purchaseAmount);
		Assert.assertEquals(sum, purchaseAmount);
	}
	

}
