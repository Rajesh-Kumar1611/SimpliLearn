package readtestdata.kvp.org;

import java.io.File;
import java.io.FileInputStream;
import java.util.Hashtable;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadData {

	static XSSFSheet sheet1;
	static XSSFWorkbook wb;
	// global variables declaration
	public static Hashtable<String, Integer> dict1 = new Hashtable<String, Integer>();

	// ******************Constructor to load excel from the path
	// specified*********/
	public void getpath(String excelpath)

	{
		try {
			File src = new File(excelpath);
			FileInputStream fis = new FileInputStream(src);
			wb = new XSSFWorkbook(fis);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/****************** Get the Sheet Index from the Excel Sheet *********/
	public void getsheetindex(int indexval)

	{
		System.out.println(wb.getNumberOfSheets());
		sheet1 = wb.getSheetAt(indexval);

	}

	// ****************Get Row Count*************************//
	public int rowcount() {

		int rows = sheet1.getLastRowNum();
		rows += 1;
		return rows;
	}

	/****************** Get the Column Count ***********************/
	public int getcolvalue() {
		int colcount = sheet1.getRow(0).getPhysicalNumberOfCells();
		return colcount;
	}

	/******************
	 * Get the Column Names into the Dictionary and iterate using Hashmap
	 *********/
	public void columndictionary() {
		ReadData readobj = new ReadData();
		for (int col = 0; col < getcolvalue(); col++) {
			dict1.put(readobj.getdata(0, col), col);
		}
	}

	/****************** Get the Data from the Excel Sheet *****************/
	public String getdata(int rows, int cols) {
		String data = null;
		try {
			if (sheet1.getRow(rows).getCell(cols).getRawValue() != null) {
				data = sheet1.getRow(rows).getCell(cols).getRawValue();
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception " +e);
		}
		return data;
	}

	/******************
	 * Get the Column Number from the Column Names extracted by HashMap
	 *********/
	public int getCell(String col) {
		Integer value;
		try {
			value = (Integer) dict1.get(col).intValue();
			return value;
		} catch (NullPointerException e) {
			return (0);
		}
	}

}
