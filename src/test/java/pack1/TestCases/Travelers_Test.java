package pack1.TestCases;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import pack1.Endpoints.TravellersRequests;
import pack1.Payload.TouristData;

public class Travelers_Test {

	public  TouristData travelerInfo;
	public  Faker faker;
	private int createdTouristId;
	
	@BeforeClass
	public void setUpData() {

		travelerInfo = new TouristData();
		faker = new Faker();

		travelerInfo.setId(faker.number().numberBetween(280000, 289999));
		travelerInfo.setTourist_name(faker.name().firstName());
		travelerInfo.setTourist_email(faker.internet().safeEmailAddress());
		travelerInfo.setTourist_location(faker.address().fullAddress());

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

		// Create a Date object with a specific time stamp
		Date date = new Date(System.currentTimeMillis()); // You can set the desired time stamp here

		// Format the Date object to the required format
		String formattedTimestamp = dateFormat.format(date);
		
		travelerInfo.setCreatedat(formattedTimestamp);

	}
	
	@Test(priority=1)
	public  void getTravelsInfo(){
		Response get_Tourists = TravellersRequests.get_Tourist();
		get_Tourists.then().log().all();
		Assert.assertEquals(get_Tourists.getStatusCode(), 200);
	}
	
	@Test(priority=2)
	public void create_traveler() {
		Response create_Tourists = TravellersRequests.create_Tourist(travelerInfo);
		create_Tourists.then().log().all();
		Assert.assertEquals(create_Tourists.getStatusCode(), 201);
		
		Response get_Tourists = TravellersRequests.get_Tourist();
		get_Tourists.then().log().all();
		Assert.assertEquals(get_Tourists.getStatusCode(), 200);
	}
	
	@Test(priority=3)
	public void get_tourist_created() {
		int createdTouristId = this.travelerInfo.getId();
		System.out.println("========================================="+createdTouristId);
		Response single_Tourist = TravellersRequests.get_single_Tourist(createdTouristId);
		single_Tourist.then().log().all();
		Assert.assertEquals(single_Tourist.getStatusCode(), 200);
	}
	
	
	
	@Test(priority=4, enabled=false)//Method is not allowed by the server. Status code: 405
	public void create_new_created() {
		
		travelerInfo.setId(faker.idNumber().hashCode());
		travelerInfo.setTourist_name(faker.name().firstName());
		travelerInfo.setTourist_email(faker.internet().safeEmailAddress());
		travelerInfo.setTourist_location(faker.address().fullAddress());
		
		Response add_Tourist = TravellersRequests.create_new_Tourist(travelerInfo, createdTouristId);
		add_Tourist.then().log().all();
		Assert.assertEquals(add_Tourist.getStatusCode(), 200);
	}

	@Test(priority=5, enabled=false)//Method is not allowed by the server. Status code: 405
	public  void deleteTravelsInfo(){
		Response delete_it = TravellersRequests.delete_Tourist(createdTouristId);
		delete_it.then().log().all();
		Assert.assertEquals(delete_it.getStatusCode(), 200);
	}
}
