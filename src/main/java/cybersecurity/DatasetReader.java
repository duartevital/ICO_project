package cybersecurity;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DatasetReader {
	
	public static String PATH = "C:\\Users\\User\\Desktop\\Work\\Java workspace\\projeto-pratico\\src\\main\\java\\cybersecurity\\Dados-ICO.xlsx";
	InputStream inp;
	Workbook wb;
	Sheet sheet;
	DataFormatter formatter;
	
	public DatasetReader() {
		try {
			inp = new FileInputStream(PATH);
			wb = WorkbookFactory.create(inp);
			sheet = wb.getSheetAt(0);
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		formatter = new DataFormatter();
	}
	
	public ArrayList<Float> getValuesArray(int row){
		Row r = sheet.getRow(row);
		ArrayList<Float> values = new ArrayList<Float>();
		
		// Seguinte pode ser melhorado, Em vez de fazer o parse para todas as cells, fazer apenas para aquelas que sejam do tipo text
		for(int i=4; i<8; i++) {
			float value = Float.parseFloat(formatter.formatCellValue(r.getCell(i)));
			values.add(value);
		}
		
		return values;
	}
	
	public static void main(String[] args) throws IOException {
		DatasetReader reader = new DatasetReader();
		ArrayList<Float> array = reader.getValuesArray(7);
		System.out.println(array.toString());
	}
}