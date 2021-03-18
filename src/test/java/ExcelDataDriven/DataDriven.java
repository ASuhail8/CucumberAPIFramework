package ExcelDataDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

public class DataDriven {

	public ArrayList<String> getExcelData(String sheetName, String testCaseName) throws IOException {

		FileInputStream fis = new FileInputStream("C:\\Users\\mohammed.a.suhail\\Desktop\\ExcelDriven.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheetCount = workbook.getNumberOfSheets();
		ArrayList<String> al = new ArrayList<String>();
		for (int i = 0; i < sheetCount; i++) {
			int column = 0;
			if (workbook.getSheetName(i).equalsIgnoreCase(sheetName)) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next(); // you are on first Row
				Iterator<Cell> cellIterator = firstRow.cellIterator();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					if (cell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
						column = cell.getColumnIndex();
					}
				}
				while (rows.hasNext()) {
					Row row = rows.next();
					if (row.getCell(column).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						Iterator<Cell> c = row.cellIterator();
						while (c.hasNext()) {
							Cell cel = c.next();
							if (cel.getCellType() == CellType.STRING) {
								al.add(cel.getStringCellValue());
							} else {
								NumberToTextConverter.toText(cel.getNumericCellValue());
								al.add(NumberToTextConverter.toText(cel.getNumericCellValue()));
							}

						}
					}
				}

			}
		}
		workbook.close();
		return al;

	}

}
