package pack1.Endpoints;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import pack1.Payload.TouristData;

public class Requests {

	public static Response get_Tourist() {
		Response get_req_res = given().accept("application/json").contentType("application/json").when()
				.get(Routes.get_URL);
		return get_req_res;
	}

	public static Response create_Tourist(TouristData payload) {
		Response post_req_res = given().accept("application/json").contentType("application/json").body(payload).when()
				.get(Routes.post_URL);
		return post_req_res;
	}

	public static Response get_single_Tourist(int id) {
		Response getST_req_res = given().accept("application/json").contentType("application/json").pathParam("id", id)
				.when().get(Routes.get_Single_URL);
		return getST_req_res;
	}

	public static Response create_new_Tourist(TouristData payload, int id) {
		Response put_req_res = given().accept("application/json").contentType("application/json").pathParam("id", id)
				.body(payload).when().get(Routes.put_URL);
		return put_req_res;
	}

	public static Response delete_Tourist(int id) {
		Response delete_req_res = given().accept("application/json").contentType("application/json").pathParam("id", id)
				.when().get(Routes.delete_URL);
		return delete_req_res;
	}

}
