package pack1.Endpoints;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import pack1.Payload.UserData;

public class Users_Requests {

	public static Response list_User() {

		Response response = given().accept("application/json").when().get(Routes.u_get_URL);
		return response;
	}

	public static Response new_User(UserData payload) {

		Response response = given().accept("application/json").body(payload).contentType("application/json").header("Authorization", "Bearer your_token").when().post(Routes.u_post_URL);
		return response;
	}
}
