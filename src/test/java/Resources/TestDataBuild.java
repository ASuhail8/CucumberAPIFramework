package Resources;

import java.util.ArrayList;
import java.util.List;

import pojo.GoogleAddAPI;
import pojo.GoogleDeleteAPI;
import pojo.Location;

public class TestDataBuild {

	public GoogleAddAPI AppPlacePayload(String name, String language, String address) {
		GoogleAddAPI p = new GoogleAddAPI();
		p.setAccuracy(50);
		p.setName(name);
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress(address);
		p.setWebsite("http://google.com");
		p.setLanguage(language);

		List<String> list = new ArrayList<String>();
		list.add("shoe park");
		list.add("shop");
		p.setTypes(list);

		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		return p;
	}

	public String deleteAPIPayload(String placeId) {
		return "{ \r\n" + "\r\n" + "    \"place_id\":\"" + placeId + "\" \r\n" + "\r\n" + "} ";
	}

	public GoogleDeleteAPI deletePlaceAPIPayload(String placeID) {
		GoogleDeleteAPI d = new GoogleDeleteAPI();
		d.setPlace_id(placeID);
		return d;
	}

}
