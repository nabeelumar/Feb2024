package andoorathodi.MavenFeb2024;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		ArrayList data = getData("Testdata","Name","Raj");
		for(int i=0; i<data.size();i++)
		{
			
			System.out.println(data.get(i));
		}

	}
	
	
	public static ArrayList<String> getData(String sheetName, String colName, String testName) throws IOException {
		
		ArrayList<String> al = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("/Users/Nabeel/Documents/Book2.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int sheets = wb.getNumberOfSheets();
		
		for(int i=0; i<sheets; i++) {
			
			if(wb.getSheetName(i).equalsIgnoreCase(sheetName)) {
				
				XSSFSheet sheet = wb.getSheetAt(i);
				
				Iterator<Row> rows = sheet.iterator();
				Row firstRow = rows.next();
				
				Iterator<Cell> cells = firstRow.cellIterator();
				
				int col = 0;
				int k =0;
				while(cells.hasNext()) {
					
					Cell cellValue = cells.next();
					if(cellValue.getStringCellValue().equalsIgnoreCase(colName)) {
						
						col  =k;
						break;
					}
					k++;
				}
				
				
				while(rows.hasNext()) {
					
					Row r = rows.next();
					if(r.getCell(col).getStringCellValue().equalsIgnoreCase(testName)) {
						
						Iterator<Cell> ce = r.cellIterator();
						while(ce.hasNext()) {
							
							Cell cl = ce.next();
							if(cl.getCellType()==CellType.STRING) {
								
								al.add(cl.getStringCellValue());
							}
							else {
								
								String t = NumberToTextConverter.toText(cl.getNumericCellValue());
								al.add(t);
							}
						}
					}
				}
			}
		}
		return al;
	}

}
