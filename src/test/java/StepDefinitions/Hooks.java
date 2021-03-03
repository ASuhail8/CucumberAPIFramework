package StepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {

	@Before("@DeletePlace")
	public void BeforeScenario() throws IOException {

		if (stepDefinition.place_Id == null) {

			stepDefinition m = new stepDefinition();

			m.add_place_payload_with("Suhail", "spanish", "London");
			m.user_calls_with_http_request("addPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Suhail", "getPlaceAPI");

		}

	}

}
