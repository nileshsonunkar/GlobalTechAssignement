package mystore.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;


public class ReadXLSXData {

	@DataProvider(name="testingData")
	public String[][] getData(Method m) throws EncryptedDocumentException, IOException {
		
		String excelSheetName = m.getName();
		File file = new File(System.getProperty("user.dir")+"\\src\\Resources\\testdata\\testData.xlsx");
		FileInputStream fis = new FileInputStream(file);
		Workbook workbook = WorkbookFactory.create(fis);
		Sheet sheetName = workbook.getSheet(excelSheetName);
		
		int totalRows = sheetName.getLastRowNum();
		Row rowcells = sheetName.getRow(0);
		int totalColumns = rowcells.getLastCellNum();
		
		DataFormatter dataFormat = new DataFormatter();
		String testData[][] = new String[totalRows][totalColumns];
		for(int i=1; i<=totalRows; i++) {
			for(int j=0; j<totalColumns; j++) {
				testData[i-1][j] = dataFormat.formatCellValue(sheetName.getRow(i).getCell(j));
			}
			
		}
		return testData;
		
	}
	
	
}
