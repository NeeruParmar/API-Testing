package backendlessApi;

import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BackendLogin {

	@Before
	public void before() {
		RestAssured.baseURI = "https://knowingtrade.backendless.app/api/users";

	}

	@Test
	public void UserLoginPostRequestTest() {

		RequestSpecification request = RestAssured.given();
		String email = "nmn@gmail.com";
		String password = "nrr234";
		request.header("Content-Type", "application/json");

		JSONObject jsonObject = new JSONObject();

		jsonObject.put("email", email);
		jsonObject.put("password", "password");

		request.body(jsonObject);
		Response response = request.post("/register");

		Assert.assertEquals(200, response.statusCode());

		JsonPath path = response.jsonPath();

		String respEmail = path.getString("email");
		System.out.println("respEmail : " + email);

		String respPass = path.getString("password");
		System.out.println("respPassword   : " + password);

		Response response1 = request.post("/login");

		String objectId = path.getString("objectId");

		System.out.println("obj ID generated after login " + objectId);

		Assert.assertEquals(200, response.statusCode());

	}

	@Test
	public void enableUserTest() {

		RequestSpecification request = RestAssured.given();
		String email = "nm@gmail.com";
		String password = "nrr234";

		JSONObject jsonObject = new JSONObject();

		request.header("Content-Type", "application/json");
		request.header("user-token", "objectId");

		request.body(jsonObject);

		jsonObject.put("email", email);
		jsonObject.put("password", "password");

		Response response2 = request.put("/<user-id>");

		Assert.assertEquals(200, response2.statusCode());
	}

}
