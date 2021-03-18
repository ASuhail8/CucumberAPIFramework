package ExcelDataDriven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class testSample {

	public static void main(String[] args) throws IOException {

		DataDriven excelData = new DataDriven();
		ArrayList<String> data = excelData.getExcelData("RestAssured", "RestAddBook");

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("Name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));

		System.out.println(map.get("author"));

	}

}
