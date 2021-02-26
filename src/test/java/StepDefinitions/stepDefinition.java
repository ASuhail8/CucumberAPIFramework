package StepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class stepDefinition extends Utils {

	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	RequestSpecification reqSpec;
	TestDataBuild data = new TestDataBuild();

	@Given("Add place payload")
	public void add_place_payload() throws IOException {

		// Set header
		res = given().spec(requestSpecification()).body(data.AppPlacePayload());
	}

	@When("user calls {string} with Post http request")
	public void user_calls_with_post_http_request(String string) {
		response = res.when().post("/maps/api/place/add/json").then().spec(reponseSpecBuilder()).extract().response();

	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int statusCode) {
		assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		assertEquals(js.get(keyValue), expectedValue);

	}

}
