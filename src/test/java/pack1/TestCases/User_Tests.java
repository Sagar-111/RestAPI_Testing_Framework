package pack1.TestCases;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import pack1.Endpoints.Users_Requests;
import pack1.Payload.UserData;

public class User_Tests {
	
	public static UserData payload;
	public static Faker faker;
	
	@BeforeClass
	public void setUserData() {
		
		payload=new UserData();
		faker=new Faker();
		
		payload.setId(1111);
		payload.setUser_email("emailstring123@hotmail.com");
		payload.setUser_location("Prayagraj");
		payload.setUser_name("Sagar");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		// Create a Date object with a specific time stamp
		Date date = new Date(System.currentTimeMillis()); // You can set the desired time stamp here

		// Format the Date object to the required format
		String formattedTimestamp = dateFormat.format(date);
		
		payload.setCreatedat(formattedTimestamp);
		payload.setUser_profilePicture(faker.avatar().image());
		
	}

	@Test(enabled = false)
	public static void get_ListOf_Users() {
		Response list_Users = Users_Requests.list_User();
		list_Users.then().log().all();
		Assert.assertEquals(list_Users.getStatusCode(), 401);
	}
	@Test(priority=2)
	public static void createNewUser() {
		Response new_Users = Users_Requests.new_User(payload);
		new_Users.then().log().all();
		Assert.assertEquals(new_Users.getStatusCode(), 200);
	}
	
}
