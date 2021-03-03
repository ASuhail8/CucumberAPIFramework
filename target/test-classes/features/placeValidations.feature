Feature: Validating Place API`s

@AddPlace
Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
Given Add place payload with "<name>" "<language>" "<address>"
When user calls "addPlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_id created maps to "<name>" using "getPlaceAPI"


Examples: 
		  |name    | language | address            |
		  |AAHouse | English  | world trade center |
	#	  |BBHouse | French   | world cross center |
	

@DeletePlace
Scenario: Verify if delete place functionality is working
Given DeletePlace payload
When user calls "deletePlaceAPI" with "POST" http request
Then the API call got success with status code 200
And "status" in response body is "OK"