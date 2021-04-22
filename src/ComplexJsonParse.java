import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		
		//Print No of courses returned by API 
		int count = js.getInt("courses.size()");
		System.out.println(count);
		
		//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print Title of the first course
		String firstCourse = js.getString("courses.title.get(0)");
		String firstCourse1 = js.get("courses[0].title");
		System.out.println(firstCourse);
		System.out.println(firstCourse1);
		
		//Print All course titles and their respective Prices
		for(int i=0; i<count; i++) {
			String courseTitles = js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString());
			System.out.println(courseTitles);
		}
			
		//Print no of copies sold by RPA Course
			System.out.println("-------------------------------");
			for(int j=0; j<count; j++) {
				String courseTitles1 = js.get("courses["+j+"].title");
				if(courseTitles1.equalsIgnoreCase("RPA"))
				{
					int copies = js.get("courses["+j+"].price");
					System.out.println(copies);
					break;
		}
	}
	}
}
