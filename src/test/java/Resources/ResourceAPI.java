package Resources;

public enum ResourceAPI {

	addPlaceAPI("/maps/api/place/add/json"), 
	getPlaceAPI("/maps/api/place/get/json"),
	deletePlaceAPI("/maps/api/place/delete/json");

	private String resource;

	ResourceAPI(String resource) {
		this.resource = resource;
	}

	public String getResource() {
		return resource;
	}

}
