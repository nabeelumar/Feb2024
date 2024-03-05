package andoorathodi.MavenFeb2024;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelSample {
	DataFormatter formatter = new DataFormatter();
	
	
	@Test
	public void sample() {
		System.out.println("This is sample test");
	}
	
	@Test(dataProvider = "getDataTest")
	public void test(String sl, String ID, String Name, String Dept) {
		System.out.println("TEST STARTED");
		
		System.out.println(sl+" "+ID+" "+Name+" "+Dept);
	}
	
	@DataProvider(name="getDataTest")
	public Object[][] getData() throws IOException{
		
		FileInputStream fis = new FileInputStream("/Users/Nabeel/Documents/Book2.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sh = wb.getSheetAt(1);
		
		int rowCount = sh.getPhysicalNumberOfRows();
		XSSFRow row = sh.getRow(0);
		int colCount = row.getLastCellNum();
		
		Object data[][] = new Object[rowCount-1][colCount];
		for(int i =0; i<rowCount-1;i++) {
			row = sh.getRow(i+1);
			for(int j=0; j<colCount;j++) {
				
				XSSFCell cell = row.getCell(j);
				data[i][j] = formatter.formatCellValue(cell);
			}
			
		}

		
		return data;
	}

}
