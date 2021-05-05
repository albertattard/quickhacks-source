package quickhack;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(final String[] args) throws IOException {
        try (final InputStream inputStream = readExcelFile();
             final XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            for (final Sheet sheet : workbook) {
                printSheetName(sheet);
                for (final Row row : sheet) {
                    for (final Cell cell : row) {
                        printCellValue(cell);
                    }
                    System.out.println();
                }
            }
        }
    }

    private static void printSheetName(final Sheet sheet) {
        System.out.printf("Sheet: %s%n", sheet.getSheetName());
    }

    private static void printCellValue(final Cell cell) {
        if (cell.getCellType() == CellType.NUMERIC) {
            System.out.printf("%.0f", cell.getNumericCellValue());
        } else {
            System.out.printf("%-16s", cell.getStringCellValue());
        }
        System.out.print("\t");
    }

    private static InputStream readExcelFile() throws FileNotFoundException {
        return new BufferedInputStream(new FileInputStream("src/main/resources/samples/simple.xlsx"));
    }
}
