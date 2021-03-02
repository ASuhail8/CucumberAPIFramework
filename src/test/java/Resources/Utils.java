package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification reqSpec;
	ResponseSpecification resSpec;

	public RequestSpecification requestSpecification() throws IOException {
		if (reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("./src/logs/logs.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl")).setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return reqSpec;
		}
		return reqSpec;
	}

	public ResponseSpecification reponseSpecBuilder() {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build(); // expect
		return resSpec;
	}

	public static String getGlobalValue(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"D:\\Abdulla Suhail\\work\\SuhailWorkSpace\\src\\CucumberAPIFramework\\src\\test\\java\\Resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response response, String key) {
		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.get(key).toString();

	}

}
