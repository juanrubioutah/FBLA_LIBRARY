import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;

public class ReportCreator {
	
	public boolean isBookReport;
	
	public BookManager bookManager = main.getGlobalBookManager();
	
	public UserManager userManager = main.getGlobalUserManager();
	
	public ReportCreator(String reportType) {
		if(reportType.equals("book")) {
			isBookReport = true;
		}
		else if(reportType.equals("users")) {
			isBookReport = false;
		}
	}
	
	public boolean create() {
		try {
			String filename = "report.xls";
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Sheet");
			
			if(isBookReport==true) {
				HSSFRow rowhead = sheet.createRow((short)0);
				
				rowhead.createCell(0).setCellValue("Book");
				rowhead.createCell(1).setCellValue("Author");
				rowhead.createCell(2).setCellValue("Barcode");
				rowhead.createCell(3).setCellValue("Checked Out?");
				rowhead.createCell(4).setCellValue("Last User");
				rowhead.createCell(5).setCellValue("Due Date");
				
				for(int i = 0; i<bookManager.books.size(); i++) {
					HSSFRow row = sheet.createRow((short)i+1);
					row.createCell(0).setCellValue(bookManager.books.get(i).getTitle());
					row.createCell(1).setCellValue(bookManager.books.get(i).getAuthor());
					row.createCell(2).setCellValue(String.valueOf(bookManager.books.get(0).getISBN()));
					if(bookManager.books.get(i).isCheckedOut==true) {
						row.createCell(3).setCellValue("Yes");
					}
					if(bookManager.books.get(i).isCheckedOut==false) {
						row.createCell(3).setCellValue("No");
					}
					if(bookManager.books.get(i).lastUser!=null) {
						row.createCell(4).setCellValue(bookManager.books.get(i).lastUser.getName());
					}
					else if(bookManager.books.get(i).lastUser==null) {
						row.createCell(4).setCellValue("");
					}
					if(bookManager.books.get(i).getDueDate()!=null) {
						row.createCell(4).setCellValue(bookManager.books.get(i).lastUser.getName());
					}
					else if(bookManager.books.get(i).getDueDate()==null) {
						row.createCell(5).setCellValue("");
					}
					
				}
			}
			else if(isBookReport==false) {
				HSSFRow rowhead = sheet.createRow((short)0);
				
				rowhead.createCell(0).setCellValue("Name");
				rowhead.createCell(1).setCellValue("ID Number");
				rowhead.createCell(3).setCellValue("Current Fine");
				
				for(int i = 0; i<userManager.users.size(); i++) {
					HSSFRow row = sheet.createRow((short)i+1);
					row.createCell(0).setCellValue(userManager.users.get(i).getName());
					row.createCell(1).setCellValue(userManager.users.get(i).getID());
					row.createCell(3).setCellValue(userManager.users.get(i).fineAmount);
				}
			}
			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
