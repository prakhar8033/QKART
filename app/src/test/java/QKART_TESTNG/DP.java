package QKART_TESTNG;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.google.common.collect.Table.Cell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.testng.annotations.DataProvider;


public class DP {

    @DataProvider (name = "data-provider")
    public Object[][] dpMethod (Method m) throws IOException{

        String currentDir = System.getProperty("user.dir");
        String testDataExcelPath = currentDir + "/src/test/resources/";
        String fileName = testDataExcelPath + "Dataset.xlsx";
        
        FileInputStream ExcelFile = new FileInputStream(fileName);
        XSSFWorkbook workBook = new XSSFWorkbook(ExcelFile);
        System.out.println(workBook.getSheetName(2));

        XSSFSheet sheet;

        switch(m.getName()) {
            case "TestCase01": 
                sheet = workBook.getSheet("TestCase01");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString()}
                };
            case "TestCase02":
                sheet = workBook.getSheet("TestCase02");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString()}
                };
            case "TestCase03":
                sheet = workBook.getSheet("TestCase03");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString()},
                    {sheet.getRow(2).getCell(1).toString()},
                    {sheet.getRow(3).getCell(1).toString()}
                };
            case "TestCase04":
                sheet = workBook.getSheet("TestCase04");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString()},
                    {sheet.getRow(2).getCell(1).toString()},
                    {sheet.getRow(3).getCell(1).toString()}
                };
            case "TestCase05":
                sheet = workBook.getSheet("TestCase05");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString()}
                };
            case "TestCase06":
                sheet = workBook.getSheet("TestCase06");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString()}
                };
            case "TestCase07":
                sheet = workBook.getSheet("TestCase07");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString()},
                    {sheet.getRow(2).getCell(1).toString()},
                    {sheet.getRow(3).getCell(1).toString()}
                };
            case "TestCase08":
                sheet = workBook.getSheet("TestCase08");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),(int)sheet.getRow(1).getCell(2).getNumericCellValue()},
                    {sheet.getRow(2).getCell(1).toString(),(int)sheet.getRow(2).getCell(2).getNumericCellValue()},
                    {sheet.getRow(3).getCell(1).toString(),(int)sheet.getRow(3).getCell(2).getNumericCellValue()}
                };
            case "TestCase11":
                sheet = workBook.getSheet("TestCase11");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString(),sheet.getRow(1).getCell(3).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString(),sheet.getRow(2).getCell(3).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString(),sheet.getRow(3).getCell(3).toString()}
                };
            case "TestCase12":
                sheet = workBook.getSheet("TestCase12");
                return new Object[][] {
                    {sheet.getRow(1).getCell(1).toString(),sheet.getRow(1).getCell(2).toString()},
                    {sheet.getRow(2).getCell(1).toString(),sheet.getRow(2).getCell(2).toString()},
                    {sheet.getRow(3).getCell(1).toString(),sheet.getRow(3).getCell(2).toString()}
                };
            }
        return null;
    }
}