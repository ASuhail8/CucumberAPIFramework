package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resources.ResourceAPI;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	RequestSpecification reqSpec;
	TestDataBuild data = new TestDataBuild();
	static String place_Id;

	@Given("Add place payload")
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {

		// Set header
		res = given().spec(requestSpecification()).body(data.AppPlacePayload(name, language, address));
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {

		ResourceAPI resAPI = ResourceAPI.valueOf(resource);

		if (method.equalsIgnoreCase("POST"))
			response = res.when().post(resAPI.getResource()).then().spec(reponseSpecBuilder()).extract().response();
		else if (method.equalsIgnoreCase("GET"))
			response = res.when().get(resAPI.getResource()).then().spec(reponseSpecBuilder()).extract().response();

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int statusCode) {
		assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(getJsonPath(response, keyValue), expectedValue);

	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
		place_Id = getJsonPath(response, "place_id");
		res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
		user_calls_with_http_request(resource, "GET");
		assertEquals(getJsonPath(response, "name"), name);
	}

	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {

		res = given().spec(requestSpecification()).body(data.deleteAPIPayload(place_Id));

	}

}
